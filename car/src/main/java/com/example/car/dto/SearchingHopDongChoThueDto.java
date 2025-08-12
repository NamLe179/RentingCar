package com.example.car.dto;

import com.example.car.enums.HopDongChoThueStatus;
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


}
