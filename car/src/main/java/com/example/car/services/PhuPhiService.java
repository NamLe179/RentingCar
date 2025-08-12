package com.example.car.services;

import com.example.car.dto.PhuPhiDuocChonRequestDTO;
import com.example.car.entities.HoaDon;
import com.example.car.entities.PhuPhi;
import com.example.car.entities.PhuPhiDuocChon;
import com.example.car.repositories.HoaDonRepository;
import com.example.car.repositories.PhuPhiDuocChonRepository;
import com.example.car.repositories.PhuPhiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhuPhiService {
    @Autowired
    private PhuPhiRepository phuPhiRepository;
    @Autowired
    private PhuPhiDuocChonRepository phuPhiDuocChonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    public List<PhuPhi> getDanhSachPhuPhi(){
        // Lấy danh sách phụ phí
        List<PhuPhi> phuPhiList = phuPhiRepository.findAll();
        return phuPhiList;
    }

    public void luuPhuPhiDuocChon(List<PhuPhiDuocChonRequestDTO> list) {
        for (PhuPhiDuocChonRequestDTO request : list) {
            // Tạo phụ phí được chọn từ yêu cầu
            PhuPhiDuocChon phuPhiDuocChon = new PhuPhiDuocChon();
//            phuPhiDuocChon.setId(request.getId());
            PhuPhi phuPhi = phuPhiRepository.findById(request.getPhuPhiId())
                    .orElseThrow(() -> new RuntimeException("Phụ phí không tồn tại"));
            phuPhiDuocChon.setPhuPhi(phuPhi);

            HoaDon hoaDon = hoaDonRepository.findById(request.getHoaDonId())
                    .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

            if(phuPhi.getTen().equals("Phí quá giờ")) {
                // Chuyển đổi thời gian từ phút sang giờ
                int soGio = request.getSoGio();
                int soNgay = chuyenDoiThoiGian(soGio);
                if(soNgay < 1) {
                    phuPhiDuocChon.setGia(phuPhi.getGia() * soGio);
                }
                else {
                    phuPhiDuocChon.setGia(hoaDon.getHopDongThue().getOto().getGia() * soNgay);
                }
            } else {
                phuPhiDuocChon.setGia(phuPhi.getGia());
            }
            phuPhiDuocChon.setSoLuong(request.getSoLuong());

            phuPhiDuocChon.setHoaDon(hoaDon);

            // Lưu phụ phí được chọn
            phuPhiDuocChonRepository.save(phuPhiDuocChon);
        }
    }

    public PhuPhi addPhuPhi(PhuPhi phuPhi) {
        // Kiểm tra xem phụ phí đã tồn tại chưa
        if (phuPhiRepository.findByTen(phuPhi.getTen())) {
            throw new IllegalArgumentException("Phụ phí với tên này đã tồn tại");
        }
        // Lưu phụ phí mới
        return phuPhiRepository.save(phuPhi);
    }

    public int chuyenDoiThoiGian(int soGio) {
        if (soGio < 6) {
            return 0; // Nếu dưới 6 giờ, tính là 1 ngày
        }

        return (soGio + 24) / 24;
    }
}
