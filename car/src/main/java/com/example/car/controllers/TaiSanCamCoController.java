package com.example.car.controllers;

import com.example.car.dto.TaiSanCamCoCanTraRequestDTO;
import com.example.car.dto.TaiSanCamCoRequestDTO;
import com.example.car.entities.TaiSanCamCo;
import com.example.car.services.TaiSanCamCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tai-san-cam-co")
@CrossOrigin(origins = "*")
public class TaiSanCamCoController {
    @Autowired
    private TaiSanCamCoService taiSanCamCoService;

    @PostMapping("/nhan")
    public ResponseEntity<List<TaiSanCamCo>> nhanTaiSanCamCo(@RequestBody List<TaiSanCamCoRequestDTO> requestList) {
        try {
            List<TaiSanCamCo> taiSanCamCoList = taiSanCamCoService.nhanTaiSanCamCo(requestList);
            return ResponseEntity.status(HttpStatus.CREATED).body(taiSanCamCoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{doiTacId}")
    public ResponseEntity<List<TaiSanCamCo>> getTaiSanCamCo(@PathVariable String doiTacId) {
        try {
            List<TaiSanCamCo> taiSanList = taiSanCamCoService.getTaiSanCamCo(doiTacId);
            return ResponseEntity.ok(taiSanList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/tra")
    public ResponseEntity<TaiSanCamCo> traTaiSanCamCo(@RequestBody TaiSanCamCoCanTraRequestDTO requestDTO) {
        try {
            TaiSanCamCo taiSanCamCo = taiSanCamCoService.traTaiSanCamCo(requestDTO);
            return ResponseEntity.ok(taiSanCamCo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
