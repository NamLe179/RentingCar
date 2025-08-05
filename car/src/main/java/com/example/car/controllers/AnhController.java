package com.example.car.controllers;


import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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


    @GetMapping(
            value = "/{filename}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<?> getImage(@PathVariable("filename") String filename) {
        try {
            Path uploadDir = Paths.get("uploads");
            Path destination = Paths.get(uploadDir.toString(), filename);
            final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(destination));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(inputStream.contentLength())
                    .body(inputStream);
        } catch (NoSuchFileException e) {
            return ResponseEntity.badRequest().body("file khong ton tai");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
