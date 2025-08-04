package com.example.car.dto;

import com.example.car.entities.NhanVien;
import com.example.car.entities.TaiSanCamCo;
import lombok.Data;

@Data
public class TaiSanCamCoCanTraRequestDTO {
    private int taiSanCamCoId;
    private String nhanVienTraId;
}
