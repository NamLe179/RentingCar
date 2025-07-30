package com.example.car.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tien_nghi")
@Data
public class TienNghi {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(length = 200, nullable = true)
    private String moTa;
}
