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
    private OtoRepository otoRepository;
    @Autowired
    private HangXeRepository hangXeRepository;
    @Autowired
    private MauXeRepository mauXeRepository;
    @Autowired
    private TienNghiRepository tienNghiRepository;
    @Autowired
    private TienNghiDuocChonRepository tienNghiDuocChonRepository;
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
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<HangXe> getAllHangXe() {
        // Lấy danh sách hãng xe
        return hangXeRepository.findAll();
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
    public List<TienNghi> getTienNghiTheoOto(String otoId) {
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

    public List<PhuPhi> getDanhSachPhuPhi(){
        // Lấy danh sách phụ phí
        List<PhuPhi> phuPhiList = phuPhiRepository.findAll();
        return phuPhiList;
    }

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

    public HopDongThue setCheckInTime(int hopDongThueId) {
        // Cập nhật thời gian check-in
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
        HopDongThue hopDongThue = hopDongThueRepository.findById(String.valueOf(hopDongThueId))
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setCheckin(new Date());

        return hopDongThueRepository.save(hopDongThue);
    }

    public HopDongThue setCheckOutTime(int hopDongThueId) {
        // Cập nhật thời gian check-out
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
        HopDongThue hopDongThue = hopDongThueRepository.findById(String.valueOf(hopDongThueId))
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setCheckout(new Date());
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue cancelHopDongThue(int hopDongThueId) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã hủy
        HopDongThue hopDongThue = hopDongThueRepository.findById(String.valueOf(hopDongThueId))
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setTrangThai(HopDongThueStatus.HUY); // 2: Hủy
        return hopDongThueRepository.save(hopDongThue);
    }
    public HopDongThue confirmHopDongThue(int hopDongThueId) {
        // Cập nhật trạng thái hợp đồng thuê xe thành đã xác nhận
        HopDongThue hopDongThue = hopDongThueRepository.findById(String.valueOf(hopDongThueId))
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại"));
        hopDongThue.setTrangThai(HopDongThueStatus.OK); // 1: Đã xác nhận
        return hopDongThueRepository.save(hopDongThue);
    }

    public HoaDon createHoaDon(HoaDonRequestDTO requestDTO) {
        // Tạo hóa đơn từ hợp đồng thuê xe
        HoaDon hoaDon = new HoaDon();
//        hoaDon.setId(hopDongThue.getId());
        hoaDon.setHopDongThue(hopDongThueRepository.findById(String.valueOf(requestDTO.getHopDongThueId()))
                .orElseThrow(() -> new RuntimeException("Hợp đồng thuê xe không tồn tại")));
        hoaDon.setNhanVien(nhanVienRepository.findById(String.valueOf(requestDTO.getNhanVienId()))
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại")));
        hoaDon.setTongTien(requestDTO.getTongTien());
        hoaDon.setNgayThanhToan(requestDTO.getNgayThanhToan());
        hoaDon.setPhuongThucThanhToan(requestDTO.getPhuongThucThanhToan());

        return hoaDonRepository.save(hoaDon);
    }

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
            taiSanCamCo.setNhanVienNhan(nhanVienRepository.findById(String.valueOf(request.getNhanVienNhanId()))
                    .orElseThrow(() -> new RuntimeException("Nhân viên nhận tài sản không tồn tại")));

            // Lưu tài sản cầm cố
            taiSanCamCoList.add(taiSanCamCoRepository.save(taiSanCamCo));
        }
        // Lưu tài sản cầm cố
        return taiSanCamCoList;
    }

    public List<TaiSanCamCo> getTaiSanCamCo() {
        // Lấy danh sách tài sản cầm cố theo hợp đồng thuê xe
        return taiSanCamCoRepository.findByTrangThai();
    }

    public TaiSanCamCo traTaiSanCamCo(TaiSanCamCoCanTraRequestDTO requestDTO) {
        // Cập nhật trạng thái tài sản cầm cố thành đã trả
        TaiSanCamCo taiSanCamCo = taiSanCamCoRepository.findById(String.valueOf(requestDTO.getTaiSanCamCoId()))
                .orElseThrow(() -> new RuntimeException("Tài sản cầm cố không tồn tại"));
        taiSanCamCo.setTrangThai(TaiSanCamCoStatus.DA_TRA); // 1: Đã trả
        taiSanCamCo.setThoiGianTra(new Date());
        taiSanCamCo.setNhanVienTra(nhanVienRepository.findById(String.valueOf(requestDTO.getNhanVienTraId()))
                .orElseThrow(() -> new RuntimeException("Nhân viên trả tài sản không tồn tại")));

        return taiSanCamCoRepository.save(taiSanCamCo);
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
