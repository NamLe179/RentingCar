package com.example.car.services;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.HoaDonDoiTacRequestDto;
import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.dto.SearchingHoaDonDoiTacDto;
import com.example.car.entities.HoaDonDoiTac;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHoaDonDoiTacService {
    List<HoaDonDoiTac> findBySearchingHoaDonDoiTacDto(SearchingHoaDonDoiTacDto searchingHoaDonDoiTacDto);
    HoaDonDoiTac createHoaDonDoiTac(HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws DataNotFoundException, InvalidParamException;
    HoaDonDoiTac updateHoaDonDoiTac(Integer hoaDonDoiTacId,
                                    HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws DataNotFoundException, InvalidParamException;
    void deleteHoaDonDoiTac(Integer id) throws DataNotFoundException, InvalidParamException;
}
