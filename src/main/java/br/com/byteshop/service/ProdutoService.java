package br.com.byteshop.service;

import br.com.byteshop.DTO.ProdutoRequestDTO;
import br.com.byteshop.DTO.ProdutoResponseDTO;
import br.com.byteshop.exception.RecursoNaoEncontradoException;
import br.com.byteshop.mapper.ProdutoMapper;
import br.com.byteshop.model.Produto;
import br.com.byteshop.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable, String categoria, Boolean ativo) {
        Page<Produto> produtos = produtoRepository.findByFilters(categoria, ativo, pageable);
        return produtos.map(produtoMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id));
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setDescricao(dto.getDescricao());

        if (dto.getAtivo() != null) {
            produto.setAtivo(dto.getAtivo());
        }

        produto = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}