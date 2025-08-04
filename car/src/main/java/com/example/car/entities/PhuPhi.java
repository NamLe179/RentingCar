package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phu_phi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhuPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;
}
