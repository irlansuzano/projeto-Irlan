package com.marcillino.irlan.projeto.entities;

import com.marcillino.irlan.projeto.entities.enums.EstadoPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe responsável pelo mapeamento da entidade Pedido
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(mappedBy = "pedido")
    private ItensPedido itens;
    private BigDecimal valorPedido;
    private EstadoPedido statusPedido = EstadoPedido.ABERTO;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ItensPedido getItens() {
        return itens;
    }

    public void setItens(ItensPedido itens) {
        this.itens = itens;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public EstadoPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(EstadoPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(itens, pedido.itens) && Objects.equals(valorPedido, pedido.valorPedido) && statusPedido == pedido.statusPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itens, valorPedido, statusPedido);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", itens=" + itens +
                ", valorPedido=" + valorPedido +
                ", statusPedido=" + statusPedido +
                '}';
    }
}
