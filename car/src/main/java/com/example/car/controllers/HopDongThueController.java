package com.example.car.controllers;

import com.example.car.dto.HopDongThueRequestDTO;
import com.example.car.entities.HopDongThue;
import com.example.car.responses.TongTienForHopDongThue;
import com.example.car.services.HopDongThueService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/hop-dong-thue")
@CrossOrigin(origins = "*")
public class HopDongThueController {
    @Autowired
    private HopDongThueService hopDongThueService;

    @PostMapping()
    public ResponseEntity<HopDongThue> createHopDongThue(@RequestBody HopDongThueRequestDTO requestDTO) {
        try {
            HopDongThue hopDongThue = hopDongThueService.createHopDongThue(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(hopDongThue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cho-duyet")
    public ResponseEntity<List<HopDongThue>> getHopDongThueChoDuyet() {
        try {
            List<HopDongThue> hopDongList = hopDongThueService.getHopDongThueChoDuyet();
            return ResponseEntity.ok(hopDongList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cho-duyet/thoi-gian")
    public ResponseEntity<List<HopDongThue>> getHopDongThueChoDuyetByThoiGian(@RequestBody HopDongThueRequestDTO requestDTO) {
        try {
            List<HopDongThue> hopDongList = hopDongThueService.getHopDongThueChoDuyetByThoiGian(requestDTO);
            return ResponseEntity.ok(hopDongList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/checkin/{hopDongId}")
    public ResponseEntity<HopDongThue> setCheckInTime(@PathVariable int hopDongId) {
        try {
            HopDongThue updatedHopDong = hopDongThueService.setCheckInTime(hopDongId);
            return ResponseEntity.ok(updatedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/checkout/{hopDongId}")
    public ResponseEntity<HopDongThue> setCheckOutTime(@PathVariable int hopDongId) {
        try {
            HopDongThue updatedHopDong = hopDongThueService.setCheckOutTime(hopDongId);
            return ResponseEntity.ok(updatedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cancel/{hopDongId}")
    public ResponseEntity<HopDongThue> cancelHopDongThue(@PathVariable int hopDongId) {
        try {
            HopDongThue cancelledHopDong = hopDongThueService.cancelHopDongThue(hopDongId);
            return ResponseEntity.ok(cancelledHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/confirm/{hopDongId}")
    public ResponseEntity<HopDongThue> confirmHopDongThue(@PathVariable int hopDongId) {
        try {
            HopDongThue confirmedHopDong = hopDongThueService.confirmHopDongThue(hopDongId);
            return ResponseEntity.ok(confirmedHopDong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/chua-thanh-toan-cho-dt")
    public ResponseEntity<?> getHopDongThueChuaThanhToanChoDoiTac(
            @RequestParam Integer hopDongChoThueId,
            @RequestParam Date ngayBatDau,
            @RequestParam Date ngayKetThuc
            ) {
        try {
            List<TongTienForHopDongThue> hopDongThueList = hopDongThueService.getHopDonThueChuaThanhToanChoDoiTac(
                    hopDongChoThueId, ngayBatDau, ngayKetThuc
            );
            return ResponseEntity.ok(hopDongThueList);
        } catch (Exception e) {
//            e.printStackTrace();;
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
