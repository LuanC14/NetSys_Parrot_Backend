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
    public String photoProfileUri;
    public String nameAuthor;
    public String username;

    public Comment_Model(String content, UUID postId, UUID authorId, String photoProfileUri, String nameAuthor, String username) {
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
        this.photoProfileUri = photoProfileUri;
        this.nameAuthor = nameAuthor;
        this.username = username;
        setId();
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }
}
