package com.example.car.services.impl;

import com.example.car.entities.TienNghi;
import com.example.car.repositories.TienNghiRepository;
import com.example.car.services.ITienNghiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TienNghiService implements ITienNghiService {

    private TienNghiRepository tienNghiRepository;

    @Override
    public List<TienNghi> getAllTienNghi() {
        return tienNghiRepository.findAll();
    }
}
