package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.api.mapper.FotoProdutoMapper;
import br.com.apssystem.algafood.api.model.FotoProdutoModel;
import br.com.apssystem.algafood.api.model.input.FotoProdutoInput;
import br.com.apssystem.algafood.domain.model.FotoProduto;
import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.service.FotoProdutoService;
import br.com.apssystem.algafood.domain.service.ProdutoService;
import br.com.apssystem.algafood.infrastructure.storage.FotoStorageService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api(tags = "Produto do Restaurante")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
@AllArgsConstructor
public class RestauranteProdutoFotoController {

    private ProdutoService produtoService;
    private FotoProdutoService fotoProdutoService;
    private FotoProdutoMapper fotoProdutoMapper;

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = produtoService.buscarPorIdAndRestaurante(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = fotoProdutoService.salvar(foto, arquivo.getInputStream());

        return fotoProdutoMapper.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId,
                        @PathVariable Long produtoId) {
        fotoProdutoService.excluir(restauranteId, produtoId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = fotoProdutoService.buscarFotoProdutoPorRestaurante(restauranteId, produtoId);
        return fotoProdutoMapper.toModel(fotoProduto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                        @RequestHeader(name = "accept") String acceptHeader) {
        try {
            FotoProduto fotoProduto = fotoProdutoService.buscarFotoProdutoPorRestaurante(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoProdutoService.
                    recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }

        } catch (EntidadeNaoEncontradaException | HttpMediaTypeNotAcceptableException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }
}
