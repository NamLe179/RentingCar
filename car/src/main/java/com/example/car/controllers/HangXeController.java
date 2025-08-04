package com.example.car.controllers;


import com.example.car.entities.HangXe;
import com.example.car.services.IHangXeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/hang-xe")
public class HangXeController {

    private IHangXeService hangXeService;

    @GetMapping
    public ResponseEntity<List<HangXe>> getAllHangXe() {
        List<HangXe> hangXeList = hangXeService.getAllHangXe();
        return ResponseEntity.ok(hangXeList);
    }

}
