package com.example.car.specifications;

import com.example.car.entities.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class HoaDonDoiTacSpecifications {

    public static Specification<HoaDonDoiTac> joinDoiTac() {
        return (root, query, criteriaBuilder) -> {
            Join<HoaDonDoiTac, HopDongChoThue> hopDongChoThueJoin = root.join("hopDongChoThue", JoinType.INNER);
            Join<HopDongChoThue, Oto> otoJoin = hopDongChoThueJoin.join("oto", JoinType.INNER);
            Join<Oto, DoiTac> doiTacJoin = otoJoin.join("doiTac", JoinType.INNER);
            return null;
        };
    }

    public static Specification<HoaDonDoiTac> greaterThanOrEqualToDate(Date ngayBatDau) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayBatDau) ? criteriaBuilder.greaterThanOrEqualTo(root.get("ngayThanhToan"), ngayBatDau): null;
        };
    }

    public static Specification<HoaDonDoiTac> lessThanOrEqualToDate(Date ngayKetThuc) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayKetThuc) ? criteriaBuilder.lessThanOrEqualTo(root.get("ngayThanhToan"), ngayKetThuc): null;
        };
    }

    public static Specification<HoaDonDoiTac> hasPhuongThucThanhToan(String phuongThucThanhToan) {
        return (root, query, criteriaBuilder) -> {
            return phuongThucThanhToan != null ? criteriaBuilder.like(root.get("phuongThucThanhToan"),  "%" + phuongThucThanhToan + "%") : null;
        };
    }

    public static Specification<HoaDonDoiTac> belongDoiTac(String doiTacId) {
        return (root, query, criteriaBuilder) -> {
            return doiTacId != null ? criteriaBuilder.equal(root.get("hopDongChoThue")
                    .get("oto").get("doiTac").get("id"), doiTacId): null;
        };
    }

}
