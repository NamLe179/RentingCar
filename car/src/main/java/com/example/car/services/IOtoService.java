package com.example.car.services;

import com.example.car.dto.OtoRequestDto;
import com.example.car.entities.Oto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOtoService {
    public Oto createOto(OtoRequestDto oToRequestDto) throws Exception;
    Oto updateOto(Integer id, OtoRequestDto otoRequestDto) throws Exception;
    public Oto getOtoById(Integer id) throws Exception;
    List<Oto> findByDoiTacId(String doiTacId);
}
