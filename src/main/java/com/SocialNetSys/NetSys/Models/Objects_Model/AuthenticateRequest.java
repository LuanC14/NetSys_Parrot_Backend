package com.SocialNetSys.NetSys.Models.Objects_Model;

import lombok.Data;

@Data
public class AuthenticateRequest {
    public String email;
    public String password;
}
