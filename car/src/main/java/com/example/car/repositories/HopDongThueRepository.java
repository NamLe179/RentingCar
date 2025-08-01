package com.example.car.repositories;

import com.example.car.entities.HopDongThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopDongThueRepository extends JpaRepository<HopDongThue, String> {
}