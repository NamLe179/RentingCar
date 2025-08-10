package com.example.car.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnhCuaXeRequestDto {

    private MultipartFile file;

    @NotNull
    private Boolean giayToXe;

    @NotNull
    private Boolean thumnail;
    private String ghiChu;
}
