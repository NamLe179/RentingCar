package com.example.car.dto;

import com.example.car.enums.HopDongChoThueStatus;
import com.example.car.utils.MapUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchingHopDongChoThueDto {

    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private HopDongChoThueStatus trangThai;
    private String sdtDoiTac;
    private String bienSo;
    private String doiTacId;

    public static SearchingHopDongChoThueDto toSearchingHopDongChoThueDto(
            Map<String, Object> params
    ) throws Exception {
        return SearchingHopDongChoThueDto.builder()
                .doiTacId(MapUtil.getObject(params, "doiTacId", String.class))
                .bienSo(MapUtil.getObject(params, "bienSo", String.class))
                .sdtDoiTac(MapUtil.getObject(params, "sdtDoiTac", String.class))
                .trangThai(MapUtil.getObject(params, "trangThai", HopDongChoThueStatus.class))
                .ngayBatDau(MapUtil.getObject(params, "ngayBatDau", LocalDateTime.class))
                .ngayKetThuc(MapUtil.getObject(params, "ngayKetThuc", LocalDateTime.class))
                .build();
    }

}
