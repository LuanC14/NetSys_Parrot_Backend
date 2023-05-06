package com.SocialNetSys.NetSys.Services.Comment;

import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ICommentService {

    public void handleComment(CommentRequest request, HttpServletRequest servletRequest, UUID post_id);
}
