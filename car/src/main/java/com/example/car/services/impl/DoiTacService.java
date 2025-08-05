package com.example.car.services.impl;

import com.example.car.entities.DoiTac;
import com.example.car.repositories.DoiTacRepository;
import com.example.car.services.IDoiTacService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoiTacService implements IDoiTacService {

    private DoiTacRepository doiTacRepository;

    @Override
    public DoiTac getDoiTacBySdt(String sdtDoiTac) {
        return doiTacRepository.findBySdt(sdtDoiTac).orElse(null);
    }
}
