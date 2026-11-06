package model;

import java.math.BigDecimal;

public class Transacao {
    private String tipo;
    private BigDecimal valor;
    private String descricao;

    public Transacao(String tipo, BigDecimal valor, String descricao){
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }


    public void showInfo(){
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: " + valor);
        System.out.println("Descrição: " + descricao);
    }

}
