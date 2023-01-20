package com.alugacarro.alugacarro.service.implementacao;

import com.alugacarro.alugacarro.domain.contantes.TipoCategoria;
import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.domain.repository.CarroRepository;
import com.alugacarro.alugacarro.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarroServiceImpl implements CarroService {

    private final CarroRepository carroRepository;

    @Override
    public Carro salvar(Carro carro) {
        agrupaPorTipo(carro);
        String tipoAux = carro.getTipo().toUpperCase();

        carro.setTipo(tipoAux);
        carroRepository.save(carro);

        return carro;
    }

    @Override
    public List<Carro> lista() {
        return carroRepository.findAll();
    }

    @Override
    public Optional<Carro> findById(Integer id) {
        return carroRepository.findById(id);
    }

    @Override
    public List<Carro> carroPorTipo(String tipo) {
        return carroRepository.findCarroByTipo(tipo);
    }

    @Override
    public List<Carro> listaCarroDisponivel() {
        return carroRepository.findByCarroDisponivel();
    }

    @Override
    public List<Carro> listaCarroIndisponivel() {
        return carroRepository.findByCarroIndisponivel();
    }

    @Override
    public void delete(Integer id) {
        carroRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Carro carro) {
        Optional<Carro> optionalCarro = carroRepository.findById(id);

        carro.setId(optionalCarro.get().getId());
        carroRepository.save(carro);
    }

    private void agrupaPorTipo(Carro carro) {

        HashMap<String, List<Carro>> mapsCarro = new HashMap<String, List<Carro>>();

        mapsCarro.put(TipoCategoria.SEDAN, new ArrayList<Carro>());
        mapsCarro.put(TipoCategoria.HATCH, new ArrayList<Carro>());
        mapsCarro.put(TipoCategoria.CAMIONETE, new ArrayList<Carro>());
        mapsCarro.put(TipoCategoria.SUV, new ArrayList<Carro>());
        mapsCarro.put(TipoCategoria.LUXO, new ArrayList<Carro>());

        if (carro.getTipo().equalsIgnoreCase("sedan")) {
            mapsCarro.get(TipoCategoria.SEDAN).add(carro);
        } else if (carro.getTipo().equalsIgnoreCase("hatch")) {
            mapsCarro.get(TipoCategoria.HATCH).add(carro);
        } else if (carro.getTipo().equalsIgnoreCase("camionete")) {
            mapsCarro.get(TipoCategoria.CAMIONETE).add(carro);
        } else if (carro.getTipo().equalsIgnoreCase("suv")) {
            mapsCarro.get(TipoCategoria.SUV).add(carro);
        } else if (carro.getTipo().equalsIgnoreCase("luxo")) {
            mapsCarro.get(TipoCategoria.LUXO).add(carro);
        }

    }

}
