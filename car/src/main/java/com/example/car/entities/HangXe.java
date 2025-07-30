package com.example.car.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hang_xe")
@Data
public class HangXe {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(length = 200, nullable = true)
    private String moTa;
}
