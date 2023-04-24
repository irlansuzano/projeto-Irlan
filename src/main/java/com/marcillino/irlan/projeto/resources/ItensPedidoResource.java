package com.marcillino.irlan.projeto.resources;

import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.services.ItensPedidoService;
import com.marcillino.irlan.projeto.services.PedidoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe controladora dos endpoints de itens de pedido
 * @author Irlan
 */
@RestController
@RequestMapping(value = "/itens_pedidos")
public class ItensPedidoResource {

    @Autowired
    private ItensPedidoService itensPedidoService;

    /**
     * Endpoint que retorna a lista de todos os objetos itensPedido paginados
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable){
        return ResponseEntity.ok().body(itensPedidoService.findAll(pageable));
    }

    /**
     * Endpoint que retorna um objeto itensPedido bucando pelo ID
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItensPedido> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body( itensPedidoService.findById(id));
    }

    /**
     * Endpoint que cria um objeto ItensPedido
     * @param itensPedido
     * @return
     */
    @PostMapping(value = "/criar")
    public ResponseEntity<ItensPedido> criarListaItensPedido(@RequestBody ItensPedido itensPedido){
        return ResponseEntity.ok().body(itensPedidoService.criarItensPedido(itensPedido));
    }

    /**
     * Endpoint que altera um objeto itensPedido completo
     * @param id
     * @param itensPedido
     * @return
     */
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<ItensPedido> alterarListaItensPedido(@PathVariable UUID id, @RequestBody ItensPedido itensPedido){
        return ResponseEntity.ok().body(itensPedidoService.alterarListaItensPedidos(itensPedido));
    }

    /**
     * Endpoint que deleta um objeto itensPedido
     * @param id
     * @param itensPedido
     * @return
     */
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarListaItensPedido(@PathVariable UUID id, @RequestBody ItensPedido itensPedido){
        itensPedidoService.deletarItemPedido(itensPedido);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint que adiciona um novo produto/serviço a lista de itens
     * @param id
     * @param idItem
     * @return
     */
    @PutMapping(value = "/alterar/{id}/itens/{idItem}")
    public ResponseEntity<ItensPedido> adicionarProduto(@PathVariable UUID id, @PathVariable UUID idItem){
        return ResponseEntity.ok().body(itensPedidoService.adicionarItem(id, idItem));
    }

    /**
     * Endpoint que remove um protudo/serviço da lista de itens
     * @param id
     * @param idItem
     * @return
     */
    @DeleteMapping(value ="/alterar/{id}/itens/{idItem}")
    public ResponseEntity removerProdutoLista(@PathVariable UUID id, @PathVariable UUID idItem){
        itensPedidoService.remorverItem(id, idItem);
        return ResponseEntity.ok().build();
    }
}
