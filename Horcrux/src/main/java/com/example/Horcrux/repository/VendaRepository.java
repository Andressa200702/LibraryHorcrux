package com.example.Horcrux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Horcrux.models.entity.VendaEntity;

public interface VendaRepository extends JpaRepository<VendaEntity, Long> {

    List<VendaEntity> findByClienteId(Long clienteId);

    List<VendaEntity> findByLivroId(Long livroId);
}