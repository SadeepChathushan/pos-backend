package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.ReqRes;
import com.aasait.pos.backend.entity.OurUsers;
import com.aasait.pos.backend.repository.OurUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class UsersManagementService {
    @Autowired
    private OurUsersRepo ourUsersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            Optional<OurUsers> existingUser = Optional.empty();



            // If not a child login, check by email
            if (!existingUser.isPresent() && (loginRequest.getEmail() != null) && !loginRequest.getEmail().isEmpty()) {
                existingUser = ourUsersRepo.findByEmail(loginRequest.getEmail());
            }

            if (!existingUser.isPresent()) {
                response.setStatusCode(400);
                response.setMassage("Email or Child ID not found!");
                return response;
            }

            var user = existingUser.orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setUserId(user.getId());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMassage("Successfully logged in");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
        }
        return response;
    }




    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            OurUsers users = ourUsersRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMassage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
            return response;
        }
    }





    public ReqRes userRegister(ReqRes req) {
        ReqRes resp = new ReqRes();

        try {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMassage("Password cannot be null or empty");
                return resp;
            }

            Optional<OurUsers> existingUserByEmail = ourUsersRepo.findByEmail(req.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMassage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(req.getEmail());
            ourUser.setFullName(req.getFullName());
            ourUser.setNic(req.getNic());
            ourUser.setCreatedAt(new Date());  // Automatically set to the current timestamp
            ourUser.setPassword(passwordEncoder.encode(req.getPassword()));
            ourUser.setContactNo(req.getContactNo());
            ourUser.setDob(req.getDob());
            ourUser.setGender(req.getGender());
            ourUser.setRole(req.getRole());

            OurUsers ourUsersResult = ourUsersRepo.save(ourUser);
            if (ourUsersResult.getId() > 0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
}
