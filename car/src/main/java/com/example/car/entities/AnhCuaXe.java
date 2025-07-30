package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "anh_cua_xe")
@Data
public class AnhCuaXe extends Anh{
    @Column(length = 50, nullable = false)
    private String loaiAnh;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;
}
