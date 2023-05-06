package com.SocialNetSys.NetSys.Services.Publications;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IPublicationService {

    public Publication createPublication(PublicationRequest request, HttpServletRequest servlet);

    public void createComment(UUID publi_id, Comment_Model comment);

    public void setLike(Like_Model like);
}
