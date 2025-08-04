package com.example.car.repositories;

import com.example.car.entities.HoaDonDoiTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonDoiTacRepository extends JpaRepository<HoaDonDoiTac, Integer> {
}