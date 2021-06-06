package br.com.apssystem.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import br.com.apssystem.algafood.domain.repository.GrupoUsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GrupoUsuarioService {

	private GrupoUsuarioRepository grupoUsuarioRepository;

	public GrupoUsuario salvar(GrupoUsuario grupoUsuario) {
		grupoUsuarioExistente(grupoUsuario);
		return grupoUsuarioRepository.save(grupoUsuario);
	}

	public GrupoUsuario atualizar(GrupoUsuario grupoUsuario, Long id) {
		GrupoUsuario grupoUsuarioSalvo = buscarPorId(id);
		BeanUtils.copyProperties(grupoUsuario, grupoUsuarioSalvo, "id");
		return salvar(grupoUsuarioSalvo);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		grupoUsuarioRepository.deleteById(id);
	}

	public List<GrupoUsuario> listarTodos() {
		return grupoUsuarioRepository.findAll();
	}

	public GrupoUsuario buscarPorId(Long id) {
		GrupoUsuario grupoUsuarioSalvo = grupoUsuarioRepository.findById(id).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum cadastro de Grupo de Usuário com esse código %d", id)));
		return grupoUsuarioSalvo;
	}

	public void grupoUsuarioExistente(GrupoUsuario grupoUsuario) {
		boolean grupoUsuarioSalvo = grupoUsuarioRepository.findByNome(grupoUsuario.getNome()).stream()
				.anyMatch(grupoExistente -> !grupoExistente.equals(grupoUsuario));
		if (grupoUsuarioSalvo) {
			throw new NegocioException(
					String.format("Já existe um cadastro de Grupo de Usuário com nome %s!", grupoUsuario.getNome()));
		}
	}

}
