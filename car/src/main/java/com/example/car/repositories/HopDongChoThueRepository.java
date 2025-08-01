package com.example.car.repositories;

import com.example.car.entities.HopDongChoThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopDongChoThueRepository extends JpaRepository<HopDongChoThue, String> {
}