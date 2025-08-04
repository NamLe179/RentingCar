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
    @Query(value = "SELECT * FROM oto o " +
            "JOIN hop_dong_thue hdt ON o.id = hdt.oto_id " +
            "JOIN mau_xe mx ON o.mauxe_id = mx.id " +
            "JOIN hang_xe hx ON hx.id = mx.hangxe_id " +
            "JOIN dia_chi dc ON dc.id = o.diachi_id " +
            "WHERE " +
            "(:brand IS NULL OR hx.ten = :brand) AND " +
            "(:ngayNhan >= DATE_ADD(hdt.thoi_gian_tra, INTERVAL 7 DAY) OR :ngayNhan >= hdt.checkout) AND " +
            "(:ngayTra <= DATE_ADD(hdt.thoi_gian_nhan, INTERVAL 7 DAY)) AND " +
            "(:tinh = dc.tinh) AND " +
            "(:fuelType IS NULL OR o.loai_nhien_lieu = :fuelType) AND " +
            "(:transmissionType IS NULL OR o.muc_tieu_thu = :transmissionType) AND " +
            "(:seats IS NULL OR o.so_ghe = :seats) AND " +
            "o.trang_thai = 'OK'",
            nativeQuery = true)
     List<Oto> findByCriteria(@Param("ngayNhan") Date ngayNhan,
                              @Param("ngayTra") Date ngayTra,
                              @Param("tinh") String tinh,
                              @Param("brand") String brand,
                              @Param("fuelType") String fuelType,
                              @Param("transmissionType") String transmissionType,
                              @Param("seats") Integer seats);
}