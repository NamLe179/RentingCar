package com.example.car.repositories;

import com.example.car.entities.DanhSachDen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhSachDenRepository extends JpaRepository<DanhSachDen, String> {
}