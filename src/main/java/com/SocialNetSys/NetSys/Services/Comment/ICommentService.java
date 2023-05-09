package com.SocialNetSys.NetSys.Services.Comment;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ICommentService {

    public void createComment(CommentRequest request, HttpServletRequest servletRequest, UUID post_id) throws Exception;

    public Publication removeComment(UUID postId, HttpServletRequest servletRequest , UUID commentId) throws Exception;
}
