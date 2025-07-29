package com.example.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "dia_chi")
@Data
public class DiaChi {
    @Id
    @Column(length = 10, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String tinh;

    @Column(length = 100, nullable = false)
    private String quan;

    @Column(length = 100, nullable = false)
    private String phuong;

    @Column(length = 100, nullable = false)
    private String soNha;
}
