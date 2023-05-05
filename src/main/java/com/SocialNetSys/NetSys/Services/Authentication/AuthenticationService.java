package com.SocialNetSys.NetSys.Services.Authentication;

import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateResponse;
import com.SocialNetSys.NetSys.Services.Security.IJwtService;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private IUserService _userService;
    @Autowired
    private IJwtService _jwtService;

    public AuthenticateResponse authenticate(AuthenticateRequest request) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        var user = _userService.getUserByEmail(request.email);

        var verifyPasswordMatch = encoder.matches(request.password, user.getPassword());

        if(!verifyPasswordMatch) {
           return null;
        }

        var token = _jwtService.generateToken(user.getId());

        var response =  new AuthenticateResponse();

        response.setUserID(user.getId());
        response.setToken(token);

        return response;
    }
}
