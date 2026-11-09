package com.dev.testecomerce.application;

import com.dev.testecomerce.domain.model.Encomenda;
import com.dev.testecomerce.domain.model.Produto;
import com.dev.testecomerce.domain.model.StatusEncomenda;
import com.dev.testecomerce.domain.repository.EncomendaRepository;
import com.dev.testecomerce.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Salva a encomenda preenchendo o produto, o status inicial e a data do pedido
    public Encomenda criarEncomenda(Encomenda encomenda) {
        // 1. Busca o produto completo no banco
        Produto produto = produtoRepository.findById(encomenda.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        encomenda.setProduto(produto);

        // 2. Garante que o status inicial seja PENDENTE
        if (encomenda.getStatus() == null) {
            encomenda.setStatus(StatusEncomenda.PENDENTE);
        }

        // 3. Garante que a data do pedido seja o momento atual
        if (encomenda.getDataPedido() == null) {
            encomenda.setDataPedido(LocalDateTime.now());
        }

        return repository.save(encomenda);
    }

    public List<Encomenda> listarTodas() {
        return repository.findAll();
    }

    public Encomenda atualizarStatus(Long id, StatusEncomenda novoStatus) {
        Encomenda encomenda = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encomenda não encontrada!"));

        encomenda.setStatus(novoStatus);
        return repository.save(encomenda);
    }
}