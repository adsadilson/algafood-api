package br.com.apssystem.algafood.test;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.apssystem.algafood.domain.model.Cozinha;
import br.com.apssystem.algafood.domain.repository.CozinhaRepository;
import br.com.apssystem.algafood.util.DatabaseCleaner;
import br.com.apssystem.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.test.properties")
class CozinhaIntegrationIT {

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	private Long  qtdeCozinha;
	private String jsonCorretoCozinhaChinesa;
	private Cozinha cozinhaAmericana;

	private static final int COZINHA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;

	// @formatter:off
	
	@BeforeEach
	public void setUp() {
		// Habilitar o log no console para mostrar o erro apresentado.
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "algafood-api/cozinhas";
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/cozinha/cozinha-chinesa.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConterListQtdeCozinhas_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			//.body("", Matchers.hasValue(4))
			.body("nome", Matchers.hasItems("Internacional", "Italiana"));
		// Validar se no corpo da resposta tem as conzinhas mecionadas
	}

	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
			.pathParam("id", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured.given()
			.pathParam("id", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}").then()
		.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	// @formatter:on

	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaAmericana = cozinhaRepository.save(cozinhaAmericana);

		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Internacional");
		cozinhaRepository.save(cozinha3);

		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Italiana");
		cozinhaRepository.save(cozinha4);

		this.qtdeCozinha = cozinhaRepository.count();
	}

}
