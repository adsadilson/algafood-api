package br.com.apssystem.algafood.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeradorChar {

	@Autowired
	GeradorCharRepository repository;

	public String criar(Class clazz) {

		StringBuilder codigo = new StringBuilder();

		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"A", "B", "C", "D", "E", "F", "G", "G", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z", "x", "y", "z","+","-","/","*","_","!","@","$","%","&"};

		for (int i = 0; i < 10; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			codigo.append(caracteres[posicao]);
		}
		boolean existe = repository.gerarChar(clazz, codigo.toString());
		if (existe) {
			criar(clazz);
		}
		return codigo.toString();
	}

}
