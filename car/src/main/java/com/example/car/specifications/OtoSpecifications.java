package com.example.car.specifications;

import com.example.car.entities.DoiTac;
import com.example.car.entities.HangXe;
import com.example.car.entities.MauXe;
import com.example.car.entities.Oto;
import com.example.car.enums.OtoStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class OtoSpecifications {

    public static Specification<Oto> equalsOtoStatus(OtoStatus otoStatus) {
        return (root, query, criteriaBuilder) -> {
            return otoStatus != null ? criteriaBuilder.equal(root.get("trangThai"), otoStatus) : null;
        };
    }

    public static Specification<Oto> hasBienSo(String bienSo) {
        return (root, query, criteriaBuilder) -> {
            return bienSo != null ? criteriaBuilder.like(root.get("bienSo"),  bienSo) : null;
        };
    }

    public static Specification<Oto> joinDoiTac() {
        return (root, query, criteriaBuilder) -> {
            Join<Oto, DoiTac> doiTacJoin = root.join("doiTac", JoinType.INNER);
            return null;
        };
    }

    public static Specification<Oto> joinMauXe() {
        return (root, query, criteriaBuilder) -> {
            Join<Oto, MauXe> mauXeJoin = root.join("mauXe", JoinType.INNER);
            return null;
        };
    }

    public static Specification<Oto> joinHangXe() {
        return (root, query, criteriaBuilder) -> {
            Join<Oto, MauXe> mauXeJoin = root.join("mauXe", JoinType.INNER);
            Join<MauXe, HangXe> mauXeHangXeJoin = mauXeJoin.join("hangXe", JoinType.INNER);
            return null;
        };
    }

    public static Specification<Oto> belongDoiTac(String doiTacId) {
        return (root, query, criteriaBuilder) -> {
            return doiTacId != null ? criteriaBuilder.equal(root.get("doiTac").get("id"), doiTacId): null;
        };
    }

    public static Specification<Oto> equalsSdtDoiTac(String sdtDoiTac) {
        return (root, query, criteriaBuilder) -> {
            return sdtDoiTac != null ? criteriaBuilder.like(root.get("doiTac").get("sdt"),  sdtDoiTac): null;
        };
    }

    public static Specification<Oto> greaterThanOrEqualToDate(Date ngayBatDau) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayBatDau) ? criteriaBuilder.greaterThanOrEqualTo(root.get("ngayTao"), ngayBatDau): null;
        };
    }

    public static Specification<Oto> lessThanOrEqualToDate(Date ngayKetThuc) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayKetThuc) ? criteriaBuilder.lessThanOrEqualTo(root.get("ngayTao"), ngayKetThuc): null;
        };
    }

    public static Specification<Oto> equalsNamSanXuat(Integer namSanXuat) {
        return (root, query, criteriaBuilder) -> {
            return null != namSanXuat ? criteriaBuilder.equal(root.get("namSanXuat"), namSanXuat): null;
        };
    }

    public static Specification<Oto> hasTruyenDong(String truyenDong) {
        return (root, query, criteriaBuilder) -> {
            return null != truyenDong ? criteriaBuilder.like(root.get("truyenDong"), truyenDong) : null;
        };
    }

    public static Specification<Oto> hasLoaiNhienLieu(String loaiNhienLieu) {
        return (root, query, criteriaBuilder) -> {
            return null != loaiNhienLieu ? criteriaBuilder.like(root.get("loaiNhienLieu"), loaiNhienLieu) : null;
        };
    }

    public static Specification<Oto> equalsMucTieuThu(Integer mucTieuThu) {
        return (root, query, criteriaBuilder) -> {
            return null != mucTieuThu ? criteriaBuilder.equal(root.get("mucTieuThu"), mucTieuThu): null;
        };
    }

    public static Specification<Oto> belongToMauXe(Integer mauXeId) {
        return (root, query, criteriaBuilder) -> {
            return null != mauXeId ? criteriaBuilder.equal(root.get("mauXe").get("id"), mauXeId): null;
        };
    }

    public static Specification<Oto> belongToHangXe(Integer hangXeId) {
        return (root, query, criteriaBuilder) -> {
            return null != hangXeId ? criteriaBuilder.equal(root.get("mauXe").get("hangXe").get("id"), hangXeId): null;
        };
    }

}