package com.SocialNetSys.NetSys.Services.Comment;

import com.SocialNetSys.NetSys.Models.Objects_Model.CommentRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.Comment_Model;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService implements ICommentService {
    @Autowired
    IPublicationService _publicationService;

    public void handleComment(CommentRequest request, HttpServletRequest servletRequest, UUID post_id) {

        var authorIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var author_id = UUID.fromString(authorIdFromRequest);

        var comment = new Comment_Model(request.content, post_id, author_id);

        _publicationService.createComment(post_id, comment);
    }
}
