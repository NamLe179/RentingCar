package com.example.car.repositories;

import com.example.car.entities.HangXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangXeRepository extends JpaRepository<HangXe, Integer> {
}