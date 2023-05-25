package com.SocialNetSys.NetSys.Models.Requests;
import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import lombok.Data;

import java.util.LinkedList;
@Data
public class BiographyRequest {
    public String type;
    public String value;
}