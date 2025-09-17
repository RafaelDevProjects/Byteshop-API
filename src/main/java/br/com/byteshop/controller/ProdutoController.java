package br.com.byteshop.controller;

import br.com.byteshop.DTO.ProdutoRequestDTO;
import br.com.byteshop.DTO.ProdutoResponseDTO;
import br.com.byteshop.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna uma lista paginada de produtos com filtros opcionais")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(
            Pageable pageable,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean ativo) {
        Page<ProdutoResponseDTO> produtos = produtoService.listarProdutos(pageable, categoria, ativo);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico")
    public ResponseEntity<ProdutoResponseDTO> buscarProduto(@PathVariable Long id) {
        ProdutoResponseDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    @Operation(summary = "Criar produto", description = "Cria um novo produto")
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produto = produtoService.criarProduto(dto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produto = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto", description = "Exclui um produto existente")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}