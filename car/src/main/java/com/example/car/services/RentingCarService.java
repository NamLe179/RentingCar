package com.example.car.services;

import com.example.car.dto.*;
import com.example.car.entities.*;
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
    private OtoRepository otoRepository;
    @Autowired
    private TienNghiRepository tienNghiRepository;
    @Autowired
    private AnhCuaXeRepository anhCuaXeRepository;
    @Autowired
    private HopDongThueRepository hopDongThueRepository;
    @Autowired
    private PhuPhiRepository phuPhiRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private TaiSanCamCoRepository taiSanCamCoRepository;
    @Autowired
    private PhuPhiDuocChonRepository phuPhiDuocChonRepository;

    public List<Oto> searchCars(YeuCauXeRequestDTO request) {
        // Tạo truy vấn tìm kiếm dựa trên yêu cầu
        if( request != null) {
            Date ngayNhan = request.getPickupDateTime();
            Date ngayTra = request.getReturnDateTime();
            String tinh = request.getTinh();
            String brand = request.getBrand();
            String fuelType = request.getFuelType();
            String transmissionType = request.getTransmissionType();
            Integer seats = request.getSeats();


            // Gọi repository để tìm kiếm xe, thuc hiện truy vấn với các tham số
//          return otoRepository.findByCriteria();
        }
        // Nếu không có yêu cầu, trả về danh sách tất cả xe
        return otoRepository.findAll();



        // Gọi repository để tìm kiếm xe
//        return otoRepository.findByCriteria(brand, fuelType, transmissionType, seats);
    }
    public TienNghi getTienNghiTheoOto(String otoId) {
        // Lấy thông tin tiện nghi theo Oto ID
//        return tienNghiRepository.findByCarId(id).orElse(null);
    }
    public List<String> getAnhCuaXeTheoOto(String otoId) {
        // Lấy danh sách ảnh của xe theo Oto ID
//        return anhCuaXeRepository.findByCarIdAndType(otoId, "AnhXe");
    }

    public List<DanhGiaResponseDTO> getDanhGiaTheoOto(String otoId) {
        // Lấy danh sách đánh giá của xe theo Oto ID
        List<HopDongThue> hopDongThueList = HopDongThueRepository.findByOtoId(otoId);
        List<DanhGiaResponseDTO> danhGiaList = new ArrayList<>();
        for( HopDongThue hopDong : hopDongThueList) {
            // Chuyển đổi HopDongThue sang DanhGiaResponseDTO
            // Giả sử có phương thức chuyển đổi
            // DanhGiaResponseDTO danhGia = convertToDanhGiaResponseDTO(hopDong);
            // danhGiaList.add(danhGia);
            DanhGiaResponseDTO danhGia = new DanhGiaResponseDTO();
            danhGia.setTenKhachHang(hopDong.getKhachHang().getHoTen());
            danhGia.setKhachHangDanhGiaSo(hopDong.getKhachHangDanhGiaSo());
            danhGia.setKhachHangDanhGiaChu(hopDong.getKhachHangDanhGiaChu());
            danhGiaList.add(danhGia);
        }
        return danhGiaList;
    }

    public List<PhuPhi> getDanhSachPhuPhi(){
        // Lấy danh sách phụ phí
        List<PhuPhi> phuPhiList = phuPhiRepository.findAll();
        return phuPhiList;
    }

    public HopDongThue createHopDongThue(HopDongThueRequestDTO requestDTO) {
        // Lưu hợp đồng thuê xe
        HopDongThue hopDongThue = new HopDongThue();
//        hopDongThue.setId();
        hopDongThue.setOto(requestDTO.getOto());
        hopDongThue.setKhachHang(requestDTO.getKhachHang());
        hopDongThue.setThoiGianNhan(requestDTO.getThoiGianNhan());
        hopDongThue.setThoiGianTra(requestDTO.getThoiGianTra());
        hopDongThue.setTrangThai(0); // 0: Chưa xac nhan, 1: oke , 2: Hủy
        hopDongThue.setMoTa(requestDTO.getMoTa());
        hopDongThue.setGia(requestDTO.getGiaThue());
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue setCheckInTime(HopDongThue hopDongThue){
        // Cập nhật thời gian check-in
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        hopDongThue.setThoiGianNhan(currentDate);
        return hopDongThueRepository.save(hopDongThue);
    }

    public HopDongThue setCheckOutTime(HopDongThue hopDongThue){
        // Cập nhật thời gian check-out
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        hopDongThue.setThoiGianTra(currentDate);
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue cancelHopDongThue(HopDongThue hopDongThue) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã hủy
        hopDongThue.setTrangThai(2); // 2: Hủy
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue confirmHopDongThue(HopDongThue hopDongThue) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã xác nhận
        hopDongThue.setTrangThai(1); // 1: Đã xác nhận
        return hopDongThueRepository.save(hopDongThue);
    }

    public HoaDon createHoaDon(HoaDonRequestDTO requestDTO) {
        // Tạo hóa đơn từ hợp đồng thuê xe
        HoaDon hoaDon = new HoaDon();
//        hoaDon.setId(hopDongThue.getId()); // Giả sử ID của hóa đơn là ID của hợp đồng thuê
        hoaDon.setHopDongThue(requestDTO.getHopDongThue());
        hoaDon.setNhanVien(requestDTO.getNhanVien());
        hoaDon.setTongTien(requestDTO.getTongTien());
        hoaDon.setNgayThanhToan(requestDTO.getNgayThanhToan());
        hoaDon.setPhuongThucThanhToan(requestDTO.getPhuongThucThanhToan());
        return hoaDonRepository.save(hoaDon);
    }

    public void nhanTaiSanCamCo(List<TaiSanCamCoRequestDTO> list) {
        for (TaiSanCamCoRequestDTO request : list) {
            // Tạo tài sản cầm cố từ yêu cầu
            TaiSanCamCo taiSanCamCo = new TaiSanCamCo();
            taiSanCamCo.setTen(request.getTenTaiSan());
            taiSanCamCo.setLoaiTaiSan(request.getLoaiTaiSan());
            taiSanCamCo.setGia(request.getGiaTriTaiSan());
            taiSanCamCo.setMoTa(request.getMoTa());
            taiSanCamCo.setTrangThai(0); // 0: Da nhận, 1: Đã tra
            taiSanCamCo.setThoiGianNhan(request.getThoiGianNhan());
            taiSanCamCo.setKhachHang(request.getKhachHang());
            taiSanCamCo.setHopDongThue(request.getHopDongThue());
            taiSanCamCo.setNhanVienNhan(request.getNhanVienNhan());

            // Lưu tài sản cầm cố
            taiSanCamCoRepository.save(taiSanCamCo);
        }
        // Lưu tài sản cầm cố

    }

    public void luuPhuPhiDuocChon(List<PhuPhiDuocChonRequestDTO> list) {
        for (PhuPhiDuocChonRequestDTO request : list) {
            // Tạo phụ phí được chọn từ yêu cầu
            PhuPhiDuocChon phuPhiDuocChon = new PhuPhiDuocChon();
//            phuPhiDuocChon.setId(request.getId());
            phuPhiDuocChon.setPhuPhi(request.getPhuPhi());
            phuPhiDuocChon.setGia(request.getPhuPhi().getGia());
            phuPhiDuocChon.setHoaDon(request.getHoaDon());

            // Lưu phụ phí được chọn
            phuPhiDuocChonRepository.save(phuPhiDuocChon);
        }
    }

}
