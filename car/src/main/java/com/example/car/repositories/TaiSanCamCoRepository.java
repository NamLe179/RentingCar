package com.example.car.repositories;

import com.example.car.entities.TaiSanCamCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiSanCamCoRepository extends JpaRepository<TaiSanCamCo, String> {
    @Query("SELECT t FROM TaiSanCamCo t WHERE t.trangThai = com.example.car.enums.TaiSanCamCoStatus.DA_NHAN ")
    List<TaiSanCamCo> findByTrangThai();

}