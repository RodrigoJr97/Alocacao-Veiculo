package com.alugacarro.alugacarro.service.implementacao;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.entity.EnderecoCliente;
import com.alugacarro.alugacarro.domain.repository.EnderecoClienteRepository;
import com.alugacarro.alugacarro.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoClienteRepository enderecoRepository;

    @Override
    public EnderecoCliente salvar(EnderecoCliente enderecoCliente) {
        return enderecoRepository.save(enderecoCliente);
    }

    @Override
    public List<EnderecoCliente> lista() {
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<EnderecoCliente> findById(Integer id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, EnderecoCliente novoEndereco) {
        Optional<EnderecoCliente> optionalEndereco = enderecoRepository.findById(id);
        Cliente cliente = optionalEndereco.get().getCliente();

        novoEndereco.setId(optionalEndereco.get().getId());
        novoEndereco.setCliente(cliente);
        enderecoRepository.save(novoEndereco);
    }

}
