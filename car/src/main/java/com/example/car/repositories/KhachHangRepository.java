package com.example.car.repositories;

import com.example.car.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    KhachHang findTopByIdStartingWithOrderByIdDesc(String prefix);
}