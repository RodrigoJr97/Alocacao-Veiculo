package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.EnderecoCliente;
import com.alugacarro.alugacarro.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/aluga/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEndereco() {
        List<EnderecoCliente> listaEndereco = enderecoService.lista();
        if (listaEndereco.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listaEndereco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Integer id) {
        Optional<EnderecoCliente> buscaEnderecoId = enderecoService.findById(id);

        if (buscaEnderecoId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        enderecoService.delete(id);
        return new ResponseEntity<>("Endereço Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEndereco(@PathVariable Integer id, @RequestBody @Valid EnderecoCliente novoEndereco) {

        Optional<EnderecoCliente> enderecoAux = enderecoService.findById(id);

        if (enderecoAux.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        enderecoService.update(id, novoEndereco);
        return ResponseEntity.ok().build();

    }

}
