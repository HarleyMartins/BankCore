package model;

public class Transacao {
    private String tipo;
    private double valor;
    private String descricao;

    public Transacao(String tipo, double valor, String descricao){
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
