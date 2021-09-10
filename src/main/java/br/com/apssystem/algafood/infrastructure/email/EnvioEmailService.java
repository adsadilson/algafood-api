package br.com.apssystem.algafood.infrastructure.email;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;

        @NonNull
        private String corpo;

        @Singular("atributo")
        private Map<String, Object> atributos;

    }
}
