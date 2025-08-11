package com.example.car.services;

import com.example.car.dto.HoaDonDoiTacRequestDto;
import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDonDoiTac;
import org.springframework.stereotype.Service;

@Service
public interface IHoaDonDoiTacService {
    HoaDonDoiTac createHoaDonDoiTac(HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws Exception;
}
