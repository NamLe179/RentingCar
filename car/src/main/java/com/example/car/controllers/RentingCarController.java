package com.example.car.controllers;

import com.example.car.dto.*;
import com.example.car.entities.*;
import com.example.car.services.RentingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/renting")
@CrossOrigin(origins = "*")
public class RentingCarController {

    @Autowired
    private RentingCarService rentingCarService;

    @GetMapping("/hang-xe")
    public ResponseEntity<List<HangXe>> getAllHangXe() {
        try {
            List<HangXe> hangXeList = rentingCarService.getAllHangXe();
            return ResponseEntity.ok(hangXeList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/search-cars")
    public ResponseEntity<List<Oto>> searchCars(@RequestBody YeuCauXeRequestDTO request) {
        try {
            List<Oto> cars = rentingCarService.searchCars(request);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars/{otoId}/tien-nghi")
    public ResponseEntity<List<TienNghi>> getTienNghiTheoOto(@PathVariable String otoId) {
        try {
            List<TienNghi> tienNghiList = rentingCarService.getTienNghiTheoOto(otoId);
            return ResponseEntity.ok(tienNghiList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars/{otoId}/images")
    public ResponseEntity<List<String>> getAnhCuaXeTheoOto(@PathVariable String otoId) {
        try {
            List<String> images = rentingCarService.getAnhCuaXeTheoOto(Integer.parseInt(otoId));
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars/{otoId}/reviews")
    public ResponseEntity<List<DanhGiaResponseDTO>> getDanhGiaTheoOto(@PathVariable String otoId) {
        try {
            List<DanhGiaResponseDTO> reviews = rentingCarService.getDanhGiaTheoOto(otoId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/phu-phi")
    public ResponseEntity<List<PhuPhi>> getDanhSachPhuPhi() {
        try {
            List<PhuPhi> phuPhiList = rentingCarService.getDanhSachPhuPhi();
            return ResponseEntity.ok(phuPhiList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/hop-dong-thue")
    public ResponseEntity<HopDongThue> createHopDongThue(@RequestBody HopDongThueRequestDTO requestDTO) {
        try {
            HopDongThue hopDongThue = rentingCarService.createHopDongThue(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(hopDongThue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hop-dong-thue/cho-duyet")
    public ResponseEntity<List<HopDongThue>> getHopDongThueChoDuyet() {
        try {
            List<HopDongThue> hopDongList = rentingCarService.getHopDongThueChoDuyet();
            return ResponseEntity.ok(hopDongList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hop-dong-thue/checkin/{hopDongId}")
    public ResponseEntity<HopDongThue> setCheckInTime(@PathVariable int hopDongId) {
        try {
            HopDongThue updatedHopDong = rentingCarService.setCheckInTime(hopDongId);
            return ResponseEntity.ok(updatedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hop-dong-thue/checkout/{hopDongId}")
    public ResponseEntity<HopDongThue> setCheckOutTime(@PathVariable int hopDongId) {
        try {
            HopDongThue updatedHopDong = rentingCarService.setCheckOutTime(hopDongId);
            return ResponseEntity.ok(updatedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hop-dong-thue/cancel/{hopDongId}")
    public ResponseEntity<HopDongThue> cancelHopDongThue(@PathVariable int hopDongId) {
        try {
            HopDongThue cancelledHopDong = rentingCarService.cancelHopDongThue(hopDongId);
            return ResponseEntity.ok(cancelledHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hop-dong-thue/confirm/{hopDongId}")
    public ResponseEntity<HopDongThue> confirmHopDongThue(@PathVariable int hopDongId) {
        try {
            HopDongThue confirmedHopDong = rentingCarService.confirmHopDongThue(hopDongId);
            return ResponseEntity.ok(confirmedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/hoa-don")
    public ResponseEntity<HoaDon> createHoaDon(@RequestBody HoaDonRequestDTO requestDTO) {
        try {
            HoaDon hoaDon = rentingCarService.createHoaDon(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/tai-san-cam-co/nhan")
    public ResponseEntity<List<TaiSanCamCo>> nhanTaiSanCamCo(@RequestBody List<TaiSanCamCoRequestDTO> requestList) {
        try {
            List<TaiSanCamCo> taiSanCamCoList = rentingCarService.nhanTaiSanCamCo(requestList);
            return ResponseEntity.status(HttpStatus.CREATED).body(taiSanCamCoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/tai-san-cam-co")
    public ResponseEntity<List<TaiSanCamCo>> getTaiSanCamCo() {
        try {
            List<TaiSanCamCo> taiSanList = rentingCarService.getTaiSanCamCo();
            return ResponseEntity.ok(taiSanList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/tai-san-cam-co/tra")
    public ResponseEntity<TaiSanCamCo> traTaiSanCamCo(@RequestBody TaiSanCamCoCanTraRequestDTO requestDTO) {
        try {
            TaiSanCamCo taiSanCamCo = rentingCarService.traTaiSanCamCo(requestDTO);
            return ResponseEntity.ok(taiSanCamCo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/phu-phi-duoc-chon")
    public ResponseEntity<String> luuPhuPhiDuocChon(@RequestBody List<PhuPhiDuocChonRequestDTO> requestList) {
        try {
            rentingCarService.luuPhuPhiDuocChon(requestList);
            return ResponseEntity.ok("Phụ phí đã được lưu thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lưu phụ phí: " + e.getMessage());
        }
    }
}
