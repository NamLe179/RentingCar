package com.example.car.controllers;

import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.OtoRequestDto;
import com.example.car.dto.SearchingOtoDto;
import com.example.car.entities.Oto;
import com.example.car.enums.OtoStatus;
import com.example.car.services.IOtoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/cars")
public class OtoController {

    IOtoService otoService;

    @GetMapping("")
    public ResponseEntity<?> getOto(
            SearchingOtoDto searchingOtoDto
    ) {
        try {
            searchingOtoDto.setTrangThai(OtoStatus.CHO_DUYET);
            List<Oto> otoList = otoService.findBySearchingOtoDto(searchingOtoDto);
            return ResponseEntity.ok(otoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/cho-duyet")
    public ResponseEntity<?> getOtoChoDuyet() {
        try {
            List<Oto> otoList = otoService.getOtoChoDuyet();
            return ResponseEntity.ok(otoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping()
    public ResponseEntity<?> createCar(@Valid @RequestBody OtoRequestDto otoRequestDto, BindingResult result) {
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
            Oto newOto = otoService.createOto(otoRequestDto);
            return ResponseEntity.ok(newOto);
        } catch (InvalidParamException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(
            @PathVariable("id") Integer id,
            @RequestBody OtoRequestDto otoRequestDto,
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
            if(null == otoRequestDto.getDiaChi().getId()) {
                return ResponseEntity.badRequest().body("Thieu dia chi id");
            }
            Oto oto = otoService.updateOto(id, otoRequestDto);
            return ResponseEntity.ok(oto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOtoById(@PathVariable("id") Integer otoId) {
        try {
            Oto existingOto = otoService.getOtoById(otoId);
            return ResponseEntity.ok(existingOto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/doi-tac/{doiTacId}")
    public ResponseEntity<?> getOtoByDoiTacId(
            @PathVariable("doiTacId") String doiTacId,
            SearchingOtoDto searchingOtoDto
    ) {
        try {
            searchingOtoDto.setDoiTacId(doiTacId);
            List<Oto> otoList = otoService.findBySearchingOtoDto(searchingOtoDto);
            return ResponseEntity.ok(otoList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
