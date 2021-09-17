package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.mapper.PedidoMapper;
import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.core.pageableTranslator.PageableTranslator;
import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.domain.model.filter.PedidoFilter;
import br.com.apssystem.algafood.domain.service.PedidoService;
import br.com.apssystem.algafood.infrastructure.repository.specification.PedidoSpecification;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Pedidos")
@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class
PedidoController {

    private PedidoService pedidoService;
    private PedidoMapper mapper;
    private PedidoResumoMapper pedidoResumoMapper;

    @ApiOperation("Cadastrar um pedido")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel salvar(@Valid @RequestBody PedidoInput input) {
        Pedido pedido = mapper.toDomainObject(input);
        return mapper.toModel(pedidoService.salvar(pedido));
    }

    @ApiOperation("Atualizar um pedido")
    @PutMapping
    public ResponseEntity<PedidoModel> atualizar(@Valid @RequestBody PedidoInput input) {
        Pedido pedido = pedidoService.buscarPorCodigo(input.getCodigo());
        mapper.copyToDomainObject(input, pedido);
        return ResponseEntity.ok(mapper.toModel(pedidoService.atualizar(pedido)));
    }

    @ApiOperation("Excluir um pedido por Codigo")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable String codigo) {
        pedidoService.excluir(codigo);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Busca um pedido por Codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<PedidoModel> buscarPorCodigo(@PathVariable String codigo) {
        Pedido pedido = pedidoService.buscarPorCodigo(codigo);
        return ResponseEntity.ok(mapper.toModel(pedido));
    }

    @ApiOperation("Busca um pedido por filtro")
    @GetMapping
    public Page<PedidoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 2) Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidosPage = pedidoService.pesquisar(PedidoSpecification.filter(filtro), pageable);
        List<PedidoModel> pedidoResumoModels = pedidoResumoMapper.toColletionModel(pedidosPage.getContent());
        Page<PedidoModel> resumoModelPage = new PageImpl<>(pedidoResumoModels, pageable,
                pedidosPage.getTotalElements());
        return resumoModelPage;
    }

    private Pageable traduzirPageable(Pageable pageable){
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "cliente.nome", "cliente.nome",
                "valorTotal", "valorTotal"
        );
        return PageableTranslator.translate(pageable,mapeamento);
    }

}
