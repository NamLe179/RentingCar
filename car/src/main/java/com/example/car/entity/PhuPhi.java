package com.example.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "phu_phi")
@Data
public class PhuPhi {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;
}
