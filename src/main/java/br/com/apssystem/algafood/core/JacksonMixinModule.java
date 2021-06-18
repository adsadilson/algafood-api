package br.com.apssystem.algafood.core;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.apssystem.algafood.api.model.mixin.CidadeMixin;
import br.com.apssystem.algafood.api.model.mixin.CozinhaModel;
import br.com.apssystem.algafood.api.model.mixin.RestauranteModel;
import br.com.apssystem.algafood.domain.model.Cidade;
import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteModel.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaModel.class);
	}

}
