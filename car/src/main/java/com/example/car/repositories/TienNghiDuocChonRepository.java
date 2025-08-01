package com.example.car.repositories;

import com.example.car.entities.TienNghiDuocChon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TienNghiDuocChonRepository extends JpaRepository<TienNghiDuocChon, String> {
}