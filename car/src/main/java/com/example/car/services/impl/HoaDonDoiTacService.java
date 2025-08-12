package com.example.car.services.impl;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.dto.HoaDonDoiTacRequestDto;
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
    public HoaDonDoiTac createHoaDonDoiTac(HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto) throws Exception {
        HopDongChoThue hopDongChoThue = hopDongChoThueRepository.findById(hoaDonDoiTacRequestDto.getHopDongChoThueId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay hop dong cho thue co id: " + hoaDonDoiTacRequestDto.getHopDongChoThueId()
                ));
        NhanVien nhanVien = nhanVienRepository.findById(hoaDonDoiTacRequestDto.getNhanVienId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay nhan vien co id: " + hoaDonDoiTacRequestDto.getNhanVienId()
                ));
        HoaDonDoiTac hoaDonDoiTac = HoaDonDoiTac.builder()
                .tongTien(hoaDonDoiTacRequestDto.getTongTien())
                .ghiChu(hoaDonDoiTacRequestDto.getGhiChu())
                .ngayThanhToan(hoaDonDoiTacRequestDto.getNgayThanhToan())
                .ngayBatDau(hoaDonDoiTacRequestDto.getNgayBatDau())
                .ngayKetThuc(hoaDonDoiTacRequestDto.getNgayKetThuc())
                .phuongThucThanhToan(hoaDonDoiTacRequestDto.getPhuongThucThanhToan())
                .hopDongChoThue(hopDongChoThue)
                .nhanVien(nhanVien)
                .build();
        return hoaDonDoiTacRepository.save(hoaDonDoiTac);
    }
}
