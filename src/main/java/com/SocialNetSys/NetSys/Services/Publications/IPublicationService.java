package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IPublicationService {

    public Publication createPublication(PublicationRequest request, HttpServletRequest servlet);

    public List<PublicationResponse> findPublications(UUID user_id);

    public void createComment(UUID publi_id, Comment_Model comment);

    public void setLike(Like_Model like);

    public void unlike(UUID user_id, UUID post_id);

//    public Like_Model findLikeById(UUID user_id, UUID post_id);
}
