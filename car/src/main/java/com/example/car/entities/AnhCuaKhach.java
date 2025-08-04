package com.example.car.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anh_cua_khach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnhCuaKhach extends Anh{
    @ManyToOne
    @JoinColumn(name = "HopDongThueId", nullable = false)
    private HopDongThue hopDongThue;
}
