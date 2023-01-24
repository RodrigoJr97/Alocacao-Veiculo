package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.domain.repository.AluguelRepository;
import com.alugacarro.alugacarro.dto.AluguelDTO;
import com.alugacarro.alugacarro.dto.InformacoesAluguelDTO;
import com.alugacarro.alugacarro.service.AluguelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/aluga/contrato")
public class AluguelController {

    private final AluguelService aluguelService;
    private final AluguelRepository aluguelRepository;

    public AluguelController(AluguelService aluguelService,
                             AluguelRepository aluguelRepository) {
        this.aluguelService = aluguelService;
        this.aluguelRepository = aluguelRepository;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid AluguelDTO aluguelDTO) {
        Aluguel aluguelSalvo = aluguelService.salvar(aluguelDTO);
        return new ResponseEntity<>("Aluguel Salvo, Id: " + aluguelSalvo.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCarros() {

        List<Aluguel> listaAlguel = aluguelRepository.findAll();

        if (listaAlguel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listaAlguel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public InformacoesAluguelDTO getAluguelById(@PathVariable Integer id) {
        return aluguelService
                .findById(id)
                .map( aluguel -> aluguelService.converter(aluguel) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrato não encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> finalizaContrato(@PathVariable Integer id) {
        aluguelService.finalizaAluguel(id);
        return new ResponseEntity<>("Contrato Finalizado", HttpStatus.OK);
    }


}
