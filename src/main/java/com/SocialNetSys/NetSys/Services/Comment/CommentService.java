package com.SocialNetSys.NetSys.Services.Comment;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private IPublicationService _publicationService;

    @Autowired
    private IUserService _userService;

    public void createComment(CommentRequest request, HttpServletRequest servletRequest, UUID postId) throws Exception {

        var authorIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var author_id = UUID.fromString(authorIdFromRequest);
        var publication = _publicationService.findPublicationById(postId);

        var author = _userService.getUserByID(author_id);

        try {
            var comment = new Comment_Model(request.content, postId, author_id, author.getAvatar(), author.getName(), author.getUsername());
            publication.saveComment(comment);
            _publicationService.updatePublicationInDB(publication);

        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }

    public Publication removeComment(UUID postId, HttpServletRequest servletRequest , UUID commentId) throws Exception {
        var publication = _publicationService.findPublicationById(postId);

        if(publication == null) {
            throw new RuntimeException("Publicação não encontrada");
        }

        var comments = publication.getComments();
        var authorCommentIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var authorCommentId = UUID.fromString(authorCommentIdFromRequest);

        try{
            for(Comment_Model comment : comments) {
                if(comment.getAuthorId().equals(authorCommentId) && comment.getId().equals(commentId)) {
                    comments.remove(comment);
                    publication.setComments(comments);
                    break;
                }
            }

            _publicationService.updatePublicationInDB(publication);

            return publication;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
