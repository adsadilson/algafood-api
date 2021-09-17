package br.com.apssystem.algafood.api.openFeign;

import br.com.apssystem.algafood.api.model.EnderecoViaCepModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cep", url = "https://viacep.com.br/ws/")
public interface CEPFeign {

    @GetMapping("/json/")
    EnderecoViaCepModel buscarCEP(String cep);
}
