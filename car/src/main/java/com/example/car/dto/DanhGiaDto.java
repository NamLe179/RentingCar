package com.example.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DanhGiaDto {
    private String idKhachHang;
    private String binhLuan;
    private Float danhGia;
    private String tenKhachHang;
    private String ngayDanhGia;
}
