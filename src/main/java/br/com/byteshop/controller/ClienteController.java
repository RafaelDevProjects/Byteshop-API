package br.com.byteshop.controller;

import br.com.byteshop.DTO.ClienteRequestDTO;
import br.com.byteshop.DTO.ClienteResponseDTO;
import br.com.byteshop.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna uma lista paginada de clientes com filtros opcionais")
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientes(
            Pageable pageable,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email) {
        Page<ClienteResponseDTO> clientes = clienteService.listarClientes(pageable, nome, email);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna os detalhes de um cliente específico")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable Long id) {
        ClienteResponseDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(summary = "Criar cliente", description = "Cria um novo cliente")
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO cliente = clienteService.criarCliente(dto);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO cliente = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente", description = "Exclui um cliente existente")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
