package com.laye_tech.demo.services;

import com.laye_tech.demo.models.CompteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CompteService {
    CompteDTO create(CompteDTO compte) throws IOException;
    CompteDTO update(CompteDTO compteDTO) throws IOException;
    CompteDTO read(Long compteId);
    void delete(Long compteId);

    Page<CompteDTO> readAll(Pageable pageable,String nom,String prenom,String sortBy, Boolean ascending);

}
