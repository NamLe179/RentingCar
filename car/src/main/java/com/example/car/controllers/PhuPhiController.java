package com.example.car.controllers;

import com.example.car.dto.PhuPhiDuocChonRequestDTO;
import com.example.car.entities.PhuPhi;
import com.example.car.services.PhuPhiService;
import com.example.car.services.RentingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phu-phi")
@CrossOrigin(origins = "*")
public class PhuPhiController {
    @Autowired
    private PhuPhiService phuPhiService;

    @GetMapping()
    public ResponseEntity<List<PhuPhi>> getDanhSachPhuPhi() {
        try {
            List<PhuPhi> phuPhiList = phuPhiService.getDanhSachPhuPhi();
            return ResponseEntity.ok(phuPhiList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/phu-phi-duoc-chon")
    public ResponseEntity<String> luuPhuPhiDuocChon(@RequestBody List<PhuPhiDuocChonRequestDTO> requestList) {
        try {
            phuPhiService.luuPhuPhiDuocChon(requestList);
            return ResponseEntity.ok("Phụ phí đã được lưu thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lưu phụ phí: " + e.getMessage());
        }
    }
}
