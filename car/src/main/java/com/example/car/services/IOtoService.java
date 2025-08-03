package com.example.car.services;

import com.example.car.dto.OtoDto;
import com.example.car.entities.Oto;
import org.springframework.stereotype.Service;

@Service
public interface IOtoService {
    public Oto createOto(OtoDto oToDto);
}
