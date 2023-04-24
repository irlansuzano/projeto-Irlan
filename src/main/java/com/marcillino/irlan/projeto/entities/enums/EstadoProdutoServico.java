package com.marcillino.irlan.projeto.entities.enums;

public enum EstadoProdutoServico {
    ATIVO(1),
    INATIVO(2);

    private int codigo;


    private EstadoProdutoServico(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static EstadoProdutoServico valueOf(int codigo){
        for(EstadoProdutoServico estado: EstadoProdutoServico.values()){
            if(estado.getCodigo() == codigo){
                return estado;
            }
        }
        throw new IllegalArgumentException("código de estado do produto/serviço inválido!");
    }
}
