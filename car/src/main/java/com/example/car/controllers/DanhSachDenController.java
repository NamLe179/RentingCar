package com.example.car.controllers;

import com.example.car.dto.DanhSachDenRequestDTO;
import com.example.car.entities.DanhSachDen;
import com.example.car.services.DanhSachDenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/danh-sach-den")
@CrossOrigin(origins = "*")
public class DanhSachDenController {
    @Autowired
    private DanhSachDenService danhSachDenService;

    @GetMapping("/all")
    public ResponseEntity<List<DanhSachDen>> getAllDanhSachDen() {
        try {
            List<DanhSachDen> danhSachDenList = danhSachDenService.getAllDanhSachDen();
            return ResponseEntity.ok(danhSachDenList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-thoi-gian")
    public ResponseEntity<List<DanhSachDen>> getDanhSachDenByThoiGian(@RequestBody DanhSachDenRequestDTO request) {
        try {
            List<DanhSachDen> danhSachDenList = danhSachDenService.getDanhSachDenByThoiGian(request);
            return ResponseEntity.ok(danhSachDenList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-trang-thai")
    public ResponseEntity<List<DanhSachDen>> getDanhSachDenByTrangThai(@RequestBody DanhSachDenRequestDTO request) {
        try {
            List<DanhSachDen> danhSachDenList = danhSachDenService.getDanhSachDenByTrangThai(request);
            return ResponseEntity.ok(danhSachDenList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/both")
    public ResponseEntity<List<DanhSachDen>> getDanhSachDenByThoiGianAndTrangThai(@RequestBody DanhSachDenRequestDTO request) {
        try {
            List<DanhSachDen> danhSachDenList = danhSachDenService.getDanhSachDenByThoiGianAndTrangThai(request);
            return ResponseEntity.ok(danhSachDenList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<DanhSachDen> createDanhSachDen(@RequestBody DanhSachDenRequestDTO request) {
        try {
            DanhSachDen danhSachDen = danhSachDenService.createDanhSachDen(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(danhSachDen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DanhSachDen> updateDanhSachDen(@RequestBody DanhSachDenRequestDTO request) {
        try {
            DanhSachDen danhSachDen = danhSachDenService.updateDanhSachDen(request);
            return ResponseEntity.ok(danhSachDen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
