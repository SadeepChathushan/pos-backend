package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.ReqRes;
import com.aasait.pos.backend.service.UsersManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/user-register")
    public ResponseEntity<ReqRes> midwifeRegister(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.userRegister(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req, HttpServletResponse response) {
        return ResponseEntity.ok(usersManagementService.login(req, response));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(usersManagementService.refreshToken(request, response));
    }
}
