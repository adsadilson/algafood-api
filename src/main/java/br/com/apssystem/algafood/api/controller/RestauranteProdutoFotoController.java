package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.exception.NegocioException;
import br.com.apssystem.algafood.api.model.input.FotoProdutoInput;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
@AllArgsConstructor
public class RestauranteProdutoFotoController {


    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                             @Valid FotoProdutoInput fotoProdutoInput) {


        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("C:/Projetos/algafood-api/fotos/catalogo", nomeArquivo);
        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());


        /*String pastaDoUsuario = System.getProperty("user.home");
        Paths.get("E:\\spring\\catalogo\\", nomeArquivo);//Java 8
        System.out.printf(pastaDoUsuario);*/

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
