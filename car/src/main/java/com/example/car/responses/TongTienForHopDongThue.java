package com.example.car.responses;

import com.example.car.entities.HopDongThue;

public record TongTienForHopDongThue(
        HopDongThue hopDongThue,
        Double tongTien
) {
}
