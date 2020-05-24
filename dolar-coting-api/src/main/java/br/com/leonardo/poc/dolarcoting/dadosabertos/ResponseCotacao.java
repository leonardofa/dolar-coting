package br.com.leonardo.poc.dolarcoting.dadosabertos;

import br.com.leonardo.poc.dolarcoting.domain.Cotacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseCotacao {

  @JsonProperty("@odata.context")
  private String context;

  @JsonProperty("value")
  private List<Cotacao> resultados;

}
