package com.example.car.controllers;

import com.example.car.dto.AnhCuaXeRequestDto;
import com.example.car.entities.AnhCuaXe;
import com.example.car.entities.Oto;
import com.example.car.services.IAnhCuaXeService;
import com.example.car.services.IOtoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/image-of-car")
public class AnhCuaXeController {

    private IOtoService otoService;
    private IAnhCuaXeService anhCuaXeService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnhCuaXe(
            @PathVariable("id") Integer id,
            @Valid @RequestBody AnhCuaXeRequestDto anhCuaXeRequestDto,
            BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map((FieldError fieldError) -> {
                            return fieldError.getField() + " " + fieldError.getDefaultMessage();
                        })
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            AnhCuaXe anhCuaXe = anhCuaXeService.updateAnhCuaXe(id, anhCuaXeRequestDto);
            return ResponseEntity.ok(anhCuaXe);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //upload ảnh cho sản phẩm
    @PostMapping(value = "/uploads/{otoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable("otoId") Integer otoId,
            @ModelAttribute("file") MultipartFile file
    ){
        try {
            Oto existingOto = otoService.getOtoById(otoId);
            if(null == file || file.getSize() == 0) {
                return ResponseEntity.badRequest().body("Invalid image format");
            }
            // Kiểm tra kích thước file và định dạng
            if(file.getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");
            }
            String contentType = file.getContentType();
            if(contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image");
            }
            // Lưu file và cập nhật thumbnail trong DTO
            String filename = storeFile(file); // Thay thế hàm này với code của bạn để lưu file
            //lưu vào đối tượng product trong DB
            AnhCuaXe anhCuaXe = anhCuaXeService.createAnhCuaXe(existingOto.getId(),
                    AnhCuaXeRequestDto.builder()
                            .url(filename)
                            .build()
            );
            return ResponseEntity.ok().body(anhCuaXe);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    private String storeFile(MultipartFile file) throws IOException{
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID().toString() + "_" + fileName;

        java.nio.file.Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        java.nio.file.Path destination = Paths.get(uploadDir.toString(),uniqueFilename);
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

}
