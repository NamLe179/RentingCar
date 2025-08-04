package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.AnhCuaXeDto;
import com.example.car.entities.AnhCuaXe;
import com.example.car.entities.Oto;
import com.example.car.repositories.AnhCuaXeRepository;
import com.example.car.repositories.OtoRepository;
import com.example.car.services.IAnhCuaXeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class AnhCuaXeService implements IAnhCuaXeService {

    private OtoRepository otoRepository;
    private AnhCuaXeRepository anhCuaXeRepository;

    @Override
    public AnhCuaXe createAnhCuaXe(Integer otoId, AnhCuaXeDto anhCuaXeDto) throws Exception {
//        Oto existingOto = otoRepository.findById(otoId)
//                .orElseThrow(() ->
//                        new DataNotFoundException(
//                                "Cannot find oto with id: "+ otoId));
//        AnhCuaXe anhCuaXe = AnhCuaXe.builder()
//                .oto(existingOto)
//                .build();
//        anhCuaXe.setUrl(anhCuaXeDto.getUrl());
//        anhCuaXe.setNgayChup(new Date());
//        //Ko cho insert quá 5 ảnh cho 1 sản phẩm
//        int size = anhCuaXeRepository.findByOtoId(otoId).size();
//        if(size >= AnhCuaXe.MAXIMUM_IMAGES_PER_PRODUCT) {
//            throw new InvalidParamException(
//                    "Number of images must be <= "
//                            + AnhCuaXe.MAXIMUM_IMAGES_PER_PRODUCT);
//        }
//        return anhCuaXeRepository.save(anhCuaXe);
        return null;
    }

    @Override
    public AnhCuaXe updateAnhCuaXe(AnhCuaXeDto anhCuaXeDto) throws Exception {
        AnhCuaXe existingAnhCuaXe = anhCuaXeRepository.findById(anhCuaXeDto.getId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Khong tim thay anh cua xe voi id: "+ anhCuaXeDto.getId()));
        if (null != existingAnhCuaXe) {
            existingAnhCuaXe.setGhiChu(anhCuaXeDto.getGhiChu());
            existingAnhCuaXe.setGiayToXe(anhCuaXeDto.getGiayToXe());
            existingAnhCuaXe.setThumnail(anhCuaXeDto.getThumnail());
            return anhCuaXeRepository.save(existingAnhCuaXe);
        }
        return null;
    }

}
