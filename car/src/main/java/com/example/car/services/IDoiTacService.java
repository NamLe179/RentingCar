package com.example.car.services;

import com.example.car.entities.DoiTac;
import org.springframework.stereotype.Service;

@Service
public interface IDoiTacService {
    DoiTac getDoiTacBySdt(String sdtDoiTac);
}
