package com.SocialNetSys.NetSys.Controllers.Authentication;

import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateResponse;
import com.SocialNetSys.NetSys.Services.Authentication.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuthenticationService _authenticationService;
    @PostMapping()
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok().body(_authenticationService.authenticate(request));
    }
}
