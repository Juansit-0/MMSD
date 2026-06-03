package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.LoginRequest;
import com.montaniamargarita.dto.response.AuthResponse;

/** Servicio de autenticación: emite JWT cuando las credenciales son válidas. */
public interface IAuthService {

    AuthResponse autenticar(LoginRequest request);
}
