package com.example.car.services;

import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDonDoiTac;
import org.springframework.stereotype.Service;

@Service
public interface IHoaDonDoiTacService {
    HoaDonDoiTac createHoaDonDoiTac(HoaDonRequestDTO hoaDonRequestDTO) throws Exception;
}
