package com.example.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "quan_ly")
@Data
public class QuanLy extends ThanhVien{
}
