package com.example.car.dto;

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
public class OtoRequestDto {
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
    @JsonProperty("doiTacId")
    private String doiTacId;

    @NotNull
    @JsonProperty("diaChi")
    private DiaChiRequestDto diaChi;

    @JsonProperty("tienNghi")
    private List<Integer> tienNghiList;

    @NotNull
    @JsonProperty("mauXeId")
    private Integer mauXeId;
}
