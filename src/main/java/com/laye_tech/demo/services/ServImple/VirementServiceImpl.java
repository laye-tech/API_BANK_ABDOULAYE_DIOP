package com.laye_tech.demo.services.ServImple;

import com.laye_tech.demo.mappers.BalanceMapper;
import com.laye_tech.demo.mappers.VirementMapper;
import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.VirementDTO;
import com.laye_tech.demo.repositories.BalanceRepository;
import com.laye_tech.demo.repositories.VirementRepository;
import com.laye_tech.demo.services.BalanceService;
import com.laye_tech.demo.services.VirementService;
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
public class VirementServiceImpl implements VirementService {

    private final VirementRepository virementRepository;
    private final VirementMapper virementMapper;


    @Override
    public VirementDTO create(VirementDTO virementDTO) throws IOException {
        var saveVirement=virementRepository.save(virementMapper.asEntity(virementDTO));
        return virementMapper.asDto(saveVirement);
    }

    @Override
    public VirementDTO update(VirementDTO virementDTO) throws IOException {
        var saveVirement=virementRepository.save(virementMapper.asEntity(virementDTO));
        return virementMapper.asDto(saveVirement);    }

    @Override
    public VirementDTO read(Long virementId) {
        var balance= virementRepository.findById(virementId).orElseThrow();
        return virementMapper.asDto(balance);
    }

    @Override
    public void delete(Long virementId) {
        try {
            virementRepository.deleteById(virementId);
        } catch (IllegalArgumentException ex) {
        }
    }

    @Override
    public Page<VirementDTO> readAll(Pageable pageable,Double solde,Long compteId ,String numeroCompteVirement, String sortBy,Boolean ascending) {
        return virementRepository.findAllByFilter(pageable,solde, compteId,numeroCompteVirement,sortBy,ascending)
                .map(virementMapper::asDto);    }
}
