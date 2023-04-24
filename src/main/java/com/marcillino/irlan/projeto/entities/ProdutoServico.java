package com.marcillino.irlan.projeto.entities;

import com.marcillino.irlan.projeto.entities.enums.EstadoProdutoServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Classe Responsável pelo mapeamento da entidade Produto/Serviço
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoServico implements Predicate<ProdutoServico> {

    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private boolean isProduto;
    private BigDecimal preco;
    private Double quantidade;
    private EstadoProdutoServico estado;

    @ManyToOne
    @JoinColumn(name ="itensPedido_id")
    private ItensPedido itensPedido;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isProduto() {
        return isProduto;
    }

    public void setIsProduto(boolean produto) {
        isProduto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ItensPedido getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
    }

    public EstadoProdutoServico getEstado() {
        return estado;
    }

    public void setEstado(EstadoProdutoServico estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoServico that = (ProdutoServico) o;
        return isProduto == that.isProduto && Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(preco, that.preco) && Objects.equals(quantidade, that.quantidade) && estado == that.estado && Objects.equals(itensPedido, that.itensPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, isProduto, preco, quantidade, estado, itensPedido);
    }

    @Override
    public String toString() {
        return "ProdutoServico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", isProduto=" + isProduto +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", estado=" + estado +
                ", itensPedido=" + itensPedido +
                '}';
    }

    @Override
    public boolean test(ProdutoServico produtoServico) {
        return false;
    }

    @Override
    public Predicate<ProdutoServico> and(Predicate<? super ProdutoServico> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<ProdutoServico> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<ProdutoServico> or(Predicate<? super ProdutoServico> other) {
        return Predicate.super.or(other);
    }
}
