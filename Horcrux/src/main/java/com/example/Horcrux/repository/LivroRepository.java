package com.example.Horcrux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Horcrux.models.entity.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    List<LivroEntity> findByAutor(String autor);

    List<LivroEntity> findByTituloContainingIgnoreCase(String titulo);
}