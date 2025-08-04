package com.example.car.services;

import com.example.car.entities.HangXe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHangXeService {
    public List<HangXe> getAllHangXe();
}
