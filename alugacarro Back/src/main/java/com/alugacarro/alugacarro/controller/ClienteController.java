package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluga/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        List<Cliente> listaCliente = clienteRepository.findAll();

        if (listaCliente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> buscaClienteId = clienteRepository.findById(id);

        if (buscaClienteId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(buscaClienteId.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> buscaClienteId = clienteRepository.findById(id);

        if (buscaClienteId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        clienteRepository.deleteById(id);
        return new ResponseEntity<>("Cliente Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaCliente(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {

        return clienteRepository
                .findById(id)
                .map( cliente -> {
                    clienteAtualizado.setId(cliente.getId());
                    clienteAtualizado.setDisponivelParaContrato(cliente.isDisponivelParaContrato());
                    clienteRepository.save(clienteAtualizado);
                    return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
                }).orElseGet( () -> {
                    return ResponseEntity.noContent().build();
                });

    }

}
