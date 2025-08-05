package com.example.car.responses;

import com.example.car.entities.MauXe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauXeResponse {
    private Integer id;
    private String tenMauXe;
    private Integer soGhe;
    private Integer hangXeId;

    public static MauXeResponse fromMauXe(MauXe mauXe) {
        return MauXeResponse.builder()
                .hangXeId(mauXe.getHangXe().getId())
                .id(mauXe.getId())
                .tenMauXe(mauXe.getTen())
                .soGhe(mauXe.getSoGhe())
                .build();
    }
}
