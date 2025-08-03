package com.example.car.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtoDto {
    private String id;

    @NotNull
    @Positive
    private Integer namSanXuat;

    @NotBlank()
    @Size(max = 50)
    private String truyenDong;

    @NotBlank
    @Size(max = 50)
    private String loaiNhienLieu;

    @NotNull
    @Positive
    private Integer mucTieuThu;

    @NotBlank
    @Size(max = 25)
    private String bienSo;

    @NotNull
    @Positive
    private Float gia;
    private String moTa;

    @NotNull
    private DoiTacDto doiTacDto;

    @NotNull
    private DiaChiDto diaChiDto;
    private Integer soGhe;
    private Integer soChuyen;
    private Float danhGia;
    private String thumnail;
    private List<TienNghiDto> tienNghiDtoList;
    private List<DanhGiaDto> danhGiaDtoList;
    private MauXeDto mauXeDto;
    private List<AnhCuaXeDto> anhCuaXeDtoList;
}
