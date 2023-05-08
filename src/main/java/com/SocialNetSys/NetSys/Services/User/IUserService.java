package com.SocialNetSys.NetSys.Services.User;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.ChangeNameRequest;
import com.SocialNetSys.NetSys.Models.Requests.ChangePasswordRequest;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IUserService {
    public String createUser(UserRequest request);

    public FindUserResponse findUserByUsername(String username);

    public FindUserResponse findUSerByEmail(String email);

    public User getUserByEmail(String email);

    public User getUserByID(UUID id);

    public void saveBiographyInDB(UUID id, Biography_Model bio);

    public FollowerResponse followManager(UUID myId, UUID userFollowedId);

    public void changePassword(ChangePasswordRequest request);

    public void changeName(ChangeNameRequest request, HttpServletRequest servletRequest);

    public FollowerResponse unfollowManager(UUID myId, UUID userFollowedId);

    public void uploadPhotoProfile(MultipartFile photo, HttpServletRequest servletRequest) throws  Exception;

}
