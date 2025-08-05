package com.example.car.repositories;

import com.example.car.entities.TienNghiDuocChon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TienNghiDuocChonRepository extends JpaRepository<TienNghiDuocChon, Integer> {
    @Query("SELECT t FROM TienNghiDuocChon t WHERE t.oto.id = :otoId ")
    List<TienNghiDuocChon> findByOtoId(@Param("otoId") Integer otoId);


}