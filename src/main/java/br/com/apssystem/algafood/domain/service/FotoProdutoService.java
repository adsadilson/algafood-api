package br.com.apssystem.algafood.domain.service;

import br.com.apssystem.algafood.api.exception.EntidadeNaoEncontradaException;
import br.com.apssystem.algafood.domain.model.FotoProduto;
import br.com.apssystem.algafood.domain.model.Produto;
import br.com.apssystem.algafood.domain.repository.ProdutoRepository;
import br.com.apssystem.algafood.infrastructure.storage.FotoProdutoNaoEncontradaException;
import br.com.apssystem.algafood.infrastructure.storage.FotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class FotoProdutoService {

    private ProdutoRepository produtoRepository;
    private RestauranteService restauranteService;
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        var nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        var fotoSalva = produtoRepository.buscarFotoProdutoPorRestaurante(restauranteId,produtoId);
        if(fotoSalva.isPresent()){
            nomeArquivoExistente = fotoSalva.get().getNomeArquivo();
            produtoRepository.excluir(fotoSalva.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto =  produtoRepository.save(foto);
        produtoRepository.flush();

        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeAquivo(foto.getNomeArquivo())
                .contentType(foto.getContentType())
                .inputStream(dadosArquivo)
                .build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        var restaurante = restauranteService.buscarPorId(restauranteId);
        FotoProduto foto = buscarFotoProdutoPorRestaurante(restaurante.getId(), produtoId);
        produtoRepository.excluir(foto);
        produtoRepository.flush();
        fotoStorageService.remover(foto.getNomeArquivo());
    }

    public FotoProduto buscarFotoProdutoPorRestaurante(Long restauranteId, Long produtoId) {
        return produtoRepository.buscarFotoProdutoPorRestaurante(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
    }

    public FotoStorageService.FotoRecuperada recuperar(String nomeArquivo) {

        return fotoStorageService.recuperar(nomeArquivo);
    }

}
