package br.edu.uniara.api.projetoApi.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompras  extends JpaRepository<DtoCompraIngresso, Long> {
}
