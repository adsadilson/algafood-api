package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.RegistroNaoEncontradoException;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import br.com.apssystem.algafood.domain.repository.GrupoUsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GrupoUsuarioService {

	private GrupoUsuarioRepository repository;

	public GrupoUsuario adicionar(GrupoUsuario grupoUsuario) {
		grupoUsuarioExistente(grupoUsuario);
		return repository.save(grupoUsuario);
	}

	public GrupoUsuario atualizar(GrupoUsuario grupoUsuario) {
		return adicionar(grupoUsuario);
	}

	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RegistroNaoEncontradoException("Grupo de Usuário", id);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroEmUsoException("Grupo de Usuário", id);
		}
	}

	public List<GrupoUsuario> listarTodos() {
		return repository.findAll();
	}

	public GrupoUsuario buscarPorId(Long id) {
		GrupoUsuario grupoUsuarioSalvo = repository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de Grupo de Usuário com esse código %d", id)));
		return grupoUsuarioSalvo;
	}

	public void grupoUsuarioExistente(GrupoUsuario grupoUsuario) {
		boolean grupoUsuarioSalvo = repository.findByNome(grupoUsuario.getNome()).stream()
				.anyMatch(grupoExistente -> !grupoExistente.equals(grupoUsuario));
		if (grupoUsuarioSalvo) {
			throw new NegocioException(
					String.format("Já existe um cadastro de Grupo de Usuário com nome %s!", grupoUsuario.getNome()));
		}
	}

}
