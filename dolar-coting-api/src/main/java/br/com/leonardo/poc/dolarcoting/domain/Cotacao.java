package br.com.leonardo.poc.dolarcoting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Cotacao {

  @Id
  @GeneratedValue(generator = "uuid_generate_v4()", strategy = GenerationType.AUTO)
  private UUID id;

  private BigDecimal cotacaoCompra;

  private BigDecimal cotacaoVenda;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate referencia;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime dataHoraCotacao;

}
