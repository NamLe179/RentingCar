package com.example.car.converter;

import com.example.car.customExceptions.InvalidParamException;
import com.example.car.enums.HopDongChoThueStatus;
import com.example.car.enums.OtoStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class HopDongChoThueStatusConverter implements Converter<String, HopDongChoThueStatus> {


    @Override
    public HopDongChoThueStatus convert(String source) {
        // Implement custom parsing logic from String to OrderStatus
        int status = Integer.valueOf(source);
        return switch (status) {
            case 0 -> HopDongChoThueStatus.OK;
            case 1 -> HopDongChoThueStatus.HUY;
            case 2 -> HopDongChoThueStatus.HET_HAN_HOP_DONG;
            default -> throw new InvalidParamException("trang thai hop dong khong hop le " + source);
        };
//        return HopDongChoThueStatus.valueOf(source.toUpperCase()); // Example: convert to uppercase before parsing
    }
}
