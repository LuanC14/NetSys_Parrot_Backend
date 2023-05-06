package com.SocialNetSys.NetSys.Models.Responses;

import lombok.Data;

import java.util.UUID;
@Data
public class AuthenticateResponse {
    public UUID userID;
    public String token;
}
