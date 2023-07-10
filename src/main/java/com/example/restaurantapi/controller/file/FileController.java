package com.example.restaurantapi.controller.file;

import com.example.restaurantapi.dao.response.ResponseOk;
import com.example.restaurantapi.model.ImageFile;
import com.example.restaurantapi.services.filemanager.implementation.FileManagerService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("api/file")
public class FileController {

    private final FileManagerService fileService;

    public FileController(FileManagerService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseOk> upload(@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(
                ResponseOk
                        .builder()
                        .response(fileService.addFile(file))
                        .build());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        ImageFile emptyFile =  new ImageFile();
        ImageFile imageFile = fileService.downloadFile(id, emptyFile);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.getFilename() + "\"")
                .body(new ByteArrayResource(imageFile.getFile()));
    }

}