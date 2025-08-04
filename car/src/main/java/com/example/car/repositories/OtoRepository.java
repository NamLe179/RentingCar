package com.example.car.repositories;

import com.example.car.entities.Oto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtoRepository extends JpaRepository<Oto, Integer> {
}