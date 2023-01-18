package com.alugacarro.alugacarro.service.implementacao;

import com.alugacarro.alugacarro.domain.entity.Cliente;
import com.alugacarro.alugacarro.domain.repository.ClienteRepository;
import com.alugacarro.alugacarro.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> lista() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        cliente.setId(optionalCliente.get().getId());
        clienteRepository.save(cliente);
    }

}
