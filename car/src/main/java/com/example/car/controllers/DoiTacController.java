package com.example.car.controllers;

import com.example.car.entities.DoiTac;
import com.example.car.services.IDoiTacService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/doi-tac")
public class DoiTacController {

    private IDoiTacService doiTacService;

    @GetMapping("/{sdtDoiTac}")
    public ResponseEntity<?> getDoiTacById(
            @PathVariable("sdtDoiTac") String sdtDoiTac
    ) {
        try {
            DoiTac existingDoiTac = doiTacService.getDoiTacBySdt(sdtDoiTac);
            return ResponseEntity.ok(existingDoiTac);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
