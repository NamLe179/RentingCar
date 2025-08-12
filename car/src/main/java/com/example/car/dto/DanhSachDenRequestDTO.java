package com.example.car.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DanhSachDenRequestDTO {
    private Integer id;
    private Date ngayThem;
    private String lyDo;
    private String quanLyId;
    private int hopDongThueId;
    private Integer trangThai;

    private Date ngayBatDau;
    private Date ngayKetThuc;
}