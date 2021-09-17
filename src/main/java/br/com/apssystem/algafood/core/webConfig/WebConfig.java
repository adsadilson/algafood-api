package br.com.apssystem.algafood.core.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@Configuration
@EnableSwagger2
public class WebConfig implements WebMvcConfigurer{
	
	// @formatter:off
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.apssystem.algafood.api"))
				.paths(PathSelectors.any())
				//.paths(PathSelectors.ant("/restaurante/*"))
				.build()
				.apiInfo(apiInfo())
				.tags(tags()[0],tags());
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("Apssytem", "https://www.algaworks.com", "adilson.curso@yahoo.com.b"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new org.springframework.web.filter.ShallowEtagHeaderFilter();
	}


	private Tag[] tags() {
		return new Tag[]{
				new Tag("Cidades", "Gerencia as cidades"),
				new Tag("Cozinhas", "Gerencia as cozinhas"),
				new Tag("Endereços", "Gerencia os endereço"),
				new Tag("Estados", "Gerencia os estados"),
				new Tag("Estatisticas", "Gerencia as estatisticas"),
				new Tag("Forma de Pagamentos", "Gerencia as formas de pagamentos"),
				new Tag("Fluxo de Pedidos", "Gerencia o fluxo do pedido"),
				new Tag("Grupos de Usuários", "Gerencia o grupo de usuários"),
				new Tag("Pedidos", "Gerencia o pedido"),
				new Tag("Permissões", "Gerencia as permissões"),
				new Tag("Produtos", "Gerencia os produtos"),
				new Tag("Restaurantes", "Gerencia os restaurantes"),
				new Tag("Produto do Restaurante", "Gerencia o produto do restaurante"),
				new Tag("Usuários", "Gerencia os usuários"),
				new Tag("Foto do Usuário", "Gerencia as fotos do usuários")
		};
	}

	// @formatter:on
}
