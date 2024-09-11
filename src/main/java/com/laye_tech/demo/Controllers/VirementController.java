package com.laye_tech.demo.Controllers;

import com.laye_tech.demo.mappers.BalanceMapper;
import com.laye_tech.demo.mappers.VirementMapper;
import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.VirementDTO;
import com.laye_tech.demo.services.BalanceService;
import com.laye_tech.demo.services.VirementService;
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

@RestController
@RequestMapping("/virement")
@Tag(name = "Virement-controller", description = "Virement controller")
@RequiredArgsConstructor
public class VirementController {
    private final VirementService virementService;

    private final VirementMapper virementMapper;


    @Operation(summary = "Create Virement", description = "this endpoint take input virementDTO and save it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the family was syntactically incorrect"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VirementDTO createVirement(@RequestBody VirementDTO virementDTO) throws IOException {
        return virementService.create(virementDTO);
    }

    @PutMapping("/{virementId}")
    @ResponseStatus(HttpStatus.OK)
    public VirementDTO updateVirement(@Parameter(name = "virementId", description = "the virement id updated") @PathVariable Long virementId
            , @RequestBody VirementDTO virementDTO) throws IOException {

        virementDTO.setId(virementId);
        return virementService.update(virementDTO);
    }

        @Operation(summary = "Read the virement", description = "This endpoint is used to read virement it take input id virementId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @GetMapping("/{virementId}")
    @ResponseStatus(HttpStatus.OK)
    public VirementDTO readVirement(@Parameter(name = "virementId", description = "the virement id to read") @PathVariable Long virementId) {
        return virementService.read(virementId);
    }

        @Operation(summary = "delete the balance", description = "Delete virement, it take input Virement id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Request sent by the shelf was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @DeleteMapping("/{virementId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBalance(@Parameter(name = "virementId", description = "the balanceId  deleted") @PathVariable Long virementId) {
        virementService.delete(virementId);
    }

    @Operation(summary = "Read all Virement", description = "It take input param of the page and return this list related")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<VirementDTO> readAll(
            Pageable pageable,
            @Parameter(name = "solde", description = "value of solde used to filter list Virement") @RequestParam(value = "solde", required = false) Double solde,
            @Parameter(name = "compteId", description = "value of nom used to filter list Virement") @RequestParam(value = "compteId", required = false) Long compteId,
            @Parameter(name = "numeroCompteVirement", description = "value of nom used to filter list Virement") @RequestParam(value = "numeroCompteVirement", required = false) String numeroCompteVirement,
            @Parameter(name = "sortBy", description = "list of sort Request used to filter list Virement") @RequestParam(value = "sortBy", required = false) String sortBy,
            @Parameter(name = "ascending", description = "list of ascending used to filter list Virement") @RequestParam(value = "ascending", required = false) Boolean ascending
    ) {


        return  virementService.readAll(pageable,solde,compteId,numeroCompteVirement,sortBy,ascending);
    }
 }





