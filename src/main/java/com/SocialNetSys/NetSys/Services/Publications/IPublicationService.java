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

    public void updatePublicationInDB(Publication publication);

    public String imageForPublication(MultipartFile photo, Publication publication) throws Exception;

    public Publication findPublicationById(UUID postId);

    public void deletePublication(UUID postId, HttpServletRequest servletRequest);

    public List<PublicationResponse> findAllPublications(UUID user_id);

}
