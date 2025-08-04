package com.example.car.repositories;

import com.example.car.entities.PhuPhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhuPhiRepository extends JpaRepository<PhuPhi, Integer> {
}