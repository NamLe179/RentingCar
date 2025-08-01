package com.example.car.repositories;

import com.example.car.entities.TaiSanCamCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiSanCamCoRepository extends JpaRepository<TaiSanCamCo, String> {
}