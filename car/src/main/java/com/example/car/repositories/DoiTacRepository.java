package com.example.car.repositories;

import com.example.car.entities.DoiTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoiTacRepository extends JpaRepository<DoiTac, String> {
    Optional<DoiTac> findBySdt(String sdtDoiTac);
}