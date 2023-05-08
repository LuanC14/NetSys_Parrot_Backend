package com.SocialNetSys.NetSys.Services.Comment;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private IPublicationService _publicationService;

    public void createComment(CommentRequest request, HttpServletRequest servletRequest, UUID post_id) {

        var authorIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var author_id = UUID.fromString(authorIdFromRequest);

        var comment = new Comment_Model(request.content, post_id, author_id);

        _publicationService.saveNewComment(post_id, comment);
    }

    public Publication removeComment(UUID postId, HttpServletRequest servletRequest , UUID commentId) {
        var publication = _publicationService.findPublicationById(postId);
        var comments = publication.getComments();

        var authorCommentIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var authorCommentId = UUID.fromString(authorCommentIdFromRequest);

        for(Comment_Model comment : comments) {
            if(comment.getAuthorId().equals(authorCommentId) && comment.getId().equals(commentId)) {
                comments.remove(comment);
                publication.setComments(comments);
                break;
            }
        }

        _publicationService.saveWithoutCommentDeleted(publication);

        return publication;
    }
}
