package com.SocialNetSys.NetSys.Services.Users.Interfaces;

import com.SocialNetSys.NetSys.Models.Requests_Models.BiographyRequest;

public interface ICreateUserBioService {
    public String createBiography(BiographyRequest request);
}
