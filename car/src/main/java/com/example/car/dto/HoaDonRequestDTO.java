package com.example.car.dto;

import com.example.car.entities.HopDongThue;
import com.example.car.entities.NhanVien;
import lombok.Data;

import java.util.Date;

@Data
public class HoaDonRequestDTO {
    private int hopDongThueId;
    private float tongTien; // Tổng tiền thanh toán
    private Date ngayThanhToan;
    private String phuongThucThanhToan; // Tiền mặt, Chuyển khoản, Thẻ tín dụng
    private String ghiChu; // Thông tin bổ sung về thanh toán
    private String nhanVienId; // Nhân viên xử lý thanh toán
}
