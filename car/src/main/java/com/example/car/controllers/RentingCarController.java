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

    @GetMapping("get-all-cars")
    public ResponseEntity<List<Oto>> getAllCars() {
        try {
            List<Oto> cars = rentingCarService.getAllCars();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars/{otoId}/tien-nghi")
    public ResponseEntity<List<TienNghi>> getTienNghiTheoOto(@PathVariable Integer otoId) {
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
    public ResponseEntity<List<DanhGiaResponseDTO>> getDanhGiaTheoOto(@PathVariable int otoId) {
        try {
            List<DanhGiaResponseDTO> reviews = rentingCarService.getDanhGiaTheoOto(otoId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
