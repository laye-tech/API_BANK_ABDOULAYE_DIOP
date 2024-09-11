package com.laye_tech.demo.services;

import com.laye_tech.demo.models.VirementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Map;

public interface VirementService {
    VirementDTO create(VirementDTO virementDTO) throws IOException;
    VirementDTO update(VirementDTO virementDTO) throws IOException;
    VirementDTO read(Long virementId);
    void delete(Long virementId);

    Page<VirementDTO> readAll(Pageable pageable,Double solde,Long compteId ,String numeroCompteVirement, String sortBy,Boolean ascending);
}
