package br.com.apssystem.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;

import br.com.apssystem.algafood.api.controller.openapi.model.CozinhasModelOpenApi;
import br.com.apssystem.algafood.api.controller.openapi.model.PageableModelOpenApi;
import br.com.apssystem.algafood.api.exception.Problem;
import br.com.apssystem.algafood.api.model.CozinhaModel;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{

	 TypeResolver typeResolver = new TypeResolver();
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
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.ignoredParameterTypes(ServletWebRequest.class)
				.additionalModels(typeResolver.resolve(Problem.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, CozinhaModel.class),
						CozinhasModelOpenApi.class))
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

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do servidor")
						.responseModel(new ModelRef("Problema"))
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
						.build()
		);
	}

	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição inválida (erro do cliente)")
						.responseModel(new ModelRef("Problema"))
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno no servidor")
						.responseModel(new ModelRef("Problema"))
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado")
						.responseModel(new ModelRef("Problema"))
						.build()
		);
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição inválida (erro do cliente)")
						.responseModel(new ModelRef("Problema"))
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno no servidor")
						.responseModel(new ModelRef("Problema"))
						.build()
		);
	}


	@SuppressWarnings("unused")
	private <T> AlternateTypeRule buildPageTypeRole(Class<T> classModel, Class<T> classModelOpenApi) {
		return AlternateTypeRules.newRule(
				typeResolver.resolve(Page.class, classModel), classModelOpenApi);
	}

	// @formatter:on
}
