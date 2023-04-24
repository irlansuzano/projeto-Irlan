package com.marcillino.irlan.projeto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe responsável pelo mapeamento da entidade ItensPedido
 * @author Irlan
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedido {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "itensPedido")
    private List<ProdutoServico> produtosServicos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ProdutoServico> getProdutosServicos() {
        return produtosServicos;
    }

    public void setProdutosServicos(List<ProdutoServico> produtoServico) {
        this.produtosServicos = produtoServico;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(produtosServicos, that.produtosServicos) &&  Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtosServicos, pedido);
    }

    @Override
    public String toString() {
        return "ItensPedido{" +
                "id=" + id +
                ", produtos/Serviços=" + produtosServicos +
                ", pedido=" + pedido +
                '}';
    }
}
