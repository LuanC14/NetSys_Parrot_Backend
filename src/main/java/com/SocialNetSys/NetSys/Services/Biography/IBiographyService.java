package com.SocialNetSys.NetSys.Services.Biography;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;

import jakarta.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.UUID;

public interface IBiographyService {

    public LinkedList<Biography_Model> createBiography(BiographyRequest request, HttpServletRequest servletRequest);

    public LinkedList<Biography_Model>  updateBiography(UUID itemBioId, Biography_Model request, HttpServletRequest servletRequest);

    public void deleteBiography(UUID itemBioId, HttpServletRequest servletRequest);
}
