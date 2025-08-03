package com.example.car.repositories;

import com.example.car.entities.AnhCuaXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnhCuaXeRepository extends JpaRepository<AnhCuaXe, String> {
    @Query("SELECT a.url FROM AnhCuaXe a WHERE a.oto.id = :otoId AND a.loaiAnh = :loaiAnh ")
    List<String> findByOtoIdAndLoaiAnh(@Param("otoId") String otoId,@Param("loaiAnh") String loaiAnh);
}