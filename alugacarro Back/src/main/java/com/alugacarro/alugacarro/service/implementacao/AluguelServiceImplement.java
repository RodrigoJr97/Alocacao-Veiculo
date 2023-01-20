package com.alugacarro.alugacarro.service.implementacao;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.enums.StatusContrato;
import com.alugacarro.alugacarro.domain.repository.AluguelRepository;
import com.alugacarro.alugacarro.domain.repository.CarroRepository;
import com.alugacarro.alugacarro.domain.repository.ClienteRepository;
import com.alugacarro.alugacarro.dto.AluguelDTO;
import com.alugacarro.alugacarro.exception.CarroNaoDisponivelException;
import com.alugacarro.alugacarro.exception.ClienteComContratoAbertoException;
import com.alugacarro.alugacarro.exception.RegraNegocioException;
import com.alugacarro.alugacarro.service.AluguelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AluguelServiceImplement implements AluguelService {

    private final ClienteRepository clienteRepository;
    private final CarroRepository carroRepository;
    private final AluguelRepository aluguelRepository;

    public Page<Aluguel> listAll(Pageable pageable) {
        return aluguelRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Aluguel salvar(AluguelDTO aluguelDTO) {
        Integer idCliente = aluguelDTO.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));

        if (!cliente.isDisponivelParaContrato()) {
            throw new ClienteComContratoAbertoException();
        }

        cliente.setDisponivelParaContrato(false);

        Integer idCarro = aluguelDTO.getCarro();
        Carro carro = carroRepository.findById(idCarro)
                .orElseThrow(() -> new RegraNegocioException("Carro não encontrado."));

        if (!carro.isDisponivel()) {
            throw new CarroNaoDisponivelException();
        }

        carro.setDisponivel(false);


        BigDecimal valorDiaria = carro.getValorDiaria();
        int diasAluguel = aluguelDTO.getDiasDeAluguel();

        BigDecimal auxValor = new BigDecimal(diasAluguel);
        BigDecimal valorTotal = valorDiaria.multiply(auxValor);

        LocalDate dataInicioAux = LocalDate.now();
        LocalDate dataFimAux = dataInicioAux.plusDays(diasAluguel);

        Aluguel aluguel = new Aluguel();
        aluguel.setDataInicio(dataInicioAux);
        aluguel.setDataFim(dataFimAux);
        aluguel.setValorTotal(valorTotal);
        aluguel.setCliente(cliente);
        aluguel.setCarro(carro);

        aluguelRepository.save(aluguel);

        return aluguel;
    }

    @Override
    public Optional<Aluguel> obterAluguelCompleto(Integer id) {
        return aluguelRepository.findById(id);
    }

    @Override
    @Transactional
    public void finalizaAluguel(Integer id) {
        Aluguel aluguelParaFinalizar = aluguelRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Aluguel não encontrado."));

        Integer idCliente = aluguelParaFinalizar.getCliente().getId();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));

        Integer idCarro = aluguelParaFinalizar.getCarro().getId();
        Carro carro = carroRepository.findById(idCarro)
                .orElseThrow(() -> new RegraNegocioException("Carro não encontrado."));


        cliente.setDisponivelParaContrato(true);
        carro.setDisponivel(true);
        aluguelParaFinalizar.setStatusContrato(StatusContrato.valueOf(StatusContrato.FINALIZADO.name()));

        clienteRepository.save(cliente);
        carroRepository.save(carro);
        aluguelRepository.save(aluguelParaFinalizar);
    }


}
