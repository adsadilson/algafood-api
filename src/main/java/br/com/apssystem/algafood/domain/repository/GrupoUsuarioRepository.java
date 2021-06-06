package br.com.apssystem.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.algafood.domain.model.GrupoUsuario;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Long> {

	Optional<GrupoUsuario> findByNome(String nome);
}
