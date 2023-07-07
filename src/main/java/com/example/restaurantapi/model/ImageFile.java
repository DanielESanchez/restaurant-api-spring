package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("images")
public class ImageFile {
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
