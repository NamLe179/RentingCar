package com.example.car.repositories;

import com.example.car.entities.PhuPhiDuocChon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhuPhiDuocChonRepository extends JpaRepository<PhuPhiDuocChon, String> {
}