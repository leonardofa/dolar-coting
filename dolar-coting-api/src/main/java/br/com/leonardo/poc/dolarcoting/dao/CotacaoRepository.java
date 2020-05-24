package br.com.leonardo.poc.dolarcoting.dao;

import br.com.leonardo.poc.dolarcoting.domain.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CotacaoRepository extends JpaRepository<Cotacao, String> {

  Cotacao findByReferencia(LocalDate referencia);

}
