package com.SocialNetSys.NetSys.Models.Objects_Model;

import lombok.Data;

import java.util.UUID;
@Data
public class AuthenticateResponse {
    public UUID userID;
    public String token;
}
