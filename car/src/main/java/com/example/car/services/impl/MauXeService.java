package com.example.car.services.impl;

import com.example.car.entities.MauXe;
import com.example.car.repositories.MauXeRepository;
import com.example.car.services.IMauXeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MauXeService implements IMauXeService {

    private MauXeRepository mauXeRepository;

    @Override
    public List<MauXe> getAllMauXeBelongToHangXe(Integer hangXeId) {
        return mauXeRepository.findByHangXeId(hangXeId);
    }
}
