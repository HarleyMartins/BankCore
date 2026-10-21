import model.Conta;

public class Main {
    static void main(String[] args) {
       Conta conta1 = new Conta("Harley Martins", 1000.00);

       conta1.depositMoney(200);

       String titular = conta1.getTitular();
       System.out.println(titular);

       double saldo = conta1.getSaldo();
       System.out.println(saldo);

       conta1.setTitular("Martins");
       System.out.println(conta1.getTitular());

    }
}
