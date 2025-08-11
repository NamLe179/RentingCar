package com.example.car.controllers;

import com.example.car.dto.HopDongChoThueRequestDTO;
import com.example.car.dto.OtoRequestDto;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.Oto;
import com.example.car.services.IHopDongChoThueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hop-dong-cho-thue")
public class HopDongChoThueController {

    private IHopDongChoThueService hopDongChoThueService;

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

    @PutMapping("/huy-hop-dong/{id}")
    public ResponseEntity<?> huyHopDong(@PathVariable("id") Integer hopDongChoThueId) {
        try {
            HopDongChoThue hopDongChoThue = hopDongChoThueService.getHopDongChoThueById(hopDongChoThueId);
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
