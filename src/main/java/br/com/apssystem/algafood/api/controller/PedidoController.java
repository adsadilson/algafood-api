package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.controller.openapi.controller.PedidoControllerOpenApi;
import br.com.apssystem.algafood.api.mapper.PedidoMapper;
import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.core.pageableTranslator.PageableTranslator;
import br.com.apssystem.algafood.core.utils.ResourceUriHelper;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.domain.model.filter.PedidoFilter;
import br.com.apssystem.algafood.domain.service.PedidoService;
import br.com.apssystem.algafood.infrastructure.repository.specification.PedidoSpecification;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PedidoController implements PedidoControllerOpenApi {

    private PedidoService pedidoService;
    private PedidoMapper mapper;
    private PedidoResumoMapper pedidoResumoMapper;
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel salvar(@Valid @RequestBody PedidoInput input) {
        Pedido pedido = mapper.toDomainObject(input);
        ResourceUriHelper.addUriInResponseHeader(pedido.getId());
        return mapper.toModel(pedidoService.salvar(pedido));
    }

    @PutMapping
    public ResponseEntity<PedidoModel> atualizar(@Valid @RequestBody PedidoInput input) {
        Pedido pedido = pedidoService.buscarPorCodigo(input.getCodigo());
        mapper.copyToDomainObject(input, pedido);
        return ResponseEntity.ok(mapper.toModel(pedidoService.atualizar(pedido)));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable String codigo) {
        pedidoService.excluir(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PedidoModel> buscarPorCodigo(@PathVariable String codigo) {
        Pedido pedido = pedidoService.buscarPorCodigo(codigo);
        var pedidoModel = mapper.toModel(pedido);
        return ResponseEntity.ok(pedidoModel);
    }

    @GetMapping
    public PagedModel<PedidoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 2) Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidosPage = pedidoService.pesquisar(PedidoSpecification.filter(filtro), pageable);
        PagedModel<PedidoModel> pedidoModelPagedModel = pagedResourcesAssembler
                .toModel(pedidosPage,mapper);
        return pedidoModelPagedModel;
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
