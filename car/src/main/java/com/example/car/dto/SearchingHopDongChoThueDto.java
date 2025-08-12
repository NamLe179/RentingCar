package com.example.car.dto;

import com.example.car.enums.HopDongChoThueStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchingHopDongChoThueDto {

    private Date ngayBatDau;
    private Date ngayKetThuc;
    private HopDongChoThueStatus trangThai;
    private String sdtDoiTac;
    private String bienSo;
    private String doiTacId;


}
