package com.marcillino.irlan.projeto.services;

import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import com.marcillino.irlan.projeto.repositories.PedidoRepository;
import com.marcillino.irlan.projeto.repositories.ProdutoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Service encarregado de gerenciar métodos e lógicas que envolvem a entidade ProdutoServico
 * @author Irlan
 */
@Service
public class ProdutoServicoService {

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Método que retorna lista paginada de todos os produtos/serviços cadastrados
     * @param pageable
     * @return
     */
    public Page<ProdutoServico> findAll(Pageable pageable) {
        return produtoServicoRepository.findAll(pageable);
    }

    /**
     * Método responsável por encontrar um produto/serviço específico, utilizando o seu ID
      * @param id
     * @return
     */
    public ProdutoServico findById(UUID id) {
        Optional<ProdutoServico> produtoServico = produtoServicoRepository.findById(id);
        return produtoServico.get();
    }

    /**
     * Método responsável por criar um novo produto/serviço
     * @param produtoServico
     * @return
     */
    public ProdutoServico criarProdutoServico(ProdutoServico produtoServico) {
        if (Objects.nonNull(produtoServico)) {
            return produtoServicoRepository.save(produtoServico);
        }
        return null;
    }

    /**
     * Método responsável por alterar um produto/serviço
     * Obs: Caso não seja existente, o método irá criar um
     * @param produtoServico
     * @return
     */
    public ProdutoServico alterarProdutoServico(ProdutoServico produtoServico) {
        try {
            Optional<ProdutoServico> produtoExistente = produtoServicoRepository.findById(produtoServico.getId());
            produtoExistente.get().setNome(produtoServico.getNome());
            produtoExistente.get().setIsProduto(produtoServico.isProduto());
            produtoExistente.get().setPreco(produtoServico.getPreco().setScale(2));
            produtoExistente.get().setQuantidade(produtoServico.getQuantidade());
            produtoExistente.get().setEstado(produtoServico.getEstado());
            return produtoServicoRepository.save(produtoExistente.get());
        } catch (NoSuchElementException e) {
            e.getMessage();
            produtoServico.setId(null);
            return produtoServicoRepository.save(produtoServico);
        }
    }

    /**
     * Método responsável por deletar um produto/serviço
     * @param produtoServico
     */
    public void deletarProdutoServico(ProdutoServico produtoServico) {
        try {
            for (Pedido pedido : pedidoRepository.findAll()) {
                if (pedido.getItens().getProdutosServicos().stream().anyMatch(produtoServico)) {
                    throw new Exception("O produto está associado a um pedido, portanto não pode ser deletado!");
                }
                produtoServicoRepository.deleteById(produtoServico.getId());
            }
        } catch (ConfigDataResourceNotFoundException e) {
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}