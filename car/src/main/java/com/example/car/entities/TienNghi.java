package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tien_nghi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TienNghi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(length = 200, nullable = true)
    private String moTa;
}
