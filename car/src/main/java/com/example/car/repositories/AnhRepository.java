package com.example.car.repositories;

import com.example.car.entities.Anh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnhRepository extends JpaRepository<Anh, Integer> {
}