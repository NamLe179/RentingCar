package com.example.car.repositories;

import com.example.car.entities.Oto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OtoRepository extends JpaRepository<Oto, String> {
    // Các phương thức truy vấn tùy chỉnh có thể được định nghĩa ở đây
    // Ví dụ: tìm kiếm xe theo tiêu chí
    @Query("SELECT o FROM Oto o " +
            "JOIN HopDongThue hdt ON o.id = hdt.oto.id " +
            "JOIN MauXe mx ON o.mauXe.id = mx.id " +
            "JOIN HangXe hx ON hx.id = mx.hangXe.id" +
            "JOIN DiaChi dc ON dc.id = o.diaChi.id " +
            "WHERE " +
            "(:brand IS NULL OR hx.brand = :brand) AND  " +
            "(:ngayNhan >= (DATE_ADD(hdt.thoiGiantra, INTERVAL 7 DAY)) OR :ngayNhan >= hdt.checkout) AND " +
            "(:ngayTra <= (DATE_ADD(hdt.thoiGianNhan, INTERVAL 7 DAY))) AND " +
            "(:tinh = dc.tinh) AND " +
            "(:fuelType IS NULL OR o.loaiNhienLieu = :fuelType) AND " +
            "(:transmissionType IS NULL OR o.mucTieuThu = :transmissionType) AND " +
            "(:seats IS NULL OR o.soGhe = :seats) AND " +
            "o.trangThai = com.example.car.enums.OtoStatus.OK ")
     List<Oto> findByCriteria(@Param("ngayNhan") Date ngayNhan,
                              @Param("ngayTra") Date ngayTra,
                              @Param("tinh") String tinh,
                              @Param("brand") String brand,
                              @Param("fuelType") String fuelType,
                              @Param("transmissionType") String transmissionType,
                              @Param("seats") Integer seats);
}