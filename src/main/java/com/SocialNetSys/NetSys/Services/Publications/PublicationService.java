package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Providers.FilenameCreator;
import com.SocialNetSys.NetSys.Repositories.PublicationRepository;
import com.SocialNetSys.NetSys.Services.FileUpload.IFileUploadService;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

         if(nameAuthor == null) {
             throw new RuntimeException("Usuário não autenticado");
         }

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
    var filename = FilenameCreator.nameGenerator(publication.getId(), Objects.requireNonNull(photo.getOriginalFilename()));
//    var filename = publication.getId() +"."+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1);

    photoUri = _fileUploadService.upload(photo, filename);

    } catch( Exception e) {
        throw new Exception(e.getMessage());
    }
        return photoUri;
    }

    public void deletePublication(UUID postId) {

        var optionalPublication = _publicationRepository.findById(postId);

        if(optionalPublication.isPresent()) {
            _publicationRepository.deleteById(postId);
        } else {
            throw  new RuntimeException("Publicação não encontrada");
        }
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

        var optionalPublication = _publicationRepository.findById(postId);

        if(optionalPublication.isPresent()) {
            return optionalPublication.get();
        } else {
            throw  new RuntimeException("Publicação não encontrada ou ID inválido");
        }
    }

    // For External Services

    public void saveNewComment(UUID postId, Comment_Model comment) {
        var optionalPublication = _publicationRepository.findById(postId);

        if(optionalPublication.isPresent()) {
            var publication = optionalPublication.get();
            publication.saveComment(comment);
            _publicationRepository.save(publication);

        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public void saveWithoutCommentDeleted(Publication publicationWithoutComment) {
        _publicationRepository.save(publicationWithoutComment);
    }

   public void saveWithNewLike(Like_Model like) {
        var postId = like.getPostId();
        var optionalPublication = _publicationRepository.findById(postId);

        if(optionalPublication.isPresent()) {
            var publication = optionalPublication.get();
            publication.saveLike(like);
            _publicationRepository.save(publication);

        } else {
            throw new RuntimeException("Publicação não encontrada");
        }
   }

   public boolean verifyIfUserAlreadyLiked(UUID userId, UUID postId) {
       var optionalPublication = _publicationRepository.findById(postId);

       if(optionalPublication.isPresent()) {
           var publication = optionalPublication.get();
           var likes = publication.likes;

           for(Like_Model like : likes) {
               if(like.getUserId().equals(userId)) {
                   return true;
               }
           }
           return false;

       } else  {
           throw new RuntimeException("Não foi possível encontrar o usuário, verifique o ID da publicação");
       }
   }

   public void saveWithoutRemovedLike(UUID userId, UUID postId) {
       var optionalPublication = _publicationRepository.findById(postId);

       if(optionalPublication.isPresent()) {
           var publication = optionalPublication.get();
           publication.removeLike(userId);
           _publicationRepository.save(publication);
       } else {
           throw new RuntimeException("Não foi possível encontrar o usuário, verifique o ID da publicação");
       }

   }
}
