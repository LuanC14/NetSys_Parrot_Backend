package com.SocialNetSys.NetSys.Services.Biography;

import com.SocialNetSys.NetSys.Models.Objects_Model.Biography;
import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IBiographyService {

    public void createBiography(BiographyRequest request, HttpServletRequest servletRequest);
}
