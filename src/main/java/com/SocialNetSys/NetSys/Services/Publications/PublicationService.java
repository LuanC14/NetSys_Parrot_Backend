package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Repositories.PublicationRepository;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PublicationService implements IPublicationService {
    @Autowired
    PublicationRepository _publicationRepository;
    @Autowired
    IUserService _userService;

    public Publication createPublication(PublicationRequest request, HttpServletRequest servletRequest) {

         var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

         var userId = UUID.fromString(userIdFromRequest);

         var nameAuthor = _userService.getUserByID(userId).getName();

        var publication = new Publication(nameAuthor, request.contentText, request.urlImage, userId);

        _publicationRepository.save(publication);

        return publication;
    }

    public List<PublicationResponse> findPublications(UUID userId) {

        var publications = _publicationRepository.findAllByUserId(userId);

        if(!publications.isEmpty()) {
            List<PublicationResponse> allPublicationsResponse = new ArrayList<>();

            for(Publication publication : publications) {
                PublicationResponse response = new PublicationResponse();

                response.setPostId(publication.getId());
                response.setAuthorId(publication.getUserId());
                response.setNameAuthor(publication.getNameAuthor());
                response.setContentText(publication.getContentText());
                response.setContentImage(publication.getContentImage());
                response.setCreatedAt(publication.getCreated_at());
                response.setComments(publication.getComments());
                response.setLikes(publication.getLikes());

                allPublicationsResponse.add(response);
            }

            return allPublicationsResponse;
        } else {
            throw new IllegalArgumentException("Publicações não encontradas");
        }
    }

    public void createComment(UUID postId, Comment_Model comment) {
        var publication = _publicationRepository.findById(postId).get();

        publication.saveComment(comment);

        _publicationRepository.save(publication);
    }

   public void setLike(Like_Model like) {
        var postId = like.getPostId();
        var publication = _publicationRepository.findById(postId).get();

        publication.saveLike(like);

       _publicationRepository.save(publication);
   }

   public boolean verifyIfUserAlreadyLiked(UUID userId, UUID postId) {
       var publication = _publicationRepository.findById(postId).get();

       var likes = publication.likes;

       for(Like_Model like : likes) {
           if(like.getUserId().equals(userId)) {
               return true;
           }
       }
       return false;
   }

   public void unlike(UUID userId, UUID postId) {
       var post = _publicationRepository.findById(postId).get();

       post.removeLike(userId);

       _publicationRepository.save(post);
   }
}
