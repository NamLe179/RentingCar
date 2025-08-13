package com.example.car.controllers;

import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDon;
import com.example.car.services.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/hoa-don")
@CrossOrigin(origins = "*")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;


    @PostMapping()
    public ResponseEntity<HoaDon> createHoaDon(@RequestBody HoaDonRequestDTO requestDTO) {
        try {
            HoaDon hoaDon = hoaDonService.createHoaDon(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(hoaDon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /*
        Lấy tổng tiền của các hợp đồng thuê có ngày checkin
        trong  hợp đồng cho thuê có id = hopDongThueId có hiệu lực và ngày checkout
        nằm trong khoảng thời gian ngayBatDau và ngayKetThuc
     */
    @GetMapping("/tong-tien")
    public ResponseEntity<?> getTongTienByHopDongThueList(
            @RequestParam Integer hopDongChoThueId,
            @RequestParam Date ngayBatDau,
            @RequestParam Date ngayKetThuc
    ) {
        try {
            Float tongTien = hoaDonService.getTongTienByHDChoThueIdAndCheckoutBetween(
                    hopDongChoThueId, ngayBatDau, ngayKetThuc
            );
            return ResponseEntity.ok( (tongTien != null ) ? tongTien.toString() : 0);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoaDon>> getAllHoaDons() {
        try {
            List<HoaDon> hoaDons = hoaDonService.getAllHoaDons();
            return ResponseEntity.ok(hoaDons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/thoi-gian")
    public ResponseEntity<List<HoaDon>> getHoaDonByThoiGian(@RequestBody HoaDonRequestDTO request) {
        try {
            List<HoaDon> hoaDons = hoaDonService.getHoaDonByThoiGian(request);
            return ResponseEntity.ok(hoaDons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{sdtKhachHang}")
    public ResponseEntity<List<HoaDon>> getHoaDonByKhachHang(@PathVariable String sdtKhachHang) {
        try {
            List<HoaDon> hoaDons = hoaDonService.getHoaDonByKhachHang(sdtKhachHang);
            return ResponseEntity.ok(hoaDons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
