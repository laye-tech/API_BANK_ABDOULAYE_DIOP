package com.laye_tech.demo.Controllers;

import com.laye_tech.demo.mappers.CompteMapper;
import com.laye_tech.demo.models.CompteDTO;
import com.laye_tech.demo.services.CompteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/compte")
@Tag(name = "Compte-controller", description = "Compte controller")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    private final CompteMapper compteMapper;


    @Operation(summary = "Create Compte", description = "this endpoint take input compteDTO and save it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the family was syntactically incorrect"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompteDTO createCompte(@RequestBody CompteDTO compteDTO) throws IOException {
        return compteService.create(compteDTO);
    }

    @PutMapping("/{compteId}")
    @ResponseStatus(HttpStatus.OK)
    public CompteDTO updateCompte(@Parameter(name = "compteId", description = "the distinction id updated") @PathVariable Long compteId
            , @RequestBody CompteDTO compteDTO) throws IOException {

        compteDTO.setId(compteId);
        return compteService.update(compteDTO);
    }

        @Operation(summary = "Read the Compte", description = "This endpoint is used to read Compte it take input id compteId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @GetMapping("/{compteId}")
    @ResponseStatus(HttpStatus.OK)
    public CompteDTO readCompte(@Parameter(name = "compteId", description = "the compte id to read") @PathVariable Long compteId) {
        return compteService.read(compteId);
    }

        @Operation(summary = "delete the Compte", description = "Delete Compte, it take input id compte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Request sent by the shelf was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @DeleteMapping("/{compteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompte(@Parameter(name = "compteId", description = "the compteId  deleted") @PathVariable Long compteId) {
        compteService.delete(compteId);
    }
        @Operation(summary = "Read all Compte", description = "It take input param of the page and return this list related")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CompteDTO> readAll(
            Pageable pageable,
            @Parameter(name = "prenom", description = "value of prenom used to filter list compte") @RequestParam(value = "prenom", required = false) String prenom,
            @Parameter(name = "nom", description = "value of nom used to filter list compte") @RequestParam(value = "nom", required = false) String nom,
            @Parameter(name = "sortBy", description = "list of sort Request used to filter list compte") @RequestParam(value = "sortBy", required = false) String sortBy,
            @Parameter(name = "ascending", description = "list of ascending used to filter list compte") @RequestParam(value = "ascending", required = false) Boolean ascending
    ) {


       return  compteService.readAll(pageable,nom,prenom,sortBy,ascending);
    }
 }








