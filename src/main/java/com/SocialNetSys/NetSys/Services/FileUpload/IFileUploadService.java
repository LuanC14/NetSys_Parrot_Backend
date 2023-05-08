package com.SocialNetSys.NetSys.Services.FileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
    public String upload(MultipartFile file, String filename) throws Exception;

}
