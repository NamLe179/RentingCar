package com.example.car.services;

import com.example.car.entities.MauXe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMauXeService {
    public List<MauXe> getAllMauXeBelongToHangXe(Integer hangXeId);
}
