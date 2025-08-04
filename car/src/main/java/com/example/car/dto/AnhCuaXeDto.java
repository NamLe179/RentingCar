package com.example.car.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnhCuaXeDto {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(max = 200)
    private String url;

    @NotNull
    private Boolean giayToXe;

    @NotNull
    private Boolean thumnail;
    private String ghiChu;
}
