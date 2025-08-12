package com.example.car.dto;

import com.example.car.enums.OtoStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchingOtoDto {
    //ngày bắt đầu tìm
    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Integer namSanXuat;

    private String truyenDong;

    private String sdtDoiTac;

    private String loaiNhienLieu;

    private Integer mucTieuThu;

    private String bienSo;
    private OtoStatus trangThai;

    private String doiTacId;

    private Integer mauXeId;

    private Integer hangXeId;

}