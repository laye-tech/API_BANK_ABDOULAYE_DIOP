package com.laye_tech.demo.services.ServImple;

import com.laye_tech.demo.mappers.BalanceMapper;
import com.laye_tech.demo.mappers.CompteMapper;
import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.CompteDTO;
import com.laye_tech.demo.repositories.BalanceRepository;
import com.laye_tech.demo.repositories.CompteRepository;
import com.laye_tech.demo.services.BalanceService;
import com.laye_tech.demo.services.CompteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    @Override
    public BalanceDTO create(BalanceDTO balanceDTO) throws IOException {
        var saveBalance=balanceRepository.save(balanceMapper.asEntity(balanceDTO));
        return balanceMapper.asDto(saveBalance);
    }

    @Override
    public BalanceDTO update(BalanceDTO balanceDTO) throws IOException {
        var saveCompte=balanceRepository.save(balanceMapper.asEntity(balanceDTO));
        return balanceMapper.asDto(saveCompte);
    }

    @Override
    public BalanceDTO read(Long balanceId) {
        var balance= balanceRepository.findById(balanceId).orElseThrow();
        return balanceMapper.asDto(balance);
    }

    @Override
    public void delete(Long balanceId) {
        try {
            balanceRepository.deleteById(balanceId);
        } catch (IllegalArgumentException ex) {
        }
    }

    @Override
    public Page<BalanceDTO> readAll(Pageable pageable, Double solde,Long compteId, String sortBy, Boolean ascending) {
        return balanceRepository.findAllByFilter(pageable,solde, compteId,sortBy,ascending)
                .map(balanceMapper::asDto);
    }


}
