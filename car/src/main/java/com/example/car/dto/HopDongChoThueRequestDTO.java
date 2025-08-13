package com.example.car.dto;

import com.example.car.entities.NhanVien;
import com.example.car.entities.Oto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HopDongChoThueRequestDTO {

    @NotNull
    private Date ngayBatDau;

    @NotNull
    private Date ngayKetThuc;

//    @NotNull
//    private Date ngayThanhToan;

    @NotNull
    @Positive
    private Integer phanTramCuaDoiTac;

    private String ghiChu;

    @NotNull
    @Positive
    private Integer otoId;

    @NotNull
    private String quanLyId;

}
