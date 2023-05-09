package com.SocialNetSys.NetSys.Services.Likes;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ILikesService {

    public void setLikePublication(HttpServletRequest servletRequest, UUID post_id);

    public void removeLikePublication(HttpServletRequest servletRequest, UUID post_id);

    public boolean verifyIfUserAlreadyLikedThePost(UUID userId, UUID postId);
}
