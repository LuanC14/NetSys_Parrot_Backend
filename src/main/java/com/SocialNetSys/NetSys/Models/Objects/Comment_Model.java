package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Data
public class Comment_Model {
    @Id
    public  UUID id;
    public UUID authorId;
    public UUID postId;
    public String content;

    public Comment_Model(String content, UUID postId, UUID authorId) {
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
        setId();
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }
}
