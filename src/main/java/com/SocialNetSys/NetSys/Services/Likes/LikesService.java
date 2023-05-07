package com.SocialNetSys.NetSys.Services.Likes;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikesService implements ILikesService {
    @Autowired
    IPublicationService _publicationService;

    public void setLikePublication(LikeRequest request,HttpServletRequest servletRequest, UUID post_id) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var user_id = UUID.fromString(userIdFromRequest);

        var like = new Like_Model(request.name, user_id, post_id);

//        var checkUserAlreadyLikedPost = _publicationService.findLikeById(user_id, post_id);
//
//        if(checkUserAlreadyLikedPost != null) {
//            throw new Error("Publicação já curtida, o client redirecionará para o serviço de descurtir");
//
//        }
            _publicationService.setLike(like);

    }

    public void removeLikePublication(HttpServletRequest servletRequest, UUID post_id) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var user_id = UUID.fromString(userIdFromRequest);
//
//        var checkUserAlreadyLikedPost = _publicationService.findLikeById(user_id, post_id);
//
//        if(checkUserAlreadyLikedPost == null) {
//            throw new Error("Você não curtiu esta publicação, o client lhe redirecionará para o serviço de curtir");
//        }

        _publicationService.unlike(user_id, post_id);
    }
}
