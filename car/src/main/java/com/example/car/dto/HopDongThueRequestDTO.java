package com.example.car.dto;

import com.example.car.entities.KhachHang;
import com.example.car.entities.Oto;
import lombok.Data;

import java.util.Date;

@Data
public class HopDongThueRequestDTO {
    private String otoId;
    private String khachHangId;
    private Date thoiGianNhan;
    private Date thoiGianTra;
    private String moTa;
    private float giaThue;
}
