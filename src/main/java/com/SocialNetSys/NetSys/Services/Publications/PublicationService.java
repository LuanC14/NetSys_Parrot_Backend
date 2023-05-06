package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Repositories.PublicationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublicationService implements IPublicationService {
    @Autowired
    PublicationRepository _publicationRepository;
    public Publication createPublication(PublicationRequest request, HttpServletRequest servletRequest) {

         var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

         var user_id = UUID.fromString(userIdFromRequest);

        var publication = new Publication(request.contentText, request.urlImage, user_id);

        _publicationRepository.save(publication);

        return publication;
    }

    public void createComment(UUID post_id, Comment_Model comment) {
        var publication = _publicationRepository.findById(post_id).get();

        publication.saveComment(comment);

        _publicationRepository.save(publication);
    }

   public void setLike(Like_Model like) {
        var post_id = like.getPost_id();
        var publication = _publicationRepository.findById(post_id).get();

        publication.saveLike(like);

       _publicationRepository.save(publication);
   }
}
