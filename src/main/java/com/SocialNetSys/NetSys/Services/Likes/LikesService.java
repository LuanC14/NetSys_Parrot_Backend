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
    private IPublicationService _publicationService;

    @Autowired
    private IUserService _userService;

    public void setLikePublication(HttpServletRequest servletRequest, UUID postId) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var userId = UUID.fromString(userIdFromRequest);

        var publication = _publicationService.findPublicationById(postId);

        if(publication == null) {
            throw new RuntimeException("Publicação não encontrada");
        }

        if(verifyIfUserAlreadyLikedThePost(userId, postId)) {
            throw new RuntimeException("Você já curtiu essa publicação, utilize o serviço de unlike");
        }

        var userLiked = _userService.getUserByID(userId).getName();

        var like = new Like_Model(userLiked, userId, postId);

            publication.saveLike(like);

        _publicationService.updatePublicationInDB(publication);
    }

    public void removeLikePublication(HttpServletRequest servletRequest, UUID postId) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var userId = UUID.fromString(userIdFromRequest);

        if(!verifyIfUserAlreadyLikedThePost(userId, postId)) {
            throw new RuntimeException("Você não curtiu essa publicação, utilize o serviço de like");
        }

        try{
            var publication = _publicationService.findPublicationById(postId);

            if(publication == null) {
                throw  new RuntimeException("Publicação não encontrada");
            }
            publication.removeLike(userId);
            _publicationService.updatePublicationInDB(publication);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public boolean verifyIfUserAlreadyLikedThePost(UUID userId, UUID postId) {
        var publication = _publicationService.findPublicationById(postId);

        if(publication != null) {

            var likes = publication.likes;

            for(Like_Model like : likes) {
                if(like.getUserId().equals(userId)) {
                    return true;
                }
            }

        } else  {
            throw new RuntimeException("Não foi possível encontrar o usuário, verifique o ID da publicação");
        }

        return false;
    }
}
