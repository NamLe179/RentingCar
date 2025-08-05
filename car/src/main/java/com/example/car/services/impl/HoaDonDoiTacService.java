package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDonDoiTac;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.NhanVien;
import com.example.car.repositories.HoaDonDoiTacRepository;
import com.example.car.repositories.HopDongChoThueRepository;
import com.example.car.repositories.NhanVienRepository;
import com.example.car.services.IHoaDonDoiTacService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HoaDonDoiTacService implements IHoaDonDoiTacService {

    private HopDongChoThueRepository hopDongChoThueRepository;
    private NhanVienRepository nhanVienRepository;
    private HoaDonDoiTacRepository hoaDonDoiTacRepository;

    @Override
    public HoaDonDoiTac createHoaDonDoiTac(HoaDonRequestDTO hoaDonRequestDTO) throws Exception {
        HopDongChoThue hopDongChoThue = hopDongChoThueRepository.findById(hoaDonRequestDTO.getHopDongThueId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong cho thue co id: " + hoaDonRequestDTO.getHopDongThueId()
                ));
        NhanVien nhanVien = nhanVienRepository.findById(hoaDonRequestDTO.getNhanVienId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay nhan vien co id: " + hoaDonRequestDTO.getNhanVienId()
                ));
        HoaDonDoiTac hoaDonDoiTac = HoaDonDoiTac.builder()
                .tongTien(hoaDonRequestDTO.getTongTien())
                .ghiChu(hoaDonRequestDTO.getGhiChu())
                .ngayThanhToan(hoaDonRequestDTO.getNgayThanhToan())
                .phuongThucThanhToan(hoaDonRequestDTO.getPhuongThucThanhToan())
                .hopDongChoThue(hopDongChoThue)
                .nhanVien(nhanVien)
                .build();
        return hoaDonDoiTacRepository.save(hoaDonDoiTac);
    }
}
