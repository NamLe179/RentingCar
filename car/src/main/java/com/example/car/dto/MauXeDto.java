package com.example.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauXeDto {
    private String id;
    private String tenMauXe;
    private String tenHangXe;
}
