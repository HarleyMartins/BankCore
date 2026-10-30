package model;

import model.Transacao;
import java.util.ArrayList;
import java.util.List;

public class Conta{
    //tributos
    private double saldo;
    private String titular;
    private List<Transacao> transacoes = new ArrayList<>();

    public void registerAction(Transacao transacao){
        transacoes.add(transacao);
    }


    public void showStatement(){
        System.out.println("=== Extrato de " + titular + " ===");

        for (Transacao transacao: transacoes){
            transacao.showInfo();
        }
    }

    //construtor
    public Conta(String titular, double saldo){
        if (titular.isBlank() || titular == null){
            throw new IllegalArgumentException("Nome do titular inválido");
        }

        if (saldo < 0 || !Double.isFinite(saldo)){
            throw new IllegalArgumentException("Saldo inicial inválido");
        }

        this.titular = titular;
        this.saldo = saldo;


    }
    //fim construtor


    //showInfo
    public void showInfo(){ // não precisa passar os parâmetros, eles já existem dentro do objeto
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }


    //getTitular
    public String getTitular(){ // usado para acesar um tributo privado
        return this.titular;
    }


    //getSaldo
    public double getSaldo(){
        return this.saldo;
    }

    //setTitular
    public void setTitular(String titular){
        this.titular = titular;
    }


    //depositMoney
    public void depositMoney(double value){
        if (value <= 0 || !Double.isFinite(value)){
           throw new IllegalArgumentException("O valor do depósito deve ser maior do que zero");
        }

        System.out.println("Valor a ser depositado: " + value);
        saldo += value;
        System.out.println("Saldo total: " + saldo);

        registerAction(new Transacao(
                "Depósito",
                value,
                "Depósito realizado na conta"
        ));
    }

    // withdrawMoney
    public void withdrawMoney(double value){
        if (value <= 0 || !Double.isFinite(value)){
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }

        if (saldo < value){
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }


        saldo -= value;
        System.out.println("Foi retirado: " + value + ", saldo restante: " + saldo);

        registerAction(new Transacao(
                "Saque",
                value,
                "Saque realizado"
        ));
    }

    public void toTransfer(Conta destino, double value){
        if (destino == null){
            throw new IllegalArgumentException("A conta de destino não pode ser nula");
        }

        if (destino == this) { // this  é o objeto atual
            throw new IllegalArgumentException("Não é possivel transferir para si mesmo");
        }


        if (value <= 0 || !Double.isFinite(value)){
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }

        if (this.saldo < value){
            throw new IllegalArgumentException("Saldo insuficiente para transferência");
        }

        this.saldo -= value;
        destino.saldo += value;

        registerAction(new Transacao(
                "Transferência",
                value,
                "Transferência realizada"
        ));

        destino.registerAction(new Transacao(
                "Transferência",
                value,
                "Transferência recebida"
        ));

    }

}
