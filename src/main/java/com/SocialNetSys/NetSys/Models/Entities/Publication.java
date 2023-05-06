package com.SocialNetSys.NetSys.Models.Entities;

import com.SocialNetSys.NetSys.Models.Objects_Model.Comment_Model;
import lombok.Data;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;
@Data
public class Publication {

    public UUID id;
    public UUID user_id;
    public String contentText;
    public String contentImage;
    public Date created_at;
    public LinkedList<Comment_Model> comments;

//    public LinkedList<Likes> likes;

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

}
