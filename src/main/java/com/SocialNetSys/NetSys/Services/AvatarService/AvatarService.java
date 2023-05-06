package com.SocialNetSys.NetSys.Services.AvatarService;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.AvatarRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AvatarService implements IAvatarService {
    @Autowired
    IUserService _userService;

    public User setAvatar(AvatarRequest request, HttpServletRequest servletRequest) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        UUID id = UUID.fromString(userIdFromRequest);

        _userService.saveAvatarInDB(request.Avatar, id);

        return null;
    }
}
