package br.com.apssystem.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.apssystem.algafood.api.mapper.PedidoResumoMapper;
import br.com.apssystem.algafood.api.model.PedidoResumoModel;
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

import br.com.apssystem.algafood.api.mapper.PedidoMapper;
import br.com.apssystem.algafood.api.model.PedidoModel;
import br.com.apssystem.algafood.api.model.input.PedidoInput;
import br.com.apssystem.algafood.domain.model.Pedido;
import br.com.apssystem.algafood.domain.service.PedidoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class
PedidoController {

	private PedidoService pedidoService;
	private PedidoMapper mapper;
	private PedidoResumoMapper pedidoResumoMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel salvar(@Valid @RequestBody PedidoInput input) {
		Pedido pedido = mapper.toDomainObject(input);
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
		return ResponseEntity.ok(mapper.toModel(pedido));
	}

	@GetMapping
	public List<PedidoResumoModel> listarTodos() {
		return pedidoResumoMapper.toColletionModel(pedidoService.listarTodos());
	}

}
