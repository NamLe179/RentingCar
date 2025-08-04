package com.example.car.services;

import com.example.car.dto.AnhCuaXeDto;
import com.example.car.entities.AnhCuaXe;
import org.springframework.stereotype.Service;

@Service
public interface IAnhCuaXeService {
    public AnhCuaXe createAnhCuaXe(Integer otoId, AnhCuaXeDto anhCuaXeDto) throws Exception;
    public AnhCuaXe updateAnhCuaXe(AnhCuaXeDto anhCuaXeDto) throws Exception;
}
