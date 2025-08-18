package com.example.car.repositories;

import com.example.car.entities.DanhSachDen;
import com.example.car.entities.KhachHang;
import com.example.car.enums.DanhSachDenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DanhSachDenRepository extends JpaRepository<DanhSachDen, Integer> {

    @Query("SELECT d FROM DanhSachDen d WHERE d.ngayThem BETWEEN :ngayBatDau AND :ngayKetThuc")
    List<DanhSachDen> findByThoiGian(@Param("ngayBatDau") Date ngayBatDau,@Param("ngayKetThuc") Date ngayKetThuc);

    @Query("SELECT d FROM DanhSachDen d WHERE d.trangThai = :trangThai")
    List<DanhSachDen> findByTrangThai(@Param("trangThai") DanhSachDenStatus trangThai);

    @Query("SELECT d FROM DanhSachDen d WHERE d.ngayThem BETWEEN :ngayBatDau AND :ngayKetThuc AND d.trangThai = :trangThai")
    List<DanhSachDen> findByThoiGianAndTrangThai(
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc,
            @Param("trangThai") DanhSachDenStatus trangThai
    );

    List<DanhSachDen> findByKhachHang(KhachHang khachHang);
}