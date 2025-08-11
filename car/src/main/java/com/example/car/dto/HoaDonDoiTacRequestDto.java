package com.example.car.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class HoaDonDoiTacRequestDto {

    @NotNull
    private Integer hopDongChoThueId;

    @Positive
    private Float tongTien; // Tổng tiền thanh toán


    @NotNull
    private String phuongThucThanhToan; // Tiền mặt, Chuyển khoản, Thẻ tín dụng
    private String ghiChu; // Thông tin bổ sung về thanh toán

    @NotNull
    private String nhanVienId; // Nhân viên xử lý thanh toán
    @NotNull
    private LocalDateTime ngayBatDau;
    @NotNull
    private LocalDateTime ngayKetThuc;

}
