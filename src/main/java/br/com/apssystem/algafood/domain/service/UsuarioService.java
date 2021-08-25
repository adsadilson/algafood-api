package br.com.apssystem.algafood.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.exception.RegistroEmUsoException;
import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.Usuario;
import br.com.apssystem.algafood.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private static final String NOMECLASS = "Usuário";
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(NOMECLASS, id));
    }

    public void usuarioExistente(Usuario user) {
        boolean result = repository.findByEmail(user.getEmail()).stream()
                .anyMatch(userExistente -> !userExistente.equals(user));
        if (result) {
            throw new RegistroEmUsoException(
                    String.format("Já existe um Usuário cadastro com esse e-mail %s", user.getEmail()));
        }
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        usuarioExistente(usuario);
        if (usuario.getId() == null) {
            usuario.setDataCadastro(LocalDateTime.now());
        }
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Usuario usuario) {
        return save(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(NOMECLASS, id);
        } catch (DataIntegrityViolationException e) {
            throw new RegistroEmUsoException(NOMECLASS, id);
        }
    }

    @Transactional
    public void trocarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarPorId(id);
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
    }
}
