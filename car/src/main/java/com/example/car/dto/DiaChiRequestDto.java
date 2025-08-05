package com.example.car.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaChiRequestDto {
    private Integer id;
    @NotBlank
    private String tinh, quan, phuong, soNha;
}
