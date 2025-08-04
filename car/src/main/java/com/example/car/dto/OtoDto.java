package com.example.car.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer id;

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

    private Float gia;
    private String moTa;

    @NotNull
    @JsonProperty("doiTac")
    private DoiTacDto doiTacDto;

    @NotNull
    @JsonProperty("diaChi")
    private DiaChiDto diaChiDto;
    private Integer soGhe;
    private Integer soChuyen;
    private Float danhGia;
    private String thumnail;

    @JsonProperty("tienNghi")
    private List<TienNghiDto> tienNghiDtoList;
    private List<DanhGiaDto> danhGiaDtoList;

    @NotNull
    @JsonProperty("mauXe")
    private MauXeDto mauXeDto;
    private List<AnhCuaXeDto> anhCuaXeDtoList;
}
