package com.marcillino.irlan.projeto.config;

import com.marcillino.irlan.projeto.entities.ItensPedido;
import com.marcillino.irlan.projeto.entities.Pedido;
import com.marcillino.irlan.projeto.entities.ProdutoServico;
import com.marcillino.irlan.projeto.entities.enums.EstadoPedido;
import com.marcillino.irlan.projeto.entities.enums.EstadoProdutoServico;
import com.marcillino.irlan.projeto.repositories.ItensPedidoRepository;
import com.marcillino.irlan.projeto.repositories.PedidoRepository;
import com.marcillino.irlan.projeto.repositories.ProdutoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Override
    public void run(String... args)throws Exception{
        ProdutoServico produto1 = new ProdutoServico(null, "Óleo diesel", true, BigDecimal.valueOf(29.90), 150.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico produto2 = new ProdutoServico(null, "Bico de injeção", true, BigDecimal.valueOf(45.90), 150.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico produto3 = new ProdutoServico(null, "Suspensão", true, BigDecimal.valueOf(245.90), 150.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico produto4 = new ProdutoServico(null, "Caixa de câmbio", true, BigDecimal.valueOf(500.00), 7.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico produto5 = new ProdutoServico(null, "Pneu aro 32", true, BigDecimal.valueOf(225.90), 35.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico produto6 = new ProdutoServico(null, "Pneu aro 18", true, BigDecimal.valueOf(125.90), 0.00, EstadoProdutoServico.INATIVO, null);

        ProdutoServico servico1 = new ProdutoServico(null, "Troca de óleo", false, BigDecimal.valueOf(45.90), 0.00, EstadoProdutoServico.ATIVO, null);
        ProdutoServico servico2 = new ProdutoServico(null, "Revisão", false, BigDecimal.valueOf(25.00), 0.00, EstadoProdutoServico.ATIVO, null);

        produtoServicoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,servico1,servico2));




        ItensPedido itensPedido = new ItensPedido(null,Arrays.asList(produto1,produto2,produto3),null);
        ItensPedido itensPedido1 = new ItensPedido(null,Arrays.asList(produto1,produto2,produto3),null);
        ItensPedido itensPedido2 = new ItensPedido(null,Arrays.asList(produto1,produto2,produto3),null);
        ItensPedido itensPedido3 = new ItensPedido(null,Arrays.asList(produto1,produto2,produto3),null);
        itensPedidoRepository.saveAll(Arrays.asList(itensPedido,itensPedido1,itensPedido2,itensPedido3));

        Pedido pedido = new Pedido(null, itensPedido,null, EstadoPedido.ABERTO);
        Pedido pedido1 = new Pedido(null,null,null, EstadoPedido.ABERTO);
        Pedido pedido2 = new Pedido(null,null,null, EstadoPedido.ABERTO);
        Pedido pedido3 = new Pedido(null,null,null, EstadoPedido.ABERTO);
        pedidoRepository.saveAll(Arrays.asList(pedido,pedido1,pedido2,pedido3));



    }
}
