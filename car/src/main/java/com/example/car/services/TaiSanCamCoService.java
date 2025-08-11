package com.example.car.services;

import com.example.car.dto.TaiSanCamCoCanTraRequestDTO;
import com.example.car.dto.TaiSanCamCoRequestDTO;
import com.example.car.entities.TaiSanCamCo;
import com.example.car.enums.TaiSanCamCoStatus;
import com.example.car.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaiSanCamCoService {
    @Autowired
    private TaiSanCamCoRepository taiSanCamCoRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private HopDongThueRepository hopDongThueRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;


    public List<TaiSanCamCo> nhanTaiSanCamCo(List<TaiSanCamCoRequestDTO> list) {
        List<TaiSanCamCo> taiSanCamCoList = new ArrayList<>();
        for (TaiSanCamCoRequestDTO request : list) {
            // Tạo tài sản cầm cố từ yêu cầu
            TaiSanCamCo taiSanCamCo = new TaiSanCamCo();

            taiSanCamCo.setTen(request.getTenTaiSan());
            taiSanCamCo.setLoaiTaiSan(request.getLoaiTaiSan());
            taiSanCamCo.setGia(request.getGiaTriTaiSan());
            taiSanCamCo.setMoTa(request.getMoTa());
            taiSanCamCo.setTrangThai(TaiSanCamCoStatus.DA_NHAN); // 0: Da nhận, 1: Đã tra
            taiSanCamCo.setThoiGianNhan(request.getThoiGianNhan());
            taiSanCamCo.setKhachHang(khachHangRepository.findById(String.valueOf(request.getKhachHangId()))
                    .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại")));
            taiSanCamCo.setHopDongThue(hopDongThueRepository.findById(String.valueOf(request.getHopDongThueId()))
                    .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại")));
            taiSanCamCo.setNhanVienNhan(nhanVienRepository.findById(String.valueOf(request.getNhanVienId()))
                    .orElseThrow(() -> new RuntimeException("Nhân viên nhận tài sản không tồn tại")));

            // Lưu tài sản cầm cố
            taiSanCamCoList.add(taiSanCamCoRepository.save(taiSanCamCo));
        }
        // Lưu tài sản cầm cố
        return taiSanCamCoList;
    }

//    public List<TaiSanCamCo> getTaiSanCamCo(String doiTacId) {
//        // Lấy danh sách tài sản cầm cố theo hợp đồng thuê xe
//        return taiSanCamCoRepository.findByDoiTacAndTrangThai(doiTacId);
//    }

    public TaiSanCamCo traTaiSanCamCo(TaiSanCamCoCanTraRequestDTO requestDTO) {
        // Cập nhật trạng thái tài sản cầm cố thành đã trả
        TaiSanCamCo taiSanCamCo = taiSanCamCoRepository.findById(String.valueOf(requestDTO.getTaiSanCamCoId()))
                .orElseThrow(() -> new RuntimeException("Tài sản cầm cố không tồn tại"));
        taiSanCamCo.setTrangThai(TaiSanCamCoStatus.DA_TRA); // 1: Đã trả
        taiSanCamCo.setThoiGianTra(new Date());

        return taiSanCamCoRepository.save(taiSanCamCo);
    }



}
