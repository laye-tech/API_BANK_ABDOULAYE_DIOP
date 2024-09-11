package com.laye_tech.demo.services.ServImple;

import com.laye_tech.demo.mappers.CompteMapper;
import com.laye_tech.demo.models.CompteDTO;
import com.laye_tech.demo.repositories.CompteRepository;
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
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;




    @Override
    public CompteDTO create(CompteDTO compteDTO) throws IOException {

        var saveCompte=compteRepository.save(compteMapper.asEntity(compteDTO));
        return compteMapper.asDto(saveCompte);
    }

    @Override
    public CompteDTO update(CompteDTO compteDTO) throws IOException {
        var saveCompte=compteRepository.save(compteMapper.asEntity(compteDTO));
        return compteMapper.asDto(saveCompte);
    }

    @Override
    public CompteDTO read(Long compteId) {
        var compte= compteRepository.findById(compteId).orElseThrow();
        return compteMapper.asDto(compte);
    }

    @Override
    public void delete(Long compteId) {
        try {
            compteRepository.deleteById(compteId);
        } catch (IllegalArgumentException ex) {
        }
    }

    @Override
    public Page<CompteDTO> readAll(Pageable pageable, String nom, String prenom,String sortBy, Boolean ascending) {
        return compteRepository.findAllByFilter(pageable,nom,prenom,sortBy,ascending)
                .map(compteMapper::asDto);
    }


}
