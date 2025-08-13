package com.example.car.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchingHoaDonDoiTacDto {
    private String doiTacId;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String phuongThucThanhToan; // Tiền mặt, Chuyển khoản, Thẻ tín dụng
//    private String nhanVienId; // Nhân viên xử lý thanh toán

}
