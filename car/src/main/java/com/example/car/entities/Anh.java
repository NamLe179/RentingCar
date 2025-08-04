package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "anh")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String url;

    @Column(length = 255)
    private String ghiChu;

    @Column
    private Date ngayChup;
}
