package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
