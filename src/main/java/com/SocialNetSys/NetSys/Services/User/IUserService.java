package com.SocialNetSys.NetSys.Services.User;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;

import java.util.UUID;

public interface IUserService {
    public String createUser(UserRequest request);

    public FindUserResponse responseUserByEmail(String email);

    public User getUserByEmail(String email);

    public User getUserByID(UUID id);

    public void saveBiographyInDB(UUID id, Biography_Model bio);

    public void saveAvatarInDB(String avatar, UUID userId);
}
