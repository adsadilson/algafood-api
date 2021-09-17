package br.com.apssystem.algafood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.apssystem.algafood.api.mapper.UsuarioMapper;
import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.GrupoUsuarioIdInput;
import br.com.apssystem.algafood.api.model.input.SenhaInput;
import br.com.apssystem.algafood.api.model.input.UsuarioAtulizarInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import br.com.apssystem.algafood.domain.model.Usuario;
import br.com.apssystem.algafood.domain.repository.GrupoUsuarioRepository;
import br.com.apssystem.algafood.domain.service.UsuarioService;
import lombok.AllArgsConstructor;

@Api(tags = "Usuários")
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService service;
    private GrupoUsuarioRepository grupoUsuarioRepository;
    private UsuarioMapper mapper;

    @ApiOperation("Cadastrar um usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioModel> salvar(@Valid @RequestBody UsuarioInput input) {
        Usuario usuario = mapper.toDomainObject(input);

        List<Long> idsGrupos = input.getGrupoUsuarioIdInput()
                .stream()
                .map(GrupoUsuarioIdInput::getId)
                .collect(Collectors.toList());

        List<GrupoUsuario> grupos = grupoUsuarioRepository.findAllById(idsGrupos);
        usuario.getGrupos().addAll(grupos);
        return ResponseEntity.ok(mapper.toModel(service.save(usuario)));
    }

    @ApiOperation("Atualizar um usuário")
    @PutMapping
    public ResponseEntity<UsuarioModel> atualizar(@Valid @RequestBody UsuarioAtulizarInput input) {
        Usuario usuario = service.buscarPorId(input.getId());

        List<Long> idsGrupos = input.getGrupoUsuarioIdInput()
                .stream()
                .map(GrupoUsuarioIdInput::getId)
                .collect(Collectors.toList());

        List<GrupoUsuario> grupos = grupoUsuarioRepository.findAllById(idsGrupos);
        usuario.getGrupos().clear();
        usuario.getGrupos().addAll(grupos);
        mapper.copyToDomainObject(input, usuario);
        return ResponseEntity.ok(mapper.toModel(service.atualizar(usuario)));
    }

    @ApiOperation("Excluir um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Busca todos os usuários")
    @GetMapping
    public List<UsuarioModel> listarTodos() {
        return mapper.toCollectionModel(service.listarTodos());
    }

    @ApiOperation("Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toModel(service.buscarPorId(id)));
    }

    @ApiOperation("Alterar a senha do usuário")
    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senha) {
        service.trocarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
