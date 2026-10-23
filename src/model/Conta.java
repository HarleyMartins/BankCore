package model;

public class Conta{
    //tributos
    private double saldo;
    private String titular;
    //fim tributos

    //construtor
    public Conta(String titular, double saldo){
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

    //setSaldo
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //depositMoney
    public void depositMoney(double value){
        if (value <= 0){
            System.out.println("Valor inválido");
            return;
        }

        System.out.println("Valor a ser depositado: " + value);
        saldo += value;
        System.out.println("Saldo total: " + saldo);
    }

    // withdrawMoney
    public void withdrawMoney(double value){
        if (value <= 0){
            System.out.println("Valor inválido");
            return;
        }

        if (saldo < value){
            System.out.println("Saldo insuficiente para saque");
            return;
        }


        saldo -= value;
        System.out.println("Foi retirado: " + value + ", saldo restante: " + saldo);
    }

    public void toTransfer(Conta destino, double valor){
        if (valor <= 0){
            System.out.println("Valor inválido");
            return;        }

        if (this.saldo < valor){
            System.out.println("Saldo insuficiente");
            return;
        }

        this.saldo -= valor;
        destino.saldo += valor;

    }

}
