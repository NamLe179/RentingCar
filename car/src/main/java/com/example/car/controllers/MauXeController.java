package com.example.car.controllers;

import com.example.car.entities.MauXe;
import com.example.car.services.IMauXeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/mau-xe")
public class MauXeController {

    private IMauXeService mauXeService;

    @GetMapping(value = "/hang-xe-{hangXeId}")
    public ResponseEntity<List<MauXe>> getAllMauXeBelongToHangXe(
            @PathVariable("hangXeId") Integer hangXeId
    ) {
        List<MauXe> mauXeList = mauXeService.getAllMauXeBelongToHangXe(hangXeId);
        return ResponseEntity.ok(mauXeList);
    }
}
