package br.com.byteshop.DTO;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String descricao;
    private Boolean ativo;

    // Construtor
    public ProdutoResponseDTO(Long id, String nome, BigDecimal preco, String categoria, String descricao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public Boolean getAtivo() { return ativo; }
}
