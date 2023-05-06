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
    public UUID user_id;
    public String contentText;
    public String contentImage;
    public Date created_at;
    public LinkedList<Comment_Model> comments;
    public LinkedList<Like_Model> likes;

    public Publication(String contentText, String contentImage, UUID user_id) {
        setId();
        this.contentText = contentText;
        this.contentImage = contentImage;
        this.created_at = new Date(new ObjectId().getDate().getTime());
        this.user_id = user_id;
    }

    private void setId() {
        this.id = UUID.randomUUID();
    }

    public void saveComment(Comment_Model comment) {

        if (this.comments == null) {
            this.comments = new LinkedList<Comment_Model>();
        }
        this.comments.add(comment);
    }

    public void saveLike(Like_Model like) {

        if (this.likes == null) {
            this.likes = new LinkedList<Like_Model>();
        }

        this.likes.add(like);
    }
}
