package com.example.car.repositories;

import com.example.car.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT h FROM HoaDon h WHERE " +
            "h.ngayThanhToan BETWEEN :ngayBatDau AND :ngayKetThuc ")
    List<HoaDon> findByThoiGian(@Param("ngayBatDau") Date ngayBatDau,@Param("ngayKetThuc") Date ngayKetThuc);

    @Query("SELECT h FROM HoaDon h WHERE " +
            "h.hopDongThue.khachHang.sdt = :sdt ")
    List<HoaDon> findByKhachHang(@Param("sdt") String sdt);

    List<HoaDon> findByHopDongThueId(int hopDongThueId);
}