import com.sun.security.jgss.GSSUtil;
import model.Conta;
import service.BancoService;

public class Main {
    static void main(String[] args) {
        try {
            Conta conta = new Conta("Harley", 1000.00);
            conta.depositMoney(Double.POSITIVE_INFINITY);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao depositar: " + e.getMessage());
        }





    }
}
