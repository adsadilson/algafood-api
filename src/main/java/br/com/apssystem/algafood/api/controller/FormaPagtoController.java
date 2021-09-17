package br.com.apssystem.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.http.CacheControl;
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

import br.com.apssystem.algafood.api.mapper.FormaPagtoMapper;
import br.com.apssystem.algafood.api.model.FormaPagtoModel;
import br.com.apssystem.algafood.api.model.input.FormaPagtoInput;
import br.com.apssystem.algafood.domain.model.FormaPagto;
import br.com.apssystem.algafood.domain.service.FormaPagtoService;
import lombok.AllArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Api(tags = "Forma de Pagamentos")
@RestController
@RequestMapping("/formaPagtos")
@AllArgsConstructor
public class FormaPagtoController {

	private FormaPagtoService formaPagtoService;
	private FormaPagtoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagtoModel salvar(@Valid @RequestBody FormaPagtoInput formaPagtoInput) {
		FormaPagto formaPagto = mapper.toDomainObject(formaPagtoInput);
		return mapper.toModel(formaPagtoService.salvar(formaPagto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormaPagtoModel> atualizar(@Valid @RequestBody FormaPagtoInput formaPagtoInput,
			@PathVariable Long id) {
		FormaPagto formaPagto = mapper.toDomainObject(formaPagtoInput);
		return ResponseEntity.ok(mapper.toModel(formaPagtoService.atualizar(formaPagto, id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		formaPagtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagtoModel> buscarPorId(@PathVariable Long id) {
		FormaPagto formaPagto = formaPagtoService.buscarPorId(id);
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.MILLISECONDS))
				.body(mapper.toModel(formaPagto));
	}

	@GetMapping
	public ResponseEntity<List<FormaPagtoModel>> listarTodos(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = formaPagtoService.getDataUltimaAtualizacao();

		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}

		if (request.checkNotModified(eTag)) {
			return null;
		}

		List<FormaPagto> result = formaPagtoService.listarTodos();
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//				.cacheControl(CacheControl.noCache())
				.eTag(eTag)
				.body(mapper.toCollectionModel(result));
	}

}
