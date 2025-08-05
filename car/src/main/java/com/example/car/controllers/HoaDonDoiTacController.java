package com.example.car.controllers;

import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.entities.HoaDonDoiTac;
import com.example.car.services.IHoaDonDoiTacService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hoa-don-doi-tac")
public class HoaDonDoiTacController {

    private IHoaDonDoiTacService hoaDonDoiTacService;

    @PostMapping
    public ResponseEntity<?> createHoaDonDoiTac(
            @Valid @RequestBody HoaDonRequestDTO hoaDonRequestDTO,
            BindingResult result
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
            HoaDonDoiTac hoaDonDoiTac = hoaDonDoiTacService.createHoaDonDoiTac(hoaDonRequestDTO);
            return ResponseEntity.ok(hoaDonDoiTac);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
