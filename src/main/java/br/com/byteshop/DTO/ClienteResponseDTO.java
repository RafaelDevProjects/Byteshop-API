package br.com.byteshop.DTO;

public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String documento;

    // Construtor
    public ClienteResponseDTO(Long id, String nome, String email, String documento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDocumento() { return documento; }
}
