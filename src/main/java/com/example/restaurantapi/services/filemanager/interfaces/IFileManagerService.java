package com.example.restaurantapi.services.filemanager.interfaces;

import com.example.restaurantapi.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileManagerService {
    ImageFile downloadFile(String id,  ImageFile imageFile) throws IOException;
    String addFile(MultipartFile upload) throws IOException;
}
