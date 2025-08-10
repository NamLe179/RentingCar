package com.example.car.services;

import com.example.car.dto.*;
import com.example.car.entities.*;
import com.example.car.enums.HopDongThueStatus;
import com.example.car.enums.OtoStatus;
import com.example.car.enums.TaiSanCamCoStatus;
import com.example.car.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RentingCarService {
    @Autowired
    private HangXeRepository hangXeRepository;
    @Autowired
    private OtoRepository otoRepository;
    @Autowired
    private TienNghiDuocChonRepository tienNghiDuocChonRepository;
    @Autowired
    private AnhCuaXeRepository anhCuaXeRepository;
    @Autowired
    private HopDongThueRepository hopDongThueRepository;

    public List<HangXe> getAllHangXe() {
        // Lấy danh sách hãng xe
        return hangXeRepository.findAll();
    }

    public List<Oto> getAllCars() {
        // Lấy danh sách tất cả xe
        return otoRepository.findAllByTrangThai(OtoStatus.OK);
    }

    public List<Oto> searchCars(YeuCauXeRequestDTO request) {
        // Tạo truy vấn tìm kiếm dựa trên yêu cầu
        if( request != null) {
            Date thoiGianNhan = request.getPickupDateTime();
            Date thoiGianTra = request.getReturnDateTime();
            String tinh = request.getTinh();
            String brand = request.getBrand();
            String fuelType = request.getFuelType();
            String transmissionType = request.getTransmissionType();
            Integer seats = request.getSeats();

            System.out.println(request);
            System.out.println(new Date());

            // Gọi repository để tìm kiếm xe, thuc hiện truy vấn với các tham số
            return otoRepository.findByCriteria(thoiGianNhan, thoiGianTra, tinh, brand, fuelType, transmissionType, seats);
        }
        // Nếu không có yêu cầu, trả về danh sách tất cả xe
        return otoRepository.findAll();
        // Gọi repository để tìm kiếm xe
//        return otoRepository.findByCriteria(brand, fuelType, transmissionType, seats);
    }
    public List<TienNghi> getTienNghiTheoOto(Integer otoId) {
        // Lấy danh sách tiện nghi theo Oto ID
        List<TienNghiDuocChon> tienNghiDuocChonList = tienNghiDuocChonRepository.findByOtoId(otoId);
        List<TienNghi> tienNghiList = new ArrayList<>();

        for (TienNghiDuocChon tienNghiDuocChon : tienNghiDuocChonList) {
            // Giả sử TienNghiDuocChon có phương thức getTienNghi() để lấy tiện nghi
            tienNghiList.add(tienNghiDuocChon.getTienNghi());
        }

        return tienNghiList;
    }
    public List<String> getAnhCuaXeTheoOto(Integer otoId) {
        // Lấy danh sách ảnh của xe theo Oto ID
        return anhCuaXeRepository.findByOtoIdAndGiayToXe(otoId);
    }

    public List<DanhGiaResponseDTO> getDanhGiaTheoOto(String otoId) {
        // Lấy danh sách đánh giá của xe theo Oto ID
        List<HopDongThue> hopDongThueList = hopDongThueRepository.findByOtoId(otoId);
        List<DanhGiaResponseDTO> danhGiaList = new ArrayList<>();
        for( HopDongThue hopDong : hopDongThueList) {

            DanhGiaResponseDTO danhGia = new DanhGiaResponseDTO();
            danhGia.setTenKhachHang(hopDong.getKhachHang().getHoTen());
            danhGia.setKhachHangDanhGiaSo(hopDong.getKhachHangDanhGiaSo());
            danhGia.setKhachHangDanhGiaChu(hopDong.getKhachHangDanhGiaChu());
            danhGiaList.add(danhGia);
        }
        return danhGiaList;
    }


}
