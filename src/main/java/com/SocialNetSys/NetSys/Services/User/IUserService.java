package com.SocialNetSys.NetSys.Services.User;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.ChangeNameRequest;
import com.SocialNetSys.NetSys.Models.Requests.ChangePasswordRequest;
import com.SocialNetSys.NetSys.Models.Requests.UserNameRequest;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    public String createUser(UserRequest request);

    public void updateUserInDB(User user);

    public FindUserResponse findUserByUsername(String username);

    public FindUserResponse findUSerByEmail(String email);

    public FindUserResponse findUserById(UUID id);

    public User getUserByEmail(String email);

    public User getUserByID(UUID id);

    public void changePassword(ChangePasswordRequest request);

    public void nameAndUsernameModifier(ChangeNameRequest request, HttpServletRequest servletRequest);

    public void uploadPhotoProfile(MultipartFile photo, HttpServletRequest servletRequest) throws Exception;

    public List<FindUserResponse> getAllUserByNameOrUsername(String name);

}
