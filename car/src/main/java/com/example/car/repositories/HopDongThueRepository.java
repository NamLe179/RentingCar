package com.example.car.repositories;

import com.example.car.entities.HoaDonDoiTac;
import com.example.car.entities.HopDongThue;
import com.example.car.responses.TongTienForHopDongThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HopDongThueRepository extends JpaRepository<HopDongThue, Integer> {

    @Query("SELECT h FROM HopDongThue h WHERE h.oto.id = :otoId ")
    List<HopDongThue> findByOtoId(@Param("otoId") int otoId);

    @Query("SELECT h FROM HopDongThue h WHERE h.trangThai = com.example.car.enums.HopDongThueStatus.CHO_DUYET ")
    List<HopDongThue> findHopDongChoDuyet();

    @Query("SELECT h FROM HopDongThue h WHERE " +
            "h.trangThai = com.example.car.enums.HopDongThueStatus.CHO_DUYET " +
            "AND h.thoiGianNhan >= :ngayBatDau" +
            " AND h.thoiGianTra <= :ngayKetThuc ")
    List<HopDongThue> findHopDongChoDuyetByThoiGian(@Param("ngayBatDau") Date ngayBatDau, @Param("ngayKetThuc") Date ngayKetThuc);

    /*
        Lấy danh sách hợp đồng thuê và tổng tiền hóa đơn chưa được thanh toán cho đối tác
        có thời gian checkout trong khoảng thời gian ngayBatDau và ngayKetThuc
        và hợp đồng thuê thuê xe ô tô thuộc hợp đồng cho thuê có id = hopDongChoThueId
     */
    @Query("""
        SELECT  NEW com.example.car.responses.TongTienForHopDongThue(
        hdt, ( SELECT sum(hd.tongTien) FROM HoaDon hd WHERE hd.hopDongThue = hdt))
        FROM HopDongThue hdt
        JOIN hdt.oto oto
        JOIN HopDongChoThue hdct ON hdct.oto = oto
        WHERE hdct.id = :hopDongChoThueId AND hdt.daThanhToanChoDoiTac = false
        AND hdt.checkout BETWEEN :ngayBatDau AND :ngayKetThuc 
        AND hdt.thoiGianNhan BETWEEN hdct.ngayBatDau AND hdct.ngayKetThuc
    """)
    List<TongTienForHopDongThue> getTongTienHoaDonAndHDThueByHDChoThueIdAndCheckOutBetween(
            @Param("hopDongChoThueId") Integer hopDongChoThueId,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc
    );

    /*
        Lấy danh sách hợp đồng thuê chưa được thanh toán cho đối tác
        có thời gian checkout trong khoảng thời gian ngayBatDau và ngayKetThuc
        và hợp đồng thuê thuê xe ô tô thuộc hợp đồng cho thuê có id = hopDongChoThueId
     */
    @Query("SELECT hdt FROM HopDongThue hdt " +
            "JOIN hdt.oto oto " +
            "JOIN HopDongChoThue hdct ON hdct.oto = oto " +
            "WHERE hdt.daThanhToanChoDoiTac = false " +
            "AND hdct.id = :hopDongChoThueId " +
            "AND hdt.checkout BETWEEN :ngayBatDau AND :ngayKetThuc " +
            "AND hdt.thoiGianNhan BETWEEN hdct.ngayBatDau AND hdct.ngayKetThuc")
    List<HopDongThue> findHDThueChuaThanhToanChoDTByHDChoThueIdAndCheckoutBetween(
            @Param("hopDongChoThueId") Integer hopDongChoThueId,
            @Param("ngayBatDau") Date ngayBatDau,
            @Param("ngayKetThuc") Date ngayKetThuc);

    /*
        Lấy danh sách hợp đồng thuê đã được thanh toán cho đối tác có hóa đơn đối tác = hoaDonDoiTac
     */
    @Query("SELECT hdt FROM HopDongThue hdt WHERE hdt.hoaDonDoiTac = :hoaDonDoiTac")
    List<HopDongThue> findByHoaDonDoiTacId(@Param("hoaDonDoiTac") HoaDonDoiTac hoaDonDoiTac);
}