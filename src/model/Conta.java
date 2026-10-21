package model;



public class Conta{
    private double saldo;
    private String titular;


    //construtor
    public Conta(String titular, double saldo){
        this.titular = titular;
        this.saldo = saldo;
    }
    //fim construtor


    public void showInfo(){ // não precisa passar os parâmetros, eles já existem dentro do objeto
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }

    public String getTitular(){ // usado para acesar um tributo privado
        return this.titular;
    }

    public double getSaldo(){
        return this.saldo;
    }


    public void setTitular(String titular){
        this.titular = titular;
    }

    public void depositMoney(double value){
        if (value <= 0){
            System.out.println("Valor inválido");
            return;
        }

        System.out.println("Valor a ser depositado: " + value);
        saldo += value;
        System.out.println("Saldo total: " + saldo);
    }


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
}
