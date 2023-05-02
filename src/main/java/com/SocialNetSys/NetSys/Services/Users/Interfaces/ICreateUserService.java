package com.SocialNetSys.NetSys.Services.Users.Interfaces;

import com.SocialNetSys.NetSys.Models.Requests_Models.UserRequest;

public interface ICreateUserService {
    public String createUser(UserRequest request);
}
