package com.example.car.controllers;


import com.example.car.services.IStorageService;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class AnhController {

    private IStorageService storageService;

    @GetMapping(
            value = "/{filename}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<?> getImage(@PathVariable("filename") String filename) {
        try {
            Resource resource = storageService.loadAsResource(filename);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
