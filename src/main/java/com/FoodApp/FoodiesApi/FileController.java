package com.FoodApp.FoodiesApi;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final SupabaseStorageService service;

    public FileController(SupabaseStorageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        return service.uploadFile(file);
    }
}