package com.pos.posspringbackend.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.posspringbackend.auth.request.AuthenticationRequest;
import com.pos.posspringbackend.auth.request.RegisterRequest;
import com.pos.posspringbackend.auth.response.AuthenticationResponse;
import com.pos.posspringbackend.auth.service.AuthenticationService;
import com.pos.posspringbackend.config.JwtService;
import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.service.EmployeeService;
import com.pos.posspringbackend.token.entity.Token;
import com.pos.posspringbackend.token.enumerated.TokenType;
import com.pos.posspringbackend.token.repository.TokenRepository;
import com.pos.posspringbackend.user.entity.User;
import com.pos.posspringbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmployeeService employeeService;

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        Employee employee = request.getEmployee();
        user.setEmployee(employee);
        employee.setUser(user);
        userRepository.save(user);
        employeeService.createEmployee(employee);
//        String jwtToken = jwtService.generateToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user);
//        savedUserToken(saveduser, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokedAllUserToken(user);
        savedUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .userId(String.valueOf(user.getId()))
                .role(String.valueOf(user.getRole()))
                .build();
    }

    private void revokedAllUserToken(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void savedUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authorizationHeader.substring(7);
        userEmail = jwtService.extractUserEmail(refreshToken);
        if(userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokedAllUserToken(user);
                savedUserToken(user, accessToken);
                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
            }
        }
    }
}
