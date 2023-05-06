package com.SocialNetSys.NetSys.Services.Biography;

import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface IBiographyService {

    public void createBiography(BiographyRequest request, HttpServletRequest servletRequest);
}
