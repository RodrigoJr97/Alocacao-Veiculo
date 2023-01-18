package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/aluga/carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Carro carro) {
        carroService.salvar(carro);

        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCarros() {
        List<Carro> carros = carroService.lista();

        if (carros.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarroById(@PathVariable Integer id) {
        Optional<Carro> buscaCarroId = carroService.findById(id);

        if (buscaCarroId.isEmpty()) {
            return new ResponseEntity<>("Carro não encontrado.", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(buscaCarroId.get(), HttpStatus.OK);
    }

    @GetMapping("/tipo")
    public ResponseEntity<?> getCarroTipo(@RequestParam String tipo) {
        List<Carro> listCarroByTipo = carroService.carroPorTipo(tipo);

        if (listCarroByTipo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listCarroByTipo, HttpStatus.OK);
    }

    @GetMapping("/disponivel")
    public ResponseEntity<?> getCarrosDisponiveis() {
        List<Carro> byCarroDisponivel = carroService.listaCarroDisponivel();

        if (byCarroDisponivel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(byCarroDisponivel, HttpStatus.OK);
    }

    @GetMapping("/indisponivel")
    public ResponseEntity<?> getCarrosIndisponiveis() {
        List<Carro> byCarroIndisponivel = carroService.listaCarroIndisponivel();

        if (byCarroIndisponivel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(byCarroIndisponivel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaCarro(@PathVariable Integer id) {
        Optional<Carro> buscaCarroId = carroService.findById(id);

        if (buscaCarroId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        carroService.delete(id);
        return new ResponseEntity<>("Carro Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaCarro(@PathVariable Integer id, @RequestBody @Valid Carro carro) {

        Optional<Carro> optionalCarro = carroService.findById(id);

        if (optionalCarro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        carroService.update(id, carro);
        return ResponseEntity.ok().build();

    }

}
