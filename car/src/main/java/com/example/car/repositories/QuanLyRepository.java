package com.example.car.repositories;

import com.example.car.entities.QuanLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuanLyRepository extends JpaRepository<QuanLy, String> {
    QuanLy findTopByIdStartingWithOrderByIdDesc(String prefix);
}