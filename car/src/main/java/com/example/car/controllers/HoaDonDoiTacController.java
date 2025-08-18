package com.example.car.controllers;

import com.example.car.customExceptions.DataNotFoundException;
import com.example.car.customExceptions.InvalidParamException;
import com.example.car.dto.HoaDonDoiTacRequestDto;
import com.example.car.dto.HoaDonRequestDTO;
import com.example.car.dto.SearchingHoaDonDoiTacDto;
import com.example.car.entities.HoaDonDoiTac;
import com.example.car.services.IHoaDonDoiTacService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hoa-don-doi-tac")
public class HoaDonDoiTacController {

    private IHoaDonDoiTacService hoaDonDoiTacService;

    @GetMapping("")
    public ResponseEntity<?> getHoaDonByDoiTac(
            SearchingHoaDonDoiTacDto searchingHoaDonDoiTacDto) {

        try {
            List<HoaDonDoiTac> hoaDonDoiTacList = hoaDonDoiTacService.findBySearchingHoaDonDoiTacDto(searchingHoaDonDoiTacDto);
            return ResponseEntity.ok(hoaDonDoiTacList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/doi-tac/{doiTacId}")
    public ResponseEntity<?> getHoaDonByDoiTac(
            @Valid @PathVariable("doiTacId") String doiTacId,
            SearchingHoaDonDoiTacDto searchingHoaDonDoiTacDto) {
        searchingHoaDonDoiTacDto.setDoiTacId(doiTacId);

        try {
            List<HoaDonDoiTac> hoaDonDoiTacList = hoaDonDoiTacService.findBySearchingHoaDonDoiTacDto(searchingHoaDonDoiTacDto);
            return ResponseEntity.ok(hoaDonDoiTacList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> createHoaDonDoiTac(
            @Valid @RequestBody HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto,
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
            HoaDonDoiTac hoaDonDoiTac = hoaDonDoiTacService.createHoaDonDoiTac(hoaDonDoiTacRequestDto);
            return ResponseEntity.ok(hoaDonDoiTac);
        } catch (DataNotFoundException | InvalidParamException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHoaDonDoiTac(
            @Valid @PathVariable("id") Integer hoaDonDoiTacId,
            @Valid @RequestBody HoaDonDoiTacRequestDto hoaDonDoiTacRequestDto,
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
            HoaDonDoiTac hoaDonDoiTac = hoaDonDoiTacService.updateHoaDonDoiTac(hoaDonDoiTacId, hoaDonDoiTacRequestDto);
            return ResponseEntity.ok(hoaDonDoiTac);
        } catch (DataNotFoundException | InvalidParamException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHoaDonDoiTac(@Valid @PathVariable("id") Integer id) {
        try {
            hoaDonDoiTacService.deleteHoaDonDoiTac(id);
            return ResponseEntity.ok().body("Delete Hoa Don Doi Tac with id : "+id+" successfully");
        } catch (DataNotFoundException | InvalidParamException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
