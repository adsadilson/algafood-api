package br.com.apssystem.algafood.infrastructure.storage;

import br.com.apssystem.algafood.domain.model.FotoProduto;
import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface FotoStorageService {

    InputStream recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString()+ "_" + nomeOriginal;
    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeAquivo;
        private InputStream inputStream;

    }
}
