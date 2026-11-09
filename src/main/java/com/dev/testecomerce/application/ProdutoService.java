package com.dev.testecomerce.application;

import com.dev.testecomerce.domain.model.Encomenda;
import com.dev.testecomerce.domain.model.Produto;
import com.dev.testecomerce.domain.repository.EncomendaRepository;
import com.dev.testecomerce.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private EncomendaRepository encomendaRepository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Transactional
    public void deletar(Long id) {
        // 1. Apaga as encomendas associadas a este produto para não dar erro
        List<Encomenda> encomendasDoProduto = encomendaRepository.findByProdutoId(id);
        encomendaRepository.deleteAll(encomendasDoProduto);

        // 2. Apaga o produto do catálogo
        repository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return repository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setEstoque(produtoAtualizado.getEstoque());
            return repository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}