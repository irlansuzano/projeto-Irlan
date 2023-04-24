package com.marcillino.irlan.projeto.services;

import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import com.marcillino.irlan.projeto.entities.enums.EstadoProdutoServico;
import com.marcillino.irlan.projeto.repositories.ItensPedidoRepository;
import com.marcillino.irlan.projeto.repositories.ProdutoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Service encarregado de gerenciar métodos e lógicas que envolvem a entidade ItensPedido
 * @author Irlan
 */
@Service
public class ItensPedidoService {

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    /**
     * Método que retorna lista paginada de todos os objetos de ItensPedido cadastrados
     *
     * @param pageable
     * @return
     */
    public Page<ItensPedido> findAll(Pageable pageable) {
        return itensPedidoRepository.findAll(pageable);
    }

    /**
     * Método responsável por encontrar um objeto ItensPedido específico, utilizando o seu ID
     *
     * @param id
     * @return
     */
    public ItensPedido findById(UUID id) {
        Optional<ItensPedido> itensPedido = itensPedidoRepository.findById(id);
        return itensPedido.get();
    }

    /**
     * Método responsável por criar um Objeto ItensPedido
     *
     * @param itensPedido
     * @return
     */
    public ItensPedido criarItensPedido(ItensPedido itensPedido) {
        if (Objects.nonNull(itensPedido)) {
            return itensPedidoRepository.save(itensPedido);
        }
        throw new NullPointerException("Não se pode registrar um objeto vazio!");
    }

    /**
     * Método responsável por alterar a lista de itens do pedido
     *
     * @param itensPedido
     * @return
     */
    public ItensPedido alterarListaItensPedidos(ItensPedido itensPedido) {
        try {
            Optional<ItensPedido> itensPedidoExistente = itensPedidoRepository.findById(itensPedido.getId());
            itensPedidoExistente.get().setProdutosServicos(itensPedido.getProdutosServicos());
            return itensPedidoRepository.save(itensPedidoExistente.get());
        } catch (ConfigDataResourceNotFoundException e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * Método responsável por adicionar um novo produto/serviço à lista do pedido
     * @param idLista
     * @param idProduto
     * @return
     */
    public ItensPedido adicionarItem(UUID idLista, UUID idProduto){
        Optional<ItensPedido> listaItens = itensPedidoRepository.findById(idLista);
        Optional<ProdutoServico> produtoSelecionado = produtoServicoRepository.findById(idProduto);
        if(EstadoProdutoServico.INATIVO.equals(produtoSelecionado.get())){
            throw new InvalidParameterException("Produto inativo!");
        }
        listaItens.get().getProdutosServicos().add(produtoSelecionado.get());
        return itensPedidoRepository.save(listaItens.get());
    }


    /**
     * Método responsável por deletar uma lista de itens do pedido
     * @param id
     */
    public void deletarItemPedido(UUID id) {
        try {
            itensPedidoRepository.deleteById(id);
        }catch(ConfigDataResourceNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Método responsável por remover um item da lista de itens do pedido
     * @param idLista
     * @param idProduto
     * @return
     */
    public ItensPedido remorverItem(UUID idLista, UUID idProduto){
        Optional<ProdutoServico> produtoSelecionado = produtoServicoRepository.findById(idProduto);
        Optional<ItensPedido> listaItens = itensPedidoRepository.findById(idLista);
        listaItens.get().getProdutosServicos().remove(produtoSelecionado.get());
        return itensPedidoRepository.save(listaItens.get());
    }
}