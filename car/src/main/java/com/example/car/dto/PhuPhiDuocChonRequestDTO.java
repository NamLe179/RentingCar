package com.example.car.dto;

import com.example.car.entities.HoaDon;
import com.example.car.entities.PhuPhi;
import lombok.Data;

@Data
public class PhuPhiDuocChonRequestDTO {
    private int phuPhiId;
    private int hoaDonId;
    private int soLuong;
    private int soGio;
}
