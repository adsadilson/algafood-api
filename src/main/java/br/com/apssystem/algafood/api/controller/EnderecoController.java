package br.com.apssystem.algafood.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apssystem.algafood.api.model.EnderecoViaCepModel;
import br.com.apssystem.algafood.domain.service.ViaCepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "Endereços")
@RestController
@RequestMapping(path = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EnderecoController {

    private ViaCepService cepService;
    	

    /*@GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCepModel> buscarEndereco(@PathVariable String cep){
       var end = cepFeign.buscarCEP(cep);
        return ResponseEntity.ok(end);
    }*/
    @ApiOperation("Busca uma endereço por CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCepModel> buscarEndereco(@PathVariable String cep){
       var end = cepService.buscarCEP(cep);
        return ResponseEntity.ok(end);
    }
}
