package com.example.car.repositories;

import com.example.car.entities.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVienRepository extends JpaRepository<ThanhVien, String> {
    ThanhVien findByUsername(String username);
}