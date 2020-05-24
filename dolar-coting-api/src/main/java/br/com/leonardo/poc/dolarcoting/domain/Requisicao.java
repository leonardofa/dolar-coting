package br.com.leonardo.poc.dolarcoting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Requisicao {

  @Id
  @GeneratedValue(generator = "uuid_generate_v4()", strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToMany
  private List<Cotacao> cotacoes;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime requsisicao;

}
