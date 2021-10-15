package br.com.apssystem.algafood.api.controller.openapi.controller;

import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.mapper.UsuarioMapper;
import br.com.apssystem.algafood.api.model.UsuarioModel;
import br.com.apssystem.algafood.api.model.input.GrupoUsuarioIdInput;
import br.com.apssystem.algafood.api.model.input.SenhaInput;
import br.com.apssystem.algafood.api.model.input.UsuarioAtulizarInput;
import br.com.apssystem.algafood.api.model.input.UsuarioInput;
import br.com.apssystem.algafood.core.utils.ResourceUriHelper;
import br.com.apssystem.algafood.domain.model.GrupoUsuario;
import br.com.apssystem.algafood.domain.model.Usuario;
import br.com.apssystem.algafood.domain.repository.GrupoUsuarioRepository;
import br.com.apssystem.algafood.domain.service.UsuarioService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {


    @ApiOperation("Cadastrar um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrada"),
    })
    ResponseEntity<UsuarioModel> salvar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioInput input);

    @ApiOperation("Atualiza um usuário")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<UsuarioModel> atualizar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioAtulizarInput input) ;

    @ApiOperation("Excluir um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário excluído"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> excluir(Long id) ;

    @ApiOperation("Busca todos os usuários")
    CollectionModel<UsuarioModel> listarTodos();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    ResponseEntity<UsuarioModel> buscarPorId(
            @ApiParam(value = "ID de uma usuário", example = "1")
            Long id);

    @ApiOperation("Alterar a senha do usuário")
    void alterarSenha(Long id,
                      @ApiParam(value = "Senha do usuário", example = "sxkheaw451")
                      SenhaInput senha);
}
