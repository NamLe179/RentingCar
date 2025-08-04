package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tien_nghi_duoc_chon")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TienNghiDuocChon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "TienNghiId", nullable = false)
    private TienNghi tienNghi;
}
