package com.SocialNetSys.NetSys.Services.Interfaces;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;

public interface IUserService {
    public String createUser(UserRequest request);

    public String createBiography(BiographyRequest request);

    public FindUserResponse findUserByEmail(String email);

    public User getUser(String email);
}
