package com.example.car.dto;

import com.example.car.entities.HoaDon;
import com.example.car.entities.PhuPhi;
import lombok.Data;

@Data
public class PhuPhiDuocChonRequestDTO {
    private PhuPhi phuPhi;
    private HoaDon hoaDon;
}
