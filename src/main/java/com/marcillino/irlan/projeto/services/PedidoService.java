package com.marcillino.irlan.projeto.services;

import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import com.marcillino.irlan.projeto.entities.enums.EstadoPedido;
import com.marcillino.irlan.projeto.repositories.ItensPedidoRepository;
import com.marcillino.irlan.projeto.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Service responsável por gerenciar métodos e lógicas que envolvem a entidade Pedido
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;


    private static final BigDecimal ZERO = BigDecimal.valueOf(0);
    private static final BigDecimal CINCO = BigDecimal.valueOf(5);
    private static final BigDecimal CEM = BigDecimal.valueOf(100);

    /**
     * Método que retorna lista paginada de todos os pedidos existentes no sistema
     * @param pageable
     * @return
     */
    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    /**
     * Método responsável pelo retorno de um Pedido pelo seu ID
     * @param id
     * @return
     */
    public Pedido findById(UUID id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.get();
    }

    /**
     * Método responsável por criar um novo pedido
     * @param pedido
     * @return
     */
    public Pedido criarPedido(Pedido pedido) {
        if (Objects.nonNull(pedido)) {
            pedido.setValorPedido(calcularValorPedido(pedido).setScale(2));
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    /**
     * Método responsável por alterar um pedido
     * Obs: Caso não seja existente, o método irá criar um
     * @param pedido
     * @return
     */
    public Pedido alterarPedido(Pedido pedido) {
        try {
            Optional<Pedido> pedidoExistente = pedidoRepository.findById(pedido.getId());
            pedidoExistente.get().setStatusPedido(pedido.getStatusPedido());
            pedidoExistente.get().setItens(pedido.getItens());
            pedidoExistente.get().setValorPedido(calcularValorPedido(pedido).setScale(2));
            return pedidoRepository.save(pedidoExistente.get());
        } catch (NoSuchElementException e) {
            e.getMessage();
            pedido.setId(null);
            return pedidoRepository.save(pedido);
        }
    }

    /**
     * Método responsável por deletar um pedido
     * @param pedido
     */
    public void deletarPedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

    /**
     * Método responsável pelo calculo do valor do pedido
     * @param pedido
     * @return BigDecimal valorFinal
     */
    private BigDecimal calcularValorPedido(Pedido pedido) {
        BigDecimal valorFinal = ZERO;
        for(ProdutoServico produtoServico : pedido.getItens().getProdutosServicos()){
            if(produtoServico.isProduto() && EstadoPedido.ABERTO.equals(pedido.getStatusPedido())){
                valorFinal.add(produtoServico.getPreco()
                        .subtract((produtoServico.getPreco().divide(CEM)).multiply(CINCO))).setScale(2);
            }else{
                valorFinal.add(produtoServico.getPreco());
            }
        }
        return valorFinal;

    }

    /**
     * Método responsável por vincular uma lista de itens com o pedido
     * @param id
     * @param idLista
     * @return
     */
    public Pedido adicionarLista(UUID id, UUID idLista) {
        Optional<Pedido> pedidoSelecionado = pedidoRepository.findById(id);
        Optional<ItensPedido> itensSelecionados = itensPedidoRepository.findById(idLista);
        pedidoSelecionado.get().setItens(itensSelecionados.get());
        return pedidoRepository.save(pedidoSelecionado.get());
    }
}
