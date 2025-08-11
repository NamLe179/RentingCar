package com.example.car.services;

import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDon;
import com.example.car.repositories.HoaDonRepository;
import com.example.car.repositories.HopDongThueRepository;
import com.example.car.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HopDongThueRepository hopDongThueRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<HoaDon> getAllHoaDons() {
        return hoaDonRepository.findAll();
    }

    public  List<HoaDon> getHoaDonByThoiGian(HoaDonRequestDTO request) {
        // Lấy danh sách hóa đơn theo thời gian bắt đầu và kết thúc
        List<HoaDon> hoaDons = hoaDonRepository.findByThoiGian(request.getNgayBatDau(), request.getNgayKetThuc());
        return hoaDons;
    }

    public List<HoaDon> getHoaDonByKhachHang(String sdt) {
        // Lấy danh sách hóa đơn theo thời gian bắt đầu, kết thúc và mã đối tác
        List<HoaDon> hoaDons = hoaDonRepository.findByKhachHang(sdt);
        return hoaDons;
    }

    public HoaDon createHoaDon(HoaDonRequestDTO requestDTO) {
        // Tạo hóa đơn từ hợp đồng thuê xe
        HoaDon hoaDon = new HoaDon();
//        hoaDon.setId(hopDongThue.getId());
        hoaDon.setHopDongThue(hopDongThueRepository.findById(requestDTO.getHopDongThueId())
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại")));
        hoaDon.setNhanVien(nhanVienRepository.findById(String.valueOf(requestDTO.getNhanVienId()))
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại")));
        hoaDon.setTongTien(requestDTO.getTongTien());
        hoaDon.setNgayThanhToan(requestDTO.getNgayThanhToan());
        hoaDon.setPhuongThucThanhToan(requestDTO.getPhuongThucThanhToan());

        return hoaDonRepository.save(hoaDon);
    }

    public float calculateTongTienTheoHopDong(int hopDongThueId) {
        // Tính tổng tiền theo hợp đồng thuê xe
        List<HoaDon> dsHoaDon = hoaDonRepository.findByHopDongThueId(hopDongThueId);
        float total = 0;
        for (HoaDon hoaDon : dsHoaDon) {
            total += hoaDon.getTongTien();
        }
        return total;
    }

}