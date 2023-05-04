package com.SocialNetSys.NetSys.Models.Objects_Model;
import lombok.Data;

import java.util.LinkedList;
@Data
public class BiographyRequest {
    public LinkedList<Biography> biography;

    public String getUserId() {

        return biography.getFirst().user_id;
    }
}