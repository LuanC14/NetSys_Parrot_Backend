package com.SocialNetSys.NetSys.Services.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileUploadService implements  IFileUploadService {
    @Autowired
    private AwsService _awsService;

    public String upload(MultipartFile file, String filename) throws Exception {
        var fileUri = "";

        try {
            fileUri = _awsService.upload(file, filename);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return fileUri;
    }
}
