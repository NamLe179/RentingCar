package com.example.car.specifications;

import com.example.car.entities.DoiTac;
import com.example.car.entities.HopDongChoThue;
import com.example.car.entities.Oto;
import com.example.car.enums.HopDongChoThueStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;

public class HopDongChoThueSpecifications {

    public static Specification<HopDongChoThue> joinOto() {
        return (root, query, criteriaBuilder) -> {
            Join<HopDongChoThue, Oto> otoJoin = root.join("oto", JoinType.INNER);
            return null;
        };
    }

    public static Specification<HopDongChoThue> joinDoiTac() {
        return (root, query, criteriaBuilder) -> {
            Join<HopDongChoThue, Oto> otoJoin = root.join("oto", JoinType.INNER);
            Join<Oto, DoiTac> doiTacJoin = otoJoin.join("doiTac", JoinType.INNER);
            return null;
        };
    }

    public static Specification<HopDongChoThue> equalsSdtDoiTac(String sdtDoiTac) {
        return (root, query, criteriaBuilder) -> {
            return sdtDoiTac != null ? criteriaBuilder.like(root.get("oto").get("doiTac").get("sdt"),  sdtDoiTac): null;
        };
    }

    public static Specification<HopDongChoThue> belongDoiTac(String doiTacId) {
        return (root, query, criteriaBuilder) -> {
            return doiTacId != null ? criteriaBuilder.equal(root.get("oto").get("doiTac").get("id"), doiTacId): null;
        };
    }

    public static Specification<HopDongChoThue> hasBienSo(String bienSo) {
        return (root, query, criteriaBuilder) -> {
            return bienSo != null ? criteriaBuilder.like(root.get("oto").get("bienSo"),  bienSo) : null;
        };
    }

    public static Specification<HopDongChoThue> equalsHopDongChoThueStatus(HopDongChoThueStatus hopDongChoThueStatus) {
        return (root, query, criteriaBuilder) -> {
            return hopDongChoThueStatus != null ? criteriaBuilder.equal(root.get("trangThai"), hopDongChoThueStatus) : null;
        };
    }

    public static Specification<HopDongChoThue> greaterThanOrEqualToDate(Date ngayBatDau) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayBatDau) ? criteriaBuilder.greaterThanOrEqualTo(root.get("ngayTao"), ngayBatDau): null;
        };
    }

    public static Specification<HopDongChoThue> lessThanOrEqualToDate(Date ngayKetThuc) {
        return (root, query, criteriaBuilder) -> {
            return (null != ngayKetThuc) ? criteriaBuilder.lessThanOrEqualTo(root.get("ngayTao"), ngayKetThuc): null;
        };
    }

}
