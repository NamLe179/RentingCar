package com.example.car.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HangXeDto {
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String ten;
    private String moTa;
}
