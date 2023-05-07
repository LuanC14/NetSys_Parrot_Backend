package com.SocialNetSys.NetSys.Services.Likes;

import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikesService implements ILikesService {
    @Autowired
    IPublicationService _publicationService;

    @Autowired
    IUserService _userService;

    public void setLikePublication(HttpServletRequest servletRequest, UUID postId) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var userId = UUID.fromString(userIdFromRequest);

        if(_publicationService.verifyIfUserAlreadyLiked(userId, postId)) {
            throw new IllegalArgumentException("Você já curtiu essa publicação, utilize o serviço de unlike");
        }

        var userLiked = _userService.getUserByID(userId).getName();

        var like = new Like_Model(userLiked, userId, postId);

            _publicationService.setLike(like);
    }

    public void removeLikePublication(HttpServletRequest servletRequest, UUID postId) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var userId = UUID.fromString(userIdFromRequest);

        if(!_publicationService.verifyIfUserAlreadyLiked(userId, postId)) {
            throw new IllegalArgumentException("Você não curtiu essa publicação, utilize o serviço de like");
        }
        _publicationService.unlike(userId, postId);
    }
}