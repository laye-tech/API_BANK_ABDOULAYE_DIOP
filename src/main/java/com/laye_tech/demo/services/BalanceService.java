package com.laye_tech.demo.services;

import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.CompteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Map;

public interface BalanceService {
    BalanceDTO create(BalanceDTO balanceDTO) throws IOException;
    BalanceDTO update(BalanceDTO balanceDTO) throws IOException;
    BalanceDTO read(Long balanceId);
    void delete(Long balanceId);

    Page<BalanceDTO> readAll(Pageable pageable,Double solde,Long compteId,String sortBy, Boolean ascending);
}
