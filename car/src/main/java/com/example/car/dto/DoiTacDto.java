package com.example.car.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoiTacDto {

    @NotNull
    private String id;
    private Integer soChuyen;
    private Float danhGia;

    @NotNull
    private String ten;

}
