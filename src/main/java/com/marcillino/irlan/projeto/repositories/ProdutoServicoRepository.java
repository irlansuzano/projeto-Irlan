package com.marcillino.irlan.projeto.repositories;


import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID> {

}
