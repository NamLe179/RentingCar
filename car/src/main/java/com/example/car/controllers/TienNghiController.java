package com.example.car.controllers;

import com.example.car.entities.TienNghi;
import com.example.car.services.ITienNghiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tien-nghi")
public class TienNghiController {

    private ITienNghiService tienNghiService;

    @GetMapping()
    public ResponseEntity<?> getAllTienNghi() {
        try {
            List<TienNghi> tienNghiList = tienNghiService.getAllTienNghi();
            return ResponseEntity.ok(tienNghiList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
