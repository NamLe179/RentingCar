package com.example.car.repositories;

import com.example.car.entities.TienNghi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TienNghiRepository extends JpaRepository<TienNghi, String> {
}