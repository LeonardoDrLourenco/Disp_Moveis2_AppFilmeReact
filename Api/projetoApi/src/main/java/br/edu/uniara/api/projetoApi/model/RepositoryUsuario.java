package br.edu.uniara.api.projetoApi.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<DtoUsuario, Long> {
}
