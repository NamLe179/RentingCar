package com.example.car.services;

import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.entities.HopDongChoThue;
import org.springframework.stereotype.Service;

@Service
public interface IHopDongChoThueService {
    HopDongChoThue createHopDongChoThue(HopDongChoThueRequestDTO hopDongChoThueRequestDTO) throws Exception;
    HopDongChoThue getHopDongChoThueById(Integer id) throws Exception;
    HopDongChoThue huyHopDongChoThue(Integer hopDongChoThueId) throws Exception;
}
