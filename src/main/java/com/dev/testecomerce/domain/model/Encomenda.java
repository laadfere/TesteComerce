package com.dev.testecomerce.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_encomenda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String whatsappCliente;

    private String observacoesCorTamanho; // Ex: "Quero a cor rosa claro com fio de algodão"

    @ManyToOne // Várias encomendas podem ser do mesmo produto de crochê
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private StatusEncomenda status = StatusEncomenda.PENDENTE; // Toda encomenda começa como PENDENTE

    private LocalDateTime dataPedido = LocalDateTime.now(); // Salva o dia e hora exatos do pedido
}