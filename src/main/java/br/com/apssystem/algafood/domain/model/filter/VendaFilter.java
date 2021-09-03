package br.com.apssystem.algafood.domain.model.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
public class VendaFilter {

    private Long restauranteId;

    private String dataCriacaoInicio;

    private String dataCriacaoFim;
}
