package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.NhanVien;
import com.example.car.entities.Oto;
import com.example.car.enums.OtoStatus;
import com.example.car.repositories.HopDongChoThueRepository;
import com.example.car.repositories.NhanVienRepository;
import com.example.car.repositories.OtoRepository;
import com.example.car.services.IHopDongChoThueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class HopDongChoThueService implements IHopDongChoThueService {

    private OtoRepository otoRepository;
    private NhanVienRepository nhanVienRepository;
    private HopDongChoThueRepository hopDongChoThueRepository;

    @Override
    @Transactional
    public HopDongChoThue createHopDongChoThue(HopDongChoThueRequestDTO hopDongChoThueRequestDTO) throws Exception {
        Oto existingOto = otoRepository.findById(hopDongChoThueRequestDTO.getOtoId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay o to co id: " + hopDongChoThueRequestDTO.getOtoId()
                ));
        NhanVien existingNhanVien = nhanVienRepository.findById(hopDongChoThueRequestDTO.getNhanVienId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay nhan vien co id: " + hopDongChoThueRequestDTO.getNhanVienId()
                ));
        HopDongChoThue hopDongChoThue = HopDongChoThue.builder()
                .giaThue(hopDongChoThueRequestDTO.getGia())
                .oto(existingOto)
                .nhanVien(existingNhanVien)
                .ghiChu(hopDongChoThueRequestDTO.getGhiChu())
                .ngayKetThuc(hopDongChoThueRequestDTO.getNgayKetThuc())
                .ngayBatDau(hopDongChoThueRequestDTO.getNgayBatDau())
                .ghiChu(hopDongChoThueRequestDTO.getGhiChu())
                .build();
        return hopDongChoThueRepository.save(hopDongChoThue);
    }

    @Override
    public HopDongChoThue getHopDongChoThueById(Integer id) throws Exception {
        return hopDongChoThueRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong co id: " + id
                ));
    }
}
