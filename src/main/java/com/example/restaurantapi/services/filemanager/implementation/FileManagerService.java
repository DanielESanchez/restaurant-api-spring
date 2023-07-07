package com.example.restaurantapi.services.filemanager.implementation;

import com.example.restaurantapi.model.ImageFile;
import com.example.restaurantapi.services.filemanager.interfaces.IFileManagerService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class FileManagerService implements IFileManagerService {

    private final GridFsTemplate template;
    private final GridFsOperations operations;

    public FileManagerService(GridFsTemplate template, GridFsOperations operations) {
        this.template = template;
        this.operations = operations;
    }

    @Override
    public ImageFile downloadFile(String id, ImageFile imageFile) throws IOException {
        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );
        if(gridFSFile == null || gridFSFile.getMetadata() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Image not found.");
        }
        imageFile.setFilename( gridFSFile.getFilename() );
        imageFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );
        imageFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );
        imageFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        return imageFile;
    }

    @Override
    public String addFile(MultipartFile upload) throws IOException{
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());
        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        return fileID.toString();
    }
}
