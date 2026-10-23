import model.Conta;

public class Main {
    static void main(String[] args) {
       Conta conta1 = new Conta("Harley Martins", 1000.00);
       Conta conta2 = new Conta("Seu João", 500);



       conta1.toTransfer(conta2, 1500);
       conta1.showInfo();

       conta2.showInfo();


    }
}
