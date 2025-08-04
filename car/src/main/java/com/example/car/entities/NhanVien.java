package com.example.car.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nhan_vien")
public class NhanVien extends ThanhVien{
}
