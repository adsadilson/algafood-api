package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.apssystem.algafood.api.mapper.CozinhaMapper;
import br.com.apssystem.algafood.api.model.CozinhaModel;
import br.com.apssystem.algafood.api.model.input.CozinhaInput;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.service.CozinhaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController {

    private CozinhaService serivce;
    private CozinhaMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel salvar(@Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinha = mapper.toDomainObject(cozinhaInput);
        return mapper.toModel(serivce.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaModel atualizar(@Valid @RequestBody CozinhaInput cozinhaInput, @PathVariable Long id) {
        Cozinha cozinha = serivce.buscarPorId(id);
        return mapper.toModel(serivce.atualizar(cozinha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        serivce.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public CozinhaModel buscarPorId(@PathVariable Long id) {
        return mapper.toModel(serivce.buscarPorId(id));
    }

    @GetMapping("/porNome/{nome}")
    public Page<CozinhaModel> buscarPorNome(@PageableDefault(size = 10) Pageable pageable,
                                            @PathVariable String nome) {
        Page<Cozinha> cozinhasPage = serivce.buscarPorNome(pageable, nome);
        List<CozinhaModel> cozinhasModel = mapper.toCollectionModel(cozinhasPage.getContent());
        Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable,
                cozinhasPage.getTotalElements());
        return cozinhasModelPage;
    }

    @GetMapping
    public Page<CozinhaModel> listarTodos(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = serivce.listarTodos(pageable);
        List<CozinhaModel> cozinhasModel = mapper.toCollectionModel(cozinhasPage.getContent());
        Page<CozinhaModel> cozinhasModelPage = new PageImpl<>(cozinhasModel, pageable,
                cozinhasPage.getTotalElements());
        return cozinhasModelPage;
    }

}
