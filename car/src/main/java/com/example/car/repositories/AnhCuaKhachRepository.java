package com.example.car.repositories;

import com.example.car.entities.AnhCuaKhach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnhCuaKhachRepository extends JpaRepository<AnhCuaKhach, Integer> {
}