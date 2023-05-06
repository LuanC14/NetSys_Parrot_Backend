package com.SocialNetSys.NetSys.Services.Likes;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ILikesService {

    public void setLikePublication(LikeRequest request, HttpServletRequest servletRequest, UUID post_id);
}
