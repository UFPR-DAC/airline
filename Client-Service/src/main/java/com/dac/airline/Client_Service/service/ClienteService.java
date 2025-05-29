package com.dac.airline.Client_Service.service;

import com.dac.airline.Client_Service.model.Cliente;
import com.dac.airline.Client_Service.repository.ClienteRepository;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void addCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente getClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com CPF: " + cpf));
    }

        
}
