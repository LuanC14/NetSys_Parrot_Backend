package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Repositories.PublicationRepository;
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

    public Publication createPublication(PublicationRequest request, HttpServletRequest servletRequest) {

         var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

         var userId = UUID.fromString(userIdFromRequest);

        var publication = new Publication(request.contentText, request.urlImage, userId);

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

//   public Like_Model findLikeById(UUID userId, UUID postId) {
//       var post = _publicationRepository.findById(postId).get();
//
//       var likes = post.likes;
//
//       for(Like_Model like : likes) {
//           if(like.getUser_id().equals(userId)) {
//               return like;
//           }
//       }
//       return null;
//   }

   public void unlike(UUID userId, UUID postId) {
       var post = _publicationRepository.findById(postId).get();

       post.removeLike(userId);

       _publicationRepository.save(post);
   }
}
