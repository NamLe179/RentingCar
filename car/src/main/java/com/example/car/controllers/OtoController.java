package com.example.car.controllers;

import com.example.car.dto.OtoDto;
import com.example.car.entities.Oto;
import com.example.car.services.IOtoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/cars")
public class OtoController {

    IOtoService otoService;

    @PostMapping()
    public ResponseEntity<?> createCar(@Valid @RequestBody OtoDto otoDto, BindingResult result) {
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
            Oto newOto = otoService.createOto(otoDto);
            return ResponseEntity.ok(newOto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
