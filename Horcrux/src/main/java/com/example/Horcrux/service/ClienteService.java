package com.example.Horcrux.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Horcrux.models.entity.ClienteEntity;
import com.example.Horcrux.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity salvar(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    public ClienteEntity atualizar(Long id, ClienteEntity clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}