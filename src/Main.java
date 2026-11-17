import com.sun.security.jgss.GSSUtil;
import model.Conta;
import service.BancoService;

import java.math.BigDecimal;

public class Main {
    static void main(String[] args) {
        try {
            Conta conta = new Conta("Harley", new BigDecimal("1000.00"));
            conta.depositMoney(new BigDecimal("1000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao depositar: " + e.getMessage());
        }





    }
}
