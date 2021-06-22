package br.com.apssystem.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.apssystem.algafood.api.model.CozinhaModel;
import br.com.apssystem.algafood.api.model.RestauranteModel;
import br.com.apssystem.algafood.domain.model.Restaurante;

@Component
public class RestauranteAssembler {
	
	public static RestauranteModel toModel(Restaurante restaurante) {
		CozinhaModel cozinhaModel = new CozinhaModel();
		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setNome(restaurante.getCozinha().getNome());

		RestauranteModel restauranteModel = new RestauranteModel();
		restauranteModel.setId(restaurante.getId());
		restauranteModel.setAberto(restaurante.isAberto());
		restauranteModel.setAtivo(restaurante.isAtivo());
		restauranteModel.setDataAtualizacao(restaurante.getDataAtualizacao());
		restauranteModel.setDataCadastro(restaurante.getDataCadastro());
		restauranteModel.setFrete(restaurante.getFrete());
		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setCozinha(cozinhaModel);
		return restauranteModel;
	}

	public static List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
}
