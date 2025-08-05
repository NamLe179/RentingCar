package com.example.car.services;

import com.example.car.dto.AnhCuaXeRequestDto;
import com.example.car.entities.AnhCuaXe;
import org.springframework.stereotype.Service;

@Service
public interface IAnhCuaXeService {
    public AnhCuaXe createAnhCuaXe(Integer otoId, AnhCuaXeRequestDto anhCuaXeRequestDto) throws Exception;
    public AnhCuaXe updateAnhCuaXe(Integer anhCuaXeId, AnhCuaXeRequestDto anhCuaXeRequestDto) throws Exception;
}
