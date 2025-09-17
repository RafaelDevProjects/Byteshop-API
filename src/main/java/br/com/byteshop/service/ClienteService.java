package br.com.byteshop.service;

import br.com.byteshop.DTO.ClienteRequestDTO;
import br.com.byteshop.DTO.ClienteResponseDTO;
import br.com.byteshop.exception.ConflitoNegocioException;
import br.com.byteshop.exception.RecursoNaoEncontradoException;
import br.com.byteshop.mapper.ClienteMapper;
import br.com.byteshop.model.Cliente;
import br.com.byteshop.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> listarClientes(Pageable pageable, String nome, String email) {
        Page<Cliente> clientes = clienteRepository.findByFilters(nome, email, pageable);
        return clientes.map(clienteMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com ID: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ConflitoNegocioException("Email já cadastrado: " + dto.getEmail());
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com ID: " + id));

        // Verifica se o email já existe em outro cliente
        if (!cliente.getEmail().equals(dto.getEmail()) &&
                clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ConflitoNegocioException("Email já cadastrado: " + dto.getEmail());
        }

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setDocumento(dto.getDocumento());

        cliente = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
