package com.example.Horcrux.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Horcrux.models.entity.LivroEntity;
import com.example.Horcrux.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroEntity> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<LivroEntity> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public List<LivroEntity> buscarPorAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    public List<LivroEntity> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public LivroEntity salvar(LivroEntity livro) {
        return livroRepository.save(livro);
    }

    public LivroEntity atualizar(Long id, LivroEntity livroAtualizado) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setAutor(livroAtualizado.getAutor());
                    livro.setIsbn(livroAtualizado.getIsbn());
                    livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                    livro.setPreco(livroAtualizado.getPreco());
                    return livroRepository.save(livro);
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}