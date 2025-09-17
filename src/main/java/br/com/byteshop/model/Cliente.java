package br.com.byteshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Documento é obrigatório")
    @Column(nullable = false)
    private String documento;

    // Construtores
    public Cliente() {}

    public Cliente(String nome, String email, String documento) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
}