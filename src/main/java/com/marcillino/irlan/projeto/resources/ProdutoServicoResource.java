package com.marcillino.irlan.projeto.resources;

import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import com.marcillino.irlan.projeto.services.PedidoService;
import com.marcillino.irlan.projeto.services.ProdutoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe controladora dos endpoints de produto/serviço
 * @author Irlan
 */
@RestController
@RequestMapping(value = "/produto_servico")
public class ProdutoServicoResource {

    @Autowired
    private ProdutoServicoService produtoServicoService;

    /**
     * Endpoint que retorna a lista de todos os objetos produtos/serviços paginados
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable){
        return ResponseEntity.ok().body(produtoServicoService.findAll(pageable));
    }

    /**
     * Endpoint que retorna um objeto produtos/serviços bucando pelo ID
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoServico> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body( produtoServicoService.findById(id));
    }

    /**
     * Endpoint que cria um novo produto/serviço
     * @param produtoServico
     * @return
     */
    @PostMapping(value = "/criar")
    public ResponseEntity<ProdutoServico> criarprodutoServico(@RequestBody ProdutoServico produtoServico){
        return ResponseEntity.ok().body(produtoServicoService.criarProdutoServico(produtoServico));
    }

    /**
     * Endpoint que altera um produto/serviço
     * @param id
     * @param produtoServico
     * @return
     */
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<ProdutoServico> alterarprodutoServico(@PathVariable UUID id, @RequestBody ProdutoServico produtoServico){
        return ResponseEntity.ok().body(produtoServicoService.alterarProdutoServico(produtoServico));
    }

    /**
     * Endpoint que deleta um produto/serviço
     * @param id
     * @param produtoServico
     * @return
     */
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarprodutoServico(@PathVariable UUID id, @RequestBody ProdutoServico produtoServico){
        produtoServicoService.deletarProdutoServico(produtoServico);
        return ResponseEntity.ok().build();
    }
}
