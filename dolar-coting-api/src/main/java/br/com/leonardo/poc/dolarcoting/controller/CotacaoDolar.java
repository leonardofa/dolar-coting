package br.com.leonardo.poc.dolarcoting.controller;

import br.com.leonardo.poc.dolarcoting.dadosabertos.ResponseCotacao;
import br.com.leonardo.poc.dolarcoting.dao.CotacaoRepository;
import br.com.leonardo.poc.dolarcoting.dao.RequisicaoRepository;
import br.com.leonardo.poc.dolarcoting.domain.Cotacao;
import br.com.leonardo.poc.dolarcoting.domain.Requisicao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("dolar")
@RequiredArgsConstructor
public class CotacaoDolar {

  private final RequisicaoRepository requisicaoRepository;
  private final CotacaoRepository cotacaoRepository;

  @ApiOperation(value = "Retorna a cotação atual do dólar", response = Requisicao.class)
  @ApiResponses({
    @ApiResponse(code = 200, message = "Requisição com a cotação atual", response = Requisicao.class),
    @ApiResponse(code = 400, message = "Erro nos dados informados para requisição", response = String.class),
    @ApiResponse(code = 500, message = "Erro não esperado no servidor", response = String.class)})
  @GetMapping("atual")
  public ResponseEntity<?> atual() {
    final RestTemplate rest = new RestTemplate();

    try {
      LocalDate referencia = obtemDataUtil(LocalDate.now());

      Cotacao cotacao = cotacaoRepository.findByReferencia(referencia);

      List<Cotacao> cotacoes = new ArrayList<>();

      if (cotacao == null) {
        String referenciaString = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(obtemDataUtil(LocalDate.now()));
        final String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia" +
          "(dataCotacao=@dataCotacao)?@dataCotacao='{0}'";
        ResponseEntity<JsonNode> response = rest.getForEntity(url, JsonNode.class, referenciaString);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ResponseCotacao responseCotacao = mapper.treeToValue(response.getBody(), ResponseCotacao.class);

        responseCotacao.getResultados().forEach(cotRef -> {
          cotRef.setReferencia(cotRef.getDataHoraCotacao().toLocalDate());
          cotacoes.add(cotacaoRepository.save(cotRef));
        });
      } else {
        cotacoes.add(cotacao);
      }

      Requisicao requisicao = new Requisicao();
      requisicao.setRequsisicao(LocalDateTime.now());
      requisicao.setCotacoes(cotacoes);

      return ResponseEntity.ok(requisicaoRepository.save(requisicao));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

  }

  private LocalDate obtemDataUtil(LocalDate now) {

    if (now.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
      return now.minus(1, ChronoUnit.DAYS);
    }

    if (now.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
      return now.minus(2, ChronoUnit.DAYS);
    }

    return now;
  }

}
