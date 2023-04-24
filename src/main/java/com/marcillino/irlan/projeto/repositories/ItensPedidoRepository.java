package com.marcillino.irlan.projeto.repositories;


import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, UUID> {

}
