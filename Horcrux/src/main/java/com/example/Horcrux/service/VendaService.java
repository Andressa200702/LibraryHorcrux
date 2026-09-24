package com.example.Horcrux.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Horcrux.models.entity.ClienteEntity;
import com.example.Horcrux.models.entity.LivroEntity;
import com.example.Horcrux.models.entity.VendaEntity;
import com.example.Horcrux.repository.ClienteRepository;
import com.example.Horcrux.repository.LivroRepository;
import com.example.Horcrux.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<VendaEntity> listarTodas() {
        return vendaRepository.findAll();
    }

    public Optional<VendaEntity> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public List<VendaEntity> buscarPorCliente(Long clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }


    public VendaEntity registrarVenda(VendaEntity venda) {
        LivroEntity livro = livroRepository.findById(venda.getLivro().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        ClienteEntity cliente = clienteRepository.findById(venda.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        venda.setLivro(livro);
        venda.setCliente(cliente);

        if (venda.getValorTotal() == null) {
            venda.setValorTotal(livro.getPreco() * venda.getQuantidade());
        }

        if (venda.getDataVenda() == null) {
            venda.setDataVenda(LocalDate.now());
        }

        return vendaRepository.save(venda);
    }

    public void deletar(Long id) {
        vendaRepository.deleteById(id);
    }
}