package com.SocialNetSys.NetSys.Services.Security;

import java.security.Key;
import java.util.UUID;

public interface IJwtService {

    public String generateToken(UUID userID);


}
