package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Repositories.PublicationRepository;
import com.SocialNetSys.NetSys.Services.FileUpload.IFileUploadService;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PublicationService implements IPublicationService {
    @Autowired
    private PublicationRepository _publicationRepository;
    @Autowired
    private IUserService _userService;

    @Autowired
    private IFileUploadService _fileUploadService;

    public Publication createPublication(String title, MultipartFile photo, HttpServletRequest servletRequest) throws Exception {

         var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

         var userId = UUID.fromString(userIdFromRequest);

         var nameAuthor = _userService.getUserByID(userId).getName();

        var publication = new Publication(nameAuthor, title, userId);

        if(photo != null) {
           var photoUri = imageForPublication(photo, publication);
           publication.setContentImage(photoUri);
        }

        _publicationRepository.save(publication);

        return publication;
    }

    public String imageForPublication(MultipartFile photo, Publication publication) throws Exception {

    var photoUri = "";

    try {

    var filename = publication.getId() +"."+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1);

    photoUri = _fileUploadService.upload(photo, filename);

    } catch( Exception e) {
        throw new Exception(e.getMessage());
    }
        return photoUri;
    }

    public void deletePublication(UUID postId) {
        _publicationRepository.deleteById(postId);
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

    public Publication findPublicationById(UUID postId) {
        return _publicationRepository.findById(postId).get();
    }

    // For External Services

    public void saveNewComment(UUID postId, Comment_Model comment) {
        var publication = _publicationRepository.findById(postId).get();

        publication.saveComment(comment);

        _publicationRepository.save(publication);
    }

    public void saveWithoutCommentDeleted(Publication publicationWithoutComment) {
        _publicationRepository.save(publicationWithoutComment);
    }

   public void saveWithNewLike(Like_Model like) {
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

   public void saveWithoutRemovedLike(UUID userId, UUID postId) {
       var post = _publicationRepository.findById(postId).get();

       post.removeLike(userId);

       _publicationRepository.save(post);
   }
}
