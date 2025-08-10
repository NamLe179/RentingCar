package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.AnhCuaXeRequestDto;
import com.example.car.entities.AnhCuaXe;
import com.example.car.entities.Oto;
import com.example.car.repositories.AnhCuaXeRepository;
import com.example.car.repositories.OtoRepository;
import com.example.car.services.IAnhCuaXeService;
import com.example.car.services.IStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AnhCuaXeService implements IAnhCuaXeService {

    private OtoRepository otoRepository;
    private AnhCuaXeRepository anhCuaXeRepository;
    private IStorageService storageService;

    @Override
    public AnhCuaXe createAnhCuaXe(Integer otoId, AnhCuaXeRequestDto anhCuaXeRequestDto) throws Exception {
        Oto existingOto = otoRepository.findById(otoId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find oto with id: "+ otoId));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(anhCuaXeRequestDto.getFile().getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID() + "_" + fileName;

        //store image
        storageService.store(anhCuaXeRequestDto.getFile(), uniqueFilename);
        AnhCuaXe anhCuaXe = AnhCuaXe.builder()
                .giayToXe(anhCuaXeRequestDto.getGiayToXe())
                .thumnail(anhCuaXeRequestDto.getThumnail())
                .oto(existingOto)
                .build();
        anhCuaXe.setUrl(uniqueFilename);
        anhCuaXe.setNgayChup(new Date());
        //Ko cho insert quá 5 ảnh cho 1 sản phẩm
        int size = anhCuaXeRepository.findByOtoId(otoId).size();
        if(size >= AnhCuaXe.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new InvalidParamException(
                    "Number of images must be <= "
                            + AnhCuaXe.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return anhCuaXeRepository.save(anhCuaXe);
    }

    @Override
    public AnhCuaXe updateAnhCuaXe(Integer anhCuaXeId, AnhCuaXeRequestDto anhCuaXeRequestDto) throws Exception {
        AnhCuaXe existingAnhCuaXe = anhCuaXeRepository.findById(anhCuaXeId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Khong tim thay anh cua xe voi id: "+ anhCuaXeId));
        if (null != existingAnhCuaXe) {
            if( !Objects.equals(anhCuaXeRequestDto.getFile().getOriginalFilename(), existingAnhCuaXe.getUrl())) {
                storageService.delete(existingAnhCuaXe.getUrl());

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(anhCuaXeRequestDto.getFile().getOriginalFilename()));
                String uniqueFilename = UUID.randomUUID() + "_" + fileName;

                //store image
                storageService.store(anhCuaXeRequestDto.getFile(), uniqueFilename);
                existingAnhCuaXe.setUrl(uniqueFilename);
                existingAnhCuaXe.setNgayChup(new Date());
            }
            existingAnhCuaXe.setGhiChu(anhCuaXeRequestDto.getGhiChu());
            existingAnhCuaXe.setGiayToXe(anhCuaXeRequestDto.getGiayToXe());
            existingAnhCuaXe.setThumnail(anhCuaXeRequestDto.getThumnail());
            return anhCuaXeRepository.save(existingAnhCuaXe);
        }
        return null;
    }

}
