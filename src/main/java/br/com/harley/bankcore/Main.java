package br.com.harley.bankcore;

import br.com.harley.bankcore.exception.RegraNegocioException;
import br.com.harley.bankcore.model.Conta;
import br.com.harley.bankcore.service.BancoService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BancoService banco = new BancoService();

    public static void main(String[] args) {
        boolean executando = true;
        System.out.println("Bem-vindo ao BankCore!");

        while (executando) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            try {
                switch (opcao) {
                    case "1" -> cadastrarConta();
                    case "2" -> listarContas();
                    case "3" -> consultarConta();
                    case "4" -> alterarTitular();
                    case "5" -> depositar();
                    case "6" -> sacar();
                    case "7" -> transferir();
                    case "8" -> consultarExtrato();
                    case "9" -> removerConta();
                    case "0" -> executando = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (RegraNegocioException | NumberFormatException e) {
                System.out.println("Não foi possível concluir: " + e.getMessage());
            }
            System.out.println();
        }

        System.out.println("Obrigado por utilizar o BankCore!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("""
                ============ MENU BANKCORE ============
                1 - Cadastrar conta
                2 - Listar contas
                3 - Consultar conta
                4 - Alterar titular
                5 - Depositar
                6 - Sacar
                7 - Transferir
                8 - Consultar extrato
                9 - Remover conta
                0 - Sair
                =======================================
                """);
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarConta() {
        System.out.print("Nome do titular: ");
        String titular = scanner.nextLine();
        banco.cadastrarConta(new Conta(titular, lerValor("Saldo inicial: R$ ")));
        System.out.println("Conta cadastrada.");
    }

    private static void listarContas() {
        if (banco.listarContas().isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        banco.listarContas().forEach(Conta::exibirResumo);
    }

    private static void consultarConta() {
        banco.buscarContaObrigatoria(lerTitular()).exibirResumo();
    }

    private static void alterarTitular() {
        System.out.print("Titular atual: ");
        String atual = scanner.nextLine();
        System.out.print("Novo titular: ");
        banco.alterarTitular(atual, scanner.nextLine());
        System.out.println("Titular alterado.");
    }

    private static void depositar() {
        banco.depositar(lerTitular(), lerValor("Valor do depósito: R$ "));
        System.out.println("Depósito realizado.");
    }

    private static void sacar() {
        banco.sacar(lerTitular(), lerValor("Valor do saque: R$ "));
        System.out.println("Saque realizado.");
    }

    private static void transferir() {
        System.out.print("Titular da conta de origem: ");
        String origem = scanner.nextLine();
        System.out.print("Titular da conta de destino: ");
        String destino = scanner.nextLine();
        banco.transferir(origem, destino, lerValor("Valor da transferência: R$ "));
        System.out.println("Transferência realizada.");
    }

    private static void consultarExtrato() {
        banco.buscarContaObrigatoria(lerTitular()).exibirExtrato();
    }

    private static void removerConta() {
        banco.removerConta(lerTitular());
        System.out.println("Conta removida.");
    }

    private static String lerTitular() {
        System.out.print("Nome do titular: ");
        return scanner.nextLine();
    }

    private static BigDecimal lerValor(String mensagem) {
        System.out.print(mensagem);
        return new BigDecimal(scanner.nextLine().trim().replace(",", "."));
    }
}
