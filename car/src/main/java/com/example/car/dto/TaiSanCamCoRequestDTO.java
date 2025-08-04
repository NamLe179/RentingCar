package com.example.car.dto;

import com.example.car.entities.HopDongThue;
import com.example.car.entities.KhachHang;
import com.example.car.entities.NhanVien;
import lombok.Data;

import java.util.Date;

@Data
public class TaiSanCamCoRequestDTO {
    private String tenTaiSan;
    private String loaiTaiSan;
    private float giaTriTaiSan;
    private String moTa;
    private Date thoiGianNhan;
    private String khachHangId; // Khách hàng sở hữu tài sản
    private int hopDongThueId; // Hợp đồng thuê liên quan đến tài sản này
    private String nhanVienNhanId; // Nhân viên nhận tài sản
}
