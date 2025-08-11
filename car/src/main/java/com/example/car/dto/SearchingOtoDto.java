package com.example.car.dto;

import com.example.car.enums.OtoStatus;
import com.example.car.utils.MapUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchingOtoDto {
    //ngày bắt đầu tìm
    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayKetThuc;

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

    public static SearchingOtoDto toSearchingOtoDtO(Map<String, Object> params) throws Exception {
        return SearchingOtoDto.builder()
                .doiTacId(MapUtil.getObject(params, "doiTacId", String.class))
                .bienSo(MapUtil.getObject(params, "bienSo", String.class))
                .trangThai(MapUtil.getObject(params, "trangThai", OtoStatus.class))
                .ngayBatDau(MapUtil.getObject(params, "ngayBatDau", LocalDateTime.class))
                .ngayKetThuc(MapUtil.getObject(params, "ngayKetThuc", LocalDateTime.class))
                .namSanXuat(MapUtil.getObject(params, "namSanXuat", Integer.class))
                .truyenDong(MapUtil.getObject(params, "truyenDong", String.class))
                .loaiNhienLieu(MapUtil.getObject(params, "loaiNhienLieu", String.class))
                .mauXeId(MapUtil.getObject(params, "mauXeId", Integer.class))
                .hangXeId(MapUtil.getObject(params, "hangXeId", Integer.class))
                .build();
    }

}