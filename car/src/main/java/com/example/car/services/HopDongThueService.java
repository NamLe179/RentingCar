package com.example.car.services;

import com.example.car.dto.HopDongThueRequestDTO;
import com.example.car.entities.HopDongThue;
import com.example.car.enums.HopDongThueStatus;
import com.example.car.repositories.HopDongThueRepository;
import com.example.car.repositories.KhachHangRepository;
import com.example.car.repositories.OtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HopDongThueService {
    @Autowired
    private HopDongThueRepository hopDongThueRepository;
    @Autowired
    private OtoRepository otoRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;


    public HopDongThue createHopDongThue(HopDongThueRequestDTO requestDTO) {
        // Lưu hợp đồng thuê xe
        HopDongThue hopDongThue = new HopDongThue();
//        hopDongThue.setId();
        hopDongThue.setOto(otoRepository.findById(requestDTO.getOtoId())
                .orElseThrow(() -> new RuntimeException("Xe không tồn tại")));
        hopDongThue.setKhachHang(khachHangRepository.findById(requestDTO.getKhachHangId())
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại")));
        hopDongThue.setThoiGianNhan(requestDTO.getThoiGianNhan());
        hopDongThue.setThoiGianTra(requestDTO.getThoiGianTra());
        hopDongThue.setTrangThai(HopDongThueStatus.CHO_DUYET); // 0: Chưa xac nhan, 1: oke , 2: Hủy
        hopDongThue.setMoTa(requestDTO.getMoTa());
        hopDongThue.setGia(requestDTO.getGiaThue());

        return hopDongThueRepository.save(hopDongThue);
    }

    public List<HopDongThue> getHopDongThueChoDuyet() {
        // Lấy danh sách hợp đồng thuê xe đang chờ duyệt
        return hopDongThueRepository.findHopDongChoDuyet();
    }

    public List<HopDongThue> getHopDongThueChoDuyetByThoiGian(HopDongThueRequestDTO requestDTO) {
        // Lấy danh sách hợp đồng thuê xe đang chờ duyệt theo thời gian
        return hopDongThueRepository.findHopDongChoDuyetByThoiGian(requestDTO.getNgayBatDau(), requestDTO.getNgayKetThuc());
    }

    public HopDongThue setCheckInTime(int hopDongThueId) {
        // Cập nhật thời gian check-in
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
        HopDongThue hopDongThue = hopDongThueRepository.findById(hopDongThueId)
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setCheckin(new Date());

        return hopDongThueRepository.save(hopDongThue);
    }

    public HopDongThue setCheckOutTime(int hopDongThueId) {
        // Cập nhật thời gian check-out
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
        HopDongThue hopDongThue = hopDongThueRepository.findById(hopDongThueId)
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setCheckout(new Date());
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue cancelHopDongThue(int hopDongThueId) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã hủy
        HopDongThue hopDongThue = hopDongThueRepository.findById(hopDongThueId)
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setTrangThai(HopDongThueStatus.HUY); // 2: Hủy
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue confirmHopDongThue(int hopDongThueId) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã xác nhận
        HopDongThue hopDongThue = hopDongThueRepository.findById(hopDongThueId)
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setTrangThai(HopDongThueStatus.OK); // 1: Đã xác nhận
        return hopDongThueRepository.save(hopDongThue);
    }

}
