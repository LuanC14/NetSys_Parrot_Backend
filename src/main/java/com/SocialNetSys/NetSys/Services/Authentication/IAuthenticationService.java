package com.SocialNetSys.NetSys.Services.Authentication;

import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.AuthenticateResponse;

public interface IAuthenticationService {

    public AuthenticateResponse authenticate(AuthenticateRequest request);
}
