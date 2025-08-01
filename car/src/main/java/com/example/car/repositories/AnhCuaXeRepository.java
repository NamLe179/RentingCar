package com.example.car.repositories;

import com.example.car.entities.AnhCuaXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnhCuaXeRepository extends JpaRepository<AnhCuaXe, String> {
}