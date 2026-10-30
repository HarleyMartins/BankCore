package service;
import model.Conta;
import java.util.ArrayList;
import java.util.List;



public class BancoService {

    private List<Conta> contas = new ArrayList<>();



    public void registerAccount(Conta conta){
        for (Conta a: contas){
            if (a.getTitular().equalsIgnoreCase(conta.getTitular())){
                System.out.println("Já existe uma conta cadastrada com esse titular");
                return;
            }
        }

        contas.add(conta);
        System.out.println("Conta cadastrada com sucesso");
    }

    public void removeAccount(String titular){
        Conta conta = findAccount(titular);

        if (conta != null){
            if (conta.getSaldo() == 0){
                contas.remove(conta);
                System.out.println("Conta de" + titular + " removida com sucesso");
            } else{
                System.out.println("O saldo dessa conta não está zerado, portanto ela não pode ser removida");
            }
        } else{
            System.out.println("Essa conta não existe");
        }


    }

    public void listAccounts(){
        for (Conta a : contas){
            a.showInfo();
            System.out.println("-----------------");
        }
    }

    public Conta findAccount(String titular){
        for (Conta a: contas){
            if(a.getTitular().equalsIgnoreCase(titular)){
                return a; // "a" já é uma conta
            }
        }

        return null;
    }

    public void showAccount(String titular){
        Conta account = findAccount(titular);

        if (account != null){
            account.showInfo();
        } else {
            System.out.println("Conta não encontrada");
        }

    }



}
