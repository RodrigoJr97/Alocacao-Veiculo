package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.dto.AluguelDTO;
import com.alugacarro.alugacarro.dto.InformacoesAluguelDTO;
import com.alugacarro.alugacarro.service.AluguelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/aluga/contrato")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid AluguelDTO aluguelDTO) {
        Aluguel aluguelSalvo = aluguelService.salvar(aluguelDTO);
        return new ResponseEntity<>("Aluguel Salvo, Id: " + aluguelSalvo.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public InformacoesAluguelDTO getAluguelById(@PathVariable Integer id) {
        return aluguelService
                .obterAluguelCompleto(id)
                .map( aluguel -> converter(aluguel) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrato não encontrado"));
    }

    private InformacoesAluguelDTO converter(Aluguel aluguel) {

        return InformacoesAluguelDTO
                .builder()
                .codigo(aluguel.getId())
                .incioAluguel(aluguel.getDataInicio())
                .fimAluguel(aluguel.getDataFim())
                .nome(aluguel.getCliente().getNome())
                .numeroTelefone(aluguel.getCliente().getNumeroTelefone())
                .cpf(aluguel.getCliente().getCpf())
                .email(aluguel.getCliente().getEmail())
                .marca(aluguel.getCarro().getMarca())
                .modelo(aluguel.getCarro().getModelo())
                .tipo(aluguel.getCarro().getTipo())
                .valorDiaria(aluguel.getCarro().getValorDiaria())
                .valorTotal(aluguel.getValorTotal())
                .build();

    }

}
