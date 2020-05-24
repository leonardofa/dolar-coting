package br.com.leonardo.poc.dolarcoting.dao;

import br.com.leonardo.poc.dolarcoting.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, String> {
}
