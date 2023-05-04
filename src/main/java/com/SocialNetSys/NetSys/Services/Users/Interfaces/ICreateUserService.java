package com.SocialNetSys.NetSys.Services.Users.Interfaces;

import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;

public interface ICreateUserService {
    public String createUser(UserRequest request);

    public FindUserResponse findUserByEmail(String email);
}
