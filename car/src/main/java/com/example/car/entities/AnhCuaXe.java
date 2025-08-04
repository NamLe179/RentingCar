package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anh_cua_xe")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnhCuaXe extends Anh{
    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 10;

    private Boolean giayToXe;

    private Boolean thumnail;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;
}
