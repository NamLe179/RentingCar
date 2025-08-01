package com.example.car.repositories;

import com.example.car.entities.DoiTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoiTacRepository extends JpaRepository<DoiTac, String> {
}