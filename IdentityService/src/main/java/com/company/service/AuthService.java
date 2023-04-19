package com.company.service;

import com.company.dto.UserDTO;
import com.company.service.feign.Demo1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Demo1Service demo1Service;
    private final JwtService jwtService;

    public UserDTO saveUser(UserDTO dto) {
        return demo1Service.create(dto);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}