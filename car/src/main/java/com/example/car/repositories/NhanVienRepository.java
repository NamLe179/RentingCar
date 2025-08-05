package com.example.car.repositories;

import com.example.car.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    NhanVien findTopByIdStartingWithOrderByIdDesc(String prefix);
}