package com.example.car.services.impl;

import com.example.car.entities.HangXe;
import com.example.car.repositories.HangXeRepository;
import com.example.car.services.IHangXeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HangXeService implements IHangXeService {

    private HangXeRepository hangXeRepository;

    @Override
    public List<HangXe> getAllHangXe() {
        return hangXeRepository.findAll();
    }
}
