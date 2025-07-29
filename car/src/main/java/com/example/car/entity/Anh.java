package com.example.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "anh")
@Data
public class Anh {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 200, nullable = false)
    private String url;

    @Column(length = 255, nullable = true)
    private String ghiChu;

    @Column(nullable = true)
    private Date ngayChup;
}
