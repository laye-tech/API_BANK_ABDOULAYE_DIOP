package com.laye_tech.demo.Controllers;

import com.laye_tech.demo.mappers.BalanceMapper;
import com.laye_tech.demo.mappers.CompteMapper;
import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.CompteDTO;
import com.laye_tech.demo.services.BalanceService;
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

@RestController
@RequestMapping("/balance")
@Tag(name = "Balance-controller", description = "Balance controller")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    private final BalanceMapper balanceMapper;


    @Operation(summary = "Create Balance", description = "this endpoint take input balanceDTO and save it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the family was syntactically incorrect"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BalanceDTO createBalance(@RequestBody BalanceDTO balanceDTO) throws IOException {
        return balanceService.create(balanceDTO);
    }

    @PutMapping("/{balanceId}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO updateBalance(@Parameter(name = "balanceId", description = "the balance id updated") @PathVariable Long balanceId
            , @RequestBody BalanceDTO balanceDTO) throws IOException {

        balanceDTO.setId(balanceId);
        return balanceService.update(balanceDTO);
    }

        @Operation(summary = "Read the Balance", description = "This endpoint is used to read Balance it take input id balanceId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request sent by the client was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @GetMapping("/{balanceId}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceDTO readBalance(@Parameter(name = "balanceId", description = "the balance id to read") @PathVariable Long balanceId) {
        return balanceService.read(balanceId);
    }

        @Operation(summary = "delete the balance", description = "Delete balance, it take input balance id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Request sent by the shelf was syntactically incorrect"),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @DeleteMapping("/{balanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBalance(@Parameter(name = "balanceId", description = "the balanceId  deleted") @PathVariable Long balanceId) {
        balanceService.delete(balanceId);
    }

    @Operation(summary = "Read all Balance", description = "It take input param of the page and return this list related")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error during request processing")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<BalanceDTO> readAll(
            Pageable pageable,
            @Parameter(name = "solde", description = "value of solde used to filter list balance") @RequestParam(value = "solde", required = false) Double solde,
            @Parameter(name = "compteId", description = "value of nom used to filter list balance") @RequestParam(value = "compteId", required = false) Long compteId,
            @Parameter(name = "sortBy", description = "list of sort Request used to filter list compte") @RequestParam(value = "sortBy", required = false) String sortBy,
            @Parameter(name = "ascending", description = "list of ascending used to filter list compte") @RequestParam(value = "ascending", required = false) Boolean ascending
    ) {


        return  balanceService.readAll(pageable,solde,compteId,sortBy,ascending);
    }
 }





