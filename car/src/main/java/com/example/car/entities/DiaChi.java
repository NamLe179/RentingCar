package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "dia_chi")
@Data
@Builder
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String tinh;

    @Column(length = 100, nullable = false)
    private String quan;

    @Column(length = 100, nullable = false)
    private String phuong;

    @Column(length = 100, nullable = false)
    private String soNha;
}
