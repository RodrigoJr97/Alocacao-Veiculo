package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.entity.EnderecoCliente;
import com.alugacarro.alugacarro.domain.repository.EnderecoClienteRepository;
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

    private final EnderecoClienteRepository enderecoRepository;

    public EnderecoController(EnderecoClienteRepository enderecoClienteRepository) {
        this.enderecoRepository = enderecoClienteRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllEndereco() {
        List<EnderecoCliente> listaEndereco = enderecoRepository.findAll();
        if (listaEndereco.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listaEndereco, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Integer id) {
        Optional<EnderecoCliente> buscaEnderecoId = enderecoRepository.findById(id);

        if (buscaEnderecoId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        enderecoRepository.deleteById(id);
        return new ResponseEntity<>("Endereço Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEndereco(@PathVariable Integer id, @RequestBody @Valid EnderecoCliente novoEndereco) {

        Optional<EnderecoCliente> enderecoAux = enderecoRepository.findById(id);
        Cliente cliente = enderecoAux.get().getCliente();

        return enderecoAux
                .map( enderecoAntigo -> {
                    novoEndereco.setId(enderecoAntigo.getId());
                    novoEndereco.setCliente(cliente);
                    enderecoRepository.save(novoEndereco);
                    return new ResponseEntity<>(novoEndereco, HttpStatus.OK);
                } ).orElseGet( () -> {
                    return ResponseEntity.noContent().build();
                });

    }

}
