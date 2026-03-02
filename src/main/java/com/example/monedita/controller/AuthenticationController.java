package com.example.monedita.controller;

import com.example.monedita.dto.AuthLoginRequestDTO;
import com.example.monedita.dto.AuthResponseDTO;
import com.example.monedita.service.UserDetailServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImp userDetailService;

    //Login es mediante postmapping
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailService.login(userRequest), HttpStatus.OK);
    }

}
