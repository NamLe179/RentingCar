package com.example.car.converter;

import com.example.car.customExceptions.InvalidParamException;
import com.example.car.enums.OtoStatus;
import org.springframework.core.convert.converter.Converter;

public class OtoStatusConverter implements Converter<String, OtoStatus> {

    @Override
    public OtoStatus convert(String source) {
        // Implement custom parsing logic from String to OrderStatus

        int status = Integer.valueOf(source);
        return switch (status) {
            case 0 -> OtoStatus.CHO_DUYET;
            case 1 -> OtoStatus.OK;
            case 2 -> OtoStatus.SUA_CHUA;
            case 3 -> OtoStatus.HET_HAN_HOP_DONG;
            default -> throw new InvalidParamException("trang thai o to khong hop le " + source);
        };

//        return OtoStatus.valueOf(source.toUpperCase()); // Example: convert to uppercase before parsing
    }
}
