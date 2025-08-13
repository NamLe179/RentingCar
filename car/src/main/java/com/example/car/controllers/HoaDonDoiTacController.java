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

    /*
        lấy danh sách hóa đơn đối tác theo các tiêu chí:
        ngayBatDau, ngayKetThuc, phuongThucThanhToan
     */
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

    /*
        lấy danh sách hóa đơn đối tác theo các tiêu chí:
        ngayBatDau, ngayKetThuc, phuongThucThanhToan thuộc đối tác có id = doiTacId
     */
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

    /*
        thêm mới hóa đơn đối tác sao cho tổng tiền của
        hóa đơn đối tác = phần trăm đối tác * tổng tiền hóa đơn của các hợp đồng thuê
     */
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

    /*
        cập nhật hóa đơn đối tác sao cho tổng tiền của
        hóa đơn đối tác = phần trăm đối tác * tổng tiền hóa đơn của các hợp đồng thuê
     */
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

    /*
        xóa hóa đơn đối tác 
     */
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
