package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.AuthenticateRequest;
import com.SocialNetSys.NetSys.Models.Responses.AuthenticateResponse;
import com.SocialNetSys.NetSys.Services.Authentication.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private IAuthenticationService _authenticationService;
    @PostMapping()
    @Operation(description = "Realiza a autenticação do usuário e gera um Token JWT, que é inserido nas requisições pelo Middleware, caso seja necessário.")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok().body(_authenticationService.authenticate(request));
    }
}
