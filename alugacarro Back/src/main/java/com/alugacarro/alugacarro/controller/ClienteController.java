package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.service.implementacao.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluga/cliente")
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente) {
        clienteService.salvar(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        List<Cliente> clientes = clienteService.lista();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> buscaClienteId = clienteService.findClienteById(id);

        if (buscaClienteId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(buscaClienteId.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> buscaClienteId = clienteService.findClienteById(id);

        if (buscaClienteId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        clienteService.delete(id);
        return new ResponseEntity<>("Cliente Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaCliente(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {

        return clienteService
                .findClienteById(id)
                .map( cliente -> {
                    clienteAtualizado.setId(cliente.getId());
                    clienteAtualizado.setDisponivelParaContrato(cliente.isDisponivelParaContrato());
                    clienteService.salvar(clienteAtualizado);
                    return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
                }).orElseGet( () -> {
                    return ResponseEntity.noContent().build();
                });

    }

}
