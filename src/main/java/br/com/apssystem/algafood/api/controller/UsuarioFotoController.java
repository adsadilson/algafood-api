package br.com.apssystem.algafood.api.controller;

import br.com.apssystem.algafood.api.model.input.FotoUsuarioInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios/{usuarioId}/foto")
public class UsuarioFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long usuarioId, FotoUsuarioInput fotoUsuarioInput) {

        var nomeArquivo = UUID.randomUUID().toString()
                + "_" + fotoUsuarioInput.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("C:/Projetos/algafood-api/fotos/usuarios", nomeArquivo);

        System.out.println(fotoUsuarioInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoUsuarioInput.getArquivo().getContentType());

        try {
            fotoUsuarioInput.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
