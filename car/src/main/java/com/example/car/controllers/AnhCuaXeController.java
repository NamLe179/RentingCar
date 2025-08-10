package com.example.car.controllers;

import com.example.car.dto.AnhCuaXeRequestDto;
import com.example.car.entities.AnhCuaXe;
import com.example.car.entities.Oto;
import com.example.car.services.IAnhCuaXeService;
import com.example.car.services.IOtoService;
import com.example.car.services.IStorageService;
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
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("anhCuaXeRequestDto") AnhCuaXeRequestDto anhCuaXeRequestDto,
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
            anhCuaXeRequestDto.setFile(file);
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
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("anhCuaXeRequestDto") AnhCuaXeRequestDto anhCuaXeRequestDto,
            BindingResult result
    ){
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

            anhCuaXeRequestDto.setFile(file);

            if(null == anhCuaXeRequestDto.getFile() || anhCuaXeRequestDto.getFile().getSize() == 0) {
                return ResponseEntity.badRequest().body("Invalid image format");
            }
            // Kiểm tra kích thước file và định dạng
            if(anhCuaXeRequestDto.getFile().getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");
            }
            String contentType = anhCuaXeRequestDto.getFile().getContentType();
            if(contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image");
            }
            //lưu vào đối tượng product trong DB
            AnhCuaXe anhCuaXe = anhCuaXeService.createAnhCuaXe(otoId, anhCuaXeRequestDto);
            return ResponseEntity.ok().body(anhCuaXe);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
