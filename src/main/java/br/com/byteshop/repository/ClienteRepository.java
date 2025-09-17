package br.com.byteshop.repository;

import br.com.byteshop.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE " +
            "(:nome IS NULL OR c.nome LIKE %:nome%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%)")
    Page<Cliente> findByFilters(@Param("nome") String nome,
                                @Param("email") String email,
                                Pageable pageable);
}
