package com.example.car.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class HoaDonRequestDTO {

    @NotNull
    private Integer hopDongThueId;

    @Positive
    private Float tongTien; // Tổng tiền thanh toán

    @NotNull
    private Date ngayThanhToan;

    @NotNull
    private String phuongThucThanhToan; // Tiền mặt, Chuyển khoản, Thẻ tín dụng
    private String ghiChu; // Thông tin bổ sung về thanh toán

    @NotNull
    private String nhanVienId; // Nhân viên xử lý thanh toán
}
