package com.SocialNetSys.NetSys.Services.AvatarService;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.AvatarRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAvatarService {

    public User setAvatar(AvatarRequest request, HttpServletRequest servletRequest);
}
