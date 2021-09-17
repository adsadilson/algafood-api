package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.api.model.EnderecoModel;
import br.com.apssystem.algafood.api.model.EnderecoViaCepModel;
import br.com.apssystem.algafood.domain.model.Endereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class ViaCepService {

    private WebClient client;

    public EnderecoViaCepModel buscarCEP(String cep) {
        var endereco = this.client.get()
                .uri("{cep}/json/", cep)
                .retrieve()
                .bodyToMono(EnderecoViaCepModel.class);
      return  endereco.block();
    }

}
