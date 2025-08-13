package com.example.car.repositories;

import com.example.car.entities.HoaDon;
import com.example.car.entities.HopDongThue;
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

    /*
        Lấy tổng tiền hóa đơn của các hợp đồng có ô tô thuộc hợp đồng cho thuê có
        id = hopDongChoThueId và ngày checkout trong khoảng ngayBatDau và ngayKetThuc
        chưa thanh toán cho đối tác
     */
    @Query("""
           SELECT SUM(hd.tongTien) 
           FROM HoaDon hd JOIN hd.hopDongThue hdt
           JOIN hdt.oto oto
           JOIN HopDongChoThue hdct ON hdct.oto = oto
           WHERE (hdt.checkout BETWEEN :ngayBatDau AND :ngayKetThuc) 
           AND hdt.daThanhToanChoDoiTac = false AND hdct.id = :hopDongChoThueId
           AND hdt.thoiGianNhan BETWEEN hdct.ngayBatDau AND hdct.ngayKetThuc
           """)
    Float getTongTienByHDChoThueIdAndCheckoutBetween(
            @Param("hopDongChoThueId") Integer hopDongChoThueId,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc
    );
}