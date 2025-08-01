package com.example.car.repositories;

import com.example.car.entities.MauXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauXeRepository extends JpaRepository<MauXe, String> {
}