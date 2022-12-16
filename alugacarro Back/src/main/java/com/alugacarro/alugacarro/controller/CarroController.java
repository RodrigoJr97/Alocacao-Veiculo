package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.domain.repository.CarroRepository;
import com.alugacarro.alugacarro.service.implementacao.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final CarroRepository carroRepository;
    private final CarroService carroService;

    public CarroController(CarroRepository carroRepository, CarroService carroService) {
        this.carroRepository = carroRepository;
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Carro carro) {
        String tipoAux = carro.getTipo().toUpperCase();

        carro.setTipo(tipoAux);
        carroRepository.save(carro);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Carro>> getAllCarros(@RequestParam int pagina,
                                                    @RequestParam int quantidade) {

        Pageable paginacao = PageRequest.of(pagina, quantidade);

        Page<Carro> carros = carroService.listAll(paginacao);

        if (carros.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarroById(@PathVariable Integer id) {
        Optional<Carro> buscaCarroId = carroRepository.findById(id);

        if (buscaCarroId.isEmpty()) {
            return new ResponseEntity<>("Carro não encontrado.", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(buscaCarroId.get(), HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> getCarroTipo(@PathVariable String tipo) {
        List<Carro> listCarroByTipo = carroRepository.findCarroByTipo(tipo);

        if (listCarroByTipo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(listCarroByTipo, HttpStatus.OK);
    }

    @GetMapping("/disponivel")
    public ResponseEntity<?> getCarrosDisponiveis() {
        List<Carro> byCarroDisponivel = carroRepository.findByCarroDisponivel();

        if (byCarroDisponivel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(byCarroDisponivel, HttpStatus.OK);
    }

    @GetMapping("/indisponivel")
    public ResponseEntity<?> getCarrosIndisponiveis() {
        List<Carro> byCarroIndisponivel = carroRepository.findByCarroIndisponivel();

        if (byCarroIndisponivel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(byCarroIndisponivel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaCarro(@PathVariable Integer id) {
        Optional<Carro> buscaCarroId = carroRepository.findById(id);

        if (buscaCarroId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        carroRepository.deleteById(id);
        return new ResponseEntity<>("Carro Deletado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaCarro(@PathVariable Integer id, @RequestBody @Valid Carro carro) {

        return carroRepository
                .findById(id)
                .map( carroAtualizado -> {
                    carro.setId(carroAtualizado.getId());
                    carroRepository.save(carro);
                    return ResponseEntity.ok().build();
                }).orElseGet( () -> {
                    return ResponseEntity.notFound().build();
                });

    }

}
