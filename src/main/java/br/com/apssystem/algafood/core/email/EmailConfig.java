package br.com.apssystem.algafood.core.email;

import br.com.apssystem.algafood.infrastructure.email.EnvioEmailService;
import br.com.apssystem.algafood.infrastructure.email.FakeEnvioEmailService;
import br.com.apssystem.algafood.infrastructure.email.SmtpEnvioEmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EmailConfig {

    EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }
}
