package com.example.car.controllers;

import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.dto.OtoRequestDto;
import com.example.car.dto.SearchingHopDongChoThueDto;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.Oto;
import com.example.car.enums.HopDongChoThueStatus;
import com.example.car.services.IHopDongChoThueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/hop-dong-cho-thue")
public class HopDongChoThueController {

    private IHopDongChoThueService hopDongChoThueService;

    @GetMapping
    public ResponseEntity<?> getHopDongChoThue(SearchingHopDongChoThueDto searchingHopDongChoThueDto) {
        try {
            List<HopDongChoThue> hopDongChoThueList = hopDongChoThueService.findBySearchingHopDongChoThueDto(searchingHopDongChoThueDto);
            return ResponseEntity.ok(hopDongChoThueList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createHopDongChoThue(
            @Valid @RequestBody HopDongChoThueRequestDTO hopDongChoThueRequestDTO, BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map((FieldError fieldError) -> {
                            return fieldError.getField() + " " + fieldError.getDefaultMessage();
                        })
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            HopDongChoThue newHopdongChoThue = hopDongChoThueService.createHopDongChoThue(hopDongChoThueRequestDTO);
            return ResponseEntity.ok(newHopdongChoThue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/huy/{id}")
    public ResponseEntity<?> huyHopDong(@PathVariable("id") Integer hopDongChoThueId) {
        try {
            HopDongChoThue hopDongChoThue = hopDongChoThueService.thanhLyHopDongChoThue(hopDongChoThueId, HopDongChoThueStatus.HUY);
            return ResponseEntity.ok(hopDongChoThue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/thanh-ly/{id}")
    public ResponseEntity<?> thanhLyHopDong(@PathVariable("id") Integer hopDongChoThueId) {
        try {
            HopDongChoThue hopDongChoThue = hopDongChoThueService.thanhLyHopDongChoThue(hopDongChoThueId, HopDongChoThueStatus.HET_HAN_HOP_DONG);
            return ResponseEntity.ok(hopDongChoThue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOtoById(@PathVariable("id") Integer hopDongChoThueId) {
        try {
            HopDongChoThue hopDongChoThue = hopDongChoThueService.getHopDongChoThueById(hopDongChoThueId);
            return ResponseEntity.ok(hopDongChoThue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
