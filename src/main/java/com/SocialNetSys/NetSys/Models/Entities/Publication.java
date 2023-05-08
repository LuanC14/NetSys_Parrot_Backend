package com.SocialNetSys.NetSys.Models.Entities;

import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;
@Data
public class Publication {
    @Id
    public UUID id;
    public UUID userId;
    public String nameAuthor;
    public String contentText;
    public String contentImage;
    public Date created_at;
    public LinkedList<Comment_Model> comments;
    public LinkedList<Like_Model> likes;

    public Publication(String nameAuthor, String contentText,  UUID userId) {
        setId();
        this.nameAuthor = nameAuthor;
        this.contentText = contentText;
        this.created_at = new Date(new ObjectId().getDate().getTime());
        this.userId = userId;

        if (this.likes == null) {
            this.likes = new LinkedList<Like_Model>();
        }

        if (this.comments == null) {
            this.comments = new LinkedList<Comment_Model>();
        }
    }

    private void setId() {
        this.id = UUID.randomUUID();
    }

    public void saveComment(Comment_Model comment) {
        this.comments.add(comment);
    }

    public void saveLike(Like_Model like) {
        this.likes.add(like);
    }

    public void removeLike(UUID userId) {
        likes.removeIf(like -> like.getUserId().equals(userId));
    }
}
