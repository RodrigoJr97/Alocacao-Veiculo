package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.repository.ClienteRepository;
import com.alugacarro.alugacarro.service.implementacao.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/aluga/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ClienteController(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClientes(@RequestParam int pagina,
                                                        @RequestParam int quantidade) {

        Pageable paginacao = PageRequest.of(pagina, quantidade);

        Page<Cliente> clientes = clienteService.listAll(paginacao);

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
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
