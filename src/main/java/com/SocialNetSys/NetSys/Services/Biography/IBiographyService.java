package com.SocialNetSys.NetSys.Services.Biography;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IBiographyService {

    public void createBiography(BiographyRequest request, HttpServletRequest servletRequest);

    public void updateBiography(UUID biographyId, Biography_Model request, HttpServletRequest servletRequest);
}
