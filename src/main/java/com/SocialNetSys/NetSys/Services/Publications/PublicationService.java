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

        if(photo == null && title == null) {
            return null;
        }

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

    photoUri = _fileUploadService.upload(photo, filename);

    } catch( Exception e) {
        throw new Exception(e.getMessage());
    }
        return photoUri;
    }

    public void deletePublication(UUID postId, HttpServletRequest servletRequest) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var userId = UUID.fromString(userIdFromRequest);

        var optionalPublication = _publicationRepository.findById(postId);

        if(optionalPublication.isPresent()) {
            var publication = optionalPublication.get();

            if(!publication.getUserId().equals(userId)) {
                throw  new RuntimeException("Você não pode excluir a publicação de outro usuário");
            }

            _publicationRepository.deleteById(postId);

        } else {
            throw  new RuntimeException("Publicação não encontrada");
        }
    }

    public List<PublicationResponse> findAllPublications(UUID userId) {

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

    public void updatePublicationInDB(Publication publication) {
        _publicationRepository.save(publication);
    }

}
