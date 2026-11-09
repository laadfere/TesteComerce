package com.dev.testecomerce.domain.repository;

import com.dev.testecomerce.domain.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    List<Encomenda> findByProdutoId(Long produtoId);
}