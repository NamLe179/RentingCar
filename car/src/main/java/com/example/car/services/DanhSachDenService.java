package com.example.car.services;

import com.example.car.dto.DanhSachDenRequestDTO;
import com.example.car.entities.DanhSachDen;
import com.example.car.entities.HopDongThue;
import com.example.car.enums.DanhSachDenStatus;
import com.example.car.repositories.DanhSachDenRepository;
import com.example.car.repositories.HopDongThueRepository;
import com.example.car.repositories.QuanLyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhSachDenService {
    @Autowired
    private DanhSachDenRepository danhSachDenRepository;
    @Autowired
    private HopDongThueRepository hopDongThueRepository;
    @Autowired
    private QuanLyRepository quanLyRepository;

    public List<DanhSachDen> getAllDanhSachDen() {
        return danhSachDenRepository.findAll();
    }

    public List<DanhSachDen> getDanhSachDenByThoiGian(DanhSachDenRequestDTO request) {
        return danhSachDenRepository.findByThoiGian(request.getNgayBatDau(), request.getNgayKetThuc());
    }

    public List<DanhSachDen> getDanhSachDenByTrangThai(DanhSachDenRequestDTO request) {
        return danhSachDenRepository.findByTrangThai(request.getTrangThai());
    }

    public List<DanhSachDen> getDanhSachDenByThoiGianAndTrangThai(DanhSachDenRequestDTO request) {
        return danhSachDenRepository.findByThoiGianAndTrangThai(
                request.getNgayBatDau(),
                request.getNgayKetThuc(),
                request.getTrangThai()
        );
    }

    public DanhSachDen createDanhSachDen(DanhSachDenRequestDTO request) {
        DanhSachDen danhSachDen = new DanhSachDen();
        danhSachDen.setNgayThem(request.getNgayThem());
        danhSachDen.setLyDo(request.getLyDo());
        danhSachDen.setTrangThai(DanhSachDenStatus.CHO_DUYET);
        danhSachDen.setQuanLy(quanLyRepository.findById(request.getQuanLyId())
                .orElseThrow(() -> new RuntimeException("Quan ly not found")));
        HopDongThue hopDongThue = hopDongThueRepository.findById(request.getHopDongThueId())
                .orElseThrow(() -> new RuntimeException("Hop dong thue not found"));
        danhSachDen.setHopDongThue(hopDongThue);
        danhSachDen.setKhachHang(hopDongThue.getKhachHang());
        return danhSachDenRepository.save(danhSachDen);
    }

    public DanhSachDen updateDanhSachDen(DanhSachDenRequestDTO request) {
        DanhSachDen danhSachDen = danhSachDenRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Danh sach den not found"));
        if(request.getTrangThai() == 1) {
            danhSachDen.setTrangThai(DanhSachDenStatus.OK);
        } else if(request.getTrangThai() == 2) {
            danhSachDen.setTrangThai(DanhSachDenStatus.HUY);
        }
        return danhSachDenRepository.save(danhSachDen);
    }

}
