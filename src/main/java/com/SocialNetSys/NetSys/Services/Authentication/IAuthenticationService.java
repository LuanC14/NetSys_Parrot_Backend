package com.SocialNetSys.NetSys.Services.Authentication;

import com.SocialNetSys.NetSys.Models.Requests.AuthenticateRequest;
import com.SocialNetSys.NetSys.Models.Responses.AuthenticateResponse;

public interface IAuthenticationService {

    public AuthenticateResponse authenticate(AuthenticateRequest request);
}
