package com.pos.posspringbackend.auth.service;

import com.pos.posspringbackend.auth.request.AuthenticationRequest;
import com.pos.posspringbackend.auth.request.RegisterRequest;
import com.pos.posspringbackend.auth.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
