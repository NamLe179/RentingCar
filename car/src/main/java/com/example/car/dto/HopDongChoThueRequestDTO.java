package com.example.car.dto;

import com.example.car.entities.NhanVien;
import com.example.car.entities.Oto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private String ghiChu;

    @Positive
    private Float gia;

    @NotNull
    @Positive
    private Integer otoId;

    @NotNull
    private String nhanVienId;

}
