package com.example.car.services;

import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.dto.SearchingHopDongChoThueDto;
import com.example.car.entities.HopDongChoThue;
import com.example.car.enums.HopDongChoThueStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHopDongChoThueService {
    List<HopDongChoThue> getAllHopDongChoThue();
    HopDongChoThue createHopDongChoThue(HopDongChoThueRequestDTO hopDongChoThueRequestDTO) throws Exception;
    HopDongChoThue getHopDongChoThueById(Integer id) throws Exception;
    HopDongChoThue thanhLyHopDongChoThue(Integer hopDongChoThueId, HopDongChoThueStatus hopDongChoThueStatus) throws Exception;
    List<HopDongChoThue> findBySearchingHopDongChoThueDto(SearchingHopDongChoThueDto searchingHopDongChoThueDto) throws Exception;
}
