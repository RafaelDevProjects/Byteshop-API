package br.com.byteshop.repository;

import br.com.byteshop.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE " +
            "(:categoria IS NULL OR p.categoria = :categoria) AND " +
            "(:ativo IS NULL OR p.ativo = :ativo)")
    Page<Produto> findByFilters(@Param("categoria") String categoria,
                                @Param("ativo") Boolean ativo,
                                Pageable pageable);
}