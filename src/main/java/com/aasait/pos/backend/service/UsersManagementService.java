package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.ReqRes;
import com.aasait.pos.backend.entity.OurUsers;
import com.aasait.pos.backend.repository.OurUsersRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersManagementService {

    private final OurUsersRepo           usersRepo;
    private final AuthenticationManager  authManager;
    private final PasswordEncoder        encoder;
    private final JWTUtils               jwt;
    private final LoginAttemptService    attempts;
    private final RefreshTokenService    refreshSvc;

    /* ---------------------------------------------------------
     * LOGIN
     * --------------------------------------------------------- */
    public ReqRes login(ReqRes in, HttpServletResponse response) {
        ReqRes resp = new ReqRes();

        OurUsers user = usersRepo.findByEmail(in.getEmail()).orElse(null);
        if (user == null) {
            resp.setStatusCode(404);
            resp.setMassage("Email not found");
            return resp;
        }

        if (user.isLocked()) {
            resp.setStatusCode(423);
            resp.setMassage("Account temporarily locked – try again later");
            return resp;
        }

        String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getRemoteAddr();

        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(in.getEmail(), in.getPassword()));
        } catch (BadCredentialsException bad) {
            attempts.onFailure(user, ip);
            resp.setStatusCode(401);
            resp.setMassage("Invalid credentials");
            return resp;
        }

        attempts.onSuccess(user, ip);
        String access = jwt.generateToken(user);
        String refresh = refreshSvc.issue(user);

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refresh)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        resp.setStatusCode(200);
        resp.setMassage("Logged-in");
        resp.setToken(access);
        resp.setExpirationTime("5 min");
        resp.setRole(user.getRole());
        resp.setUserId(user.getId());
        return resp;
    }

    public ReqRes refreshToken(HttpServletRequest request, HttpServletResponse response) {
        ReqRes resp = new ReqRes();

        try {
            String old = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                    .filter(c -> c.getName().equals("refreshToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElseThrow(() -> new RuntimeException("Missing refresh token"));

            OurUsers user = refreshSvc.validateAndRotate(old);
            String access = jwt.generateToken(user);
            String newRefresh = refreshSvc.issue(user);

            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", newRefresh)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .path("/auth/refresh")
                    .maxAge(Duration.ofDays(7))
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

            resp.setStatusCode(200);
            resp.setMassage("Token refreshed");
            resp.setToken(access);
            resp.setExpirationTime("5 min");
            resp.setRole(user.getRole());
            resp.setUserId(user.getId());
        } catch (RuntimeException ex) {
            resp.setStatusCode(401);
            resp.setMassage(ex.getMessage());
        }

        return resp;
    }

    /* ---------------------------------------------------------
     * USER REGISTER
     * --------------------------------------------------------- */
    public ReqRes userRegister(ReqRes in) {

        ReqRes resp = new ReqRes();

        if (in.getPassword() == null || in.getPassword().isBlank()) {
            resp.setStatusCode(400);
            resp.setMassage("Password cannot be empty");
            return resp;
        }
        if (usersRepo.existsByEmail(in.getEmail())) {
            resp.setStatusCode(409);
            resp.setMassage("Email already registered");
            return resp;
        }

        OurUsers u = new OurUsers();
        u.setEmail(in.getEmail());
        u.setFullName(in.getFullName());
        u.setNic(in.getNic());
        u.setContactNo(in.getContactNo());
        u.setDob(in.getDob());
        u.setGender(in.getGender());
        u.setRole(in.getRole());
        u.setCreatedAt(new Date());
        u.setPassword(encoder.encode(in.getPassword()));

        usersRepo.save(u);

        resp.setStatusCode(201);
        resp.setMassage("User registered");
        resp.setOurUsers(u);
        return resp;
    }
}
