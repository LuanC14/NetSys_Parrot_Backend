package com.SocialNetSys.NetSys.Models.Responses;

import com.SocialNetSys.NetSys.Models.Objects.Comment_Model;
import com.SocialNetSys.NetSys.Models.Objects.Like_Model;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;
@Data
public class PublicationResponse {
    public UUID postId;
    public UUID authorId;
    public String contentText;
    public String contentImage;
    public Date createdAt;

    public LinkedList<Comment_Model> comments;

    public LinkedList<Like_Model> likes;
}
