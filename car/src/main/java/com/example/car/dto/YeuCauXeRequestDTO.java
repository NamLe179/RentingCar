package com.example.car.dto;

import lombok.Data;

import java.util.Date;

@Data
public class YeuCauXeRequestDTO {
    private String khachHangId;
    private Date pickupDateTime;
    private Date returnDateTime;
    private String tinh;
    private Integer seats;
    private String brand;
    private String fuelType; // xăng/dầu/điện
    private String transmissionType;
}
