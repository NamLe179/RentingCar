package com.example.car.repositories;

import com.example.car.entities.DoiTac;
import com.example.car.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoiTacRepository extends JpaRepository<DoiTac, String> {
    Optional<DoiTac> findBySdt(String sdtDoiTac);

    DoiTac findTopByIdStartingWithOrderByIdDesc(String prefix);
}