package com.example.car.repositories;

import com.example.car.entities.HopDongThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HopDongThueRepository extends JpaRepository<HopDongThue, String> {

    @Query("SELECT h FROM HopDongThue h WHERE h.oto.id = :otoId ")
    List<HopDongThue> findByOtoId(@Param("otoId") String otoId);

    @Query("SELECT h FROM HopDongThue h WHERE h.trangThai = com.example.car.enums.HopDongThueStatus.CHO_DUYET ")
    List<HopDongThue> findHopDongChoDuyet();

}