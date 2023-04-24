package com.marcillino.irlan.projeto.entities.enums;

public enum EstadoPedido{
    ABERTO(1),
    FINALIZADO(2);

    private int codigo;


    private EstadoPedido(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static EstadoPedido valueOf(int codigo){
        for(EstadoPedido estado: EstadoPedido.values()){
            if(estado.getCodigo() == codigo){
                return estado;
            }
        }
        throw new IllegalArgumentException("código de estado do pedido inválido!");
    }
}
