package com.SocialNetSys.NetSys.Services.Likes;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ILikesService {

    public void setLikePublication( UUID userId, UUID postId);

    public void removeLikePublication(UUID userId, UUID postId);

    public boolean verifyIfUserAlreadyLikedThePost(UUID userId, UUID postId);
}
