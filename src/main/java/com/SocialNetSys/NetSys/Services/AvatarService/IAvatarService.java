package com.SocialNetSys.NetSys.Services.AvatarService;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Requests.AvatarRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface IAvatarService {

    public User setAvatar(AvatarRequest request, HttpServletRequest servletRequest);
}
