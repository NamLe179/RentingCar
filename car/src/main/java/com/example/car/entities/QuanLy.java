package com.example.car.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "quan_ly")
@Data
public class QuanLy extends ThanhVien{
}
