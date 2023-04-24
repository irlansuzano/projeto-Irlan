package com.marcillino.irlan.projeto.resources;

import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe controladora dos endpoints de pedido
 * @author Irlan
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Endpoint que retorna a lista de todos os pedidos paginados
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable){
        return ResponseEntity.ok().body(pedidoService.findAll(pageable));
    }

    /**
     * Endpoint que retorna um pedido bucando pelo ID
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body( pedidoService.findById(id));
    }

    /**
     * Endpoint que cria um pedido
     * @param pedido
     * @return
     */
    @PostMapping(value = "/criar")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(pedidoService.criarPedido(pedido));
    }

    /**
     * Endpoint que altera um pedido
     * @param id
     * @param pedido
     * @return
     */
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<Pedido> alterarPedido(@PathVariable UUID id, @RequestBody Pedido pedido){
        return ResponseEntity.ok().body(pedidoService.alterarPedido(pedido));
    }

    /**
     * endpoint responsável por adicionar uma lista de itens a um pedido
     * @param id
     * @param idLista
     * @return
     */
    @PutMapping(value= "/{id}/adicionar_lista_itens/{idLista}")
    public ResponseEntity<Pedido> adicionarItens(@PathVariable UUID id, @PathVariable UUID idLista){
        return ResponseEntity.ok().body(pedidoService.adicionarLista(id, idLista));
    }

    /**
     * endpoint responsável por deletar um pedido
     * @param id
     * @param pedido
     * @return
     */
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarPedido(@PathVariable UUID id){
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok().build();
    }
}
