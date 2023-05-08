package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IPublicationService {

    public Publication createPublication(String title, MultipartFile photo, HttpServletRequest servlet) throws Exception;

    public String imageForPublication(MultipartFile photo, Publication publication) throws Exception;

    public Publication findPublicationById(UUID postId);

    public void deletePublication(UUID postId);

    public List<PublicationResponse> findPublications(UUID user_id);

    public void saveNewComment(UUID publi_id, Comment_Model comment);

    public void saveWithoutCommentDeleted(Publication publicationWithoutComment);

    public void saveWithNewLike(Like_Model like);

    public void saveWithoutRemovedLike(UUID user_id, UUID post_id);

    public boolean verifyIfUserAlreadyLiked(UUID userId, UUID postId);
}
