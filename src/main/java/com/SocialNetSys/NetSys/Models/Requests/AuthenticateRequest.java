package com.SocialNetSys.NetSys.Models.Requests;

import lombok.Data;

@Data
public class AuthenticateRequest {
    public String email;
    public String password;
}
