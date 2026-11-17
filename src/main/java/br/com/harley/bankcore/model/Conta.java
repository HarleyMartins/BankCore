package br.com.harley.bankcore.model;

import br.com.harley.bankcore.exception.RegraNegocioException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta {
    private String titular;
    private BigDecimal saldo;
    private final List<Transacao> transacoes = new ArrayList<>();

    public Conta(String titular, BigDecimal saldoInicial) {
        validarTitular(titular);
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraNegocioException("Saldo inicial não pode ser nulo ou negativo");
        }
        this.titular = titular.trim();
        this.saldo = saldoInicial;
    }

    public String getTitular() { return titular; }
    public BigDecimal getSaldo() { return saldo; }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public void alterarTitular(String novoTitular) {
        validarTitular(novoTitular);
        titular = novoTitular.trim();
    }

    public void depositar(BigDecimal valor) {
        validarValorPositivo(valor, "depósito");
        saldo = saldo.add(valor);
        transacoes.add(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito em conta"));
    }

    public void sacar(BigDecimal valor) {
        validarValorPositivo(valor, "saque");
        if (saldo.compareTo(valor) < 0) {
            throw new RegraNegocioException("Saldo insuficiente para realizar o saque");
        }
        saldo = saldo.subtract(valor);
        transacoes.add(new Transacao(TipoTransacao.SAQUE, valor, "Saque em conta"));
    }

    public void transferirPara(Conta destino, BigDecimal valor) {
        if (destino == null) {
            throw new RegraNegocioException("Conta de destino não pode ser nula");
        }
        if (destino == this) {
            throw new RegraNegocioException("Não é possível transferir para a própria conta");
        }
        validarValorPositivo(valor, "transferência");
        if (saldo.compareTo(valor) < 0) {
            throw new RegraNegocioException("Saldo insuficiente para realizar a transferência");
        }

        saldo = saldo.subtract(valor);
        destino.saldo = destino.saldo.add(valor);
        transacoes.add(new Transacao(TipoTransacao.TRANSFERENCIA_ENVIADA,
                valor, "Transferência enviada para " + destino.titular));
        destino.transacoes.add(new Transacao(TipoTransacao.TRANSFERENCIA_RECEBIDA,
                valor, "Transferência recebida de " + titular));
    }

    public void exibirExtrato() {
        System.out.println("\n========== EXTRATO: " + titular.toUpperCase() + " ==========");
        System.out.println("Saldo atual: R$ " + saldo.toPlainString());
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            transacoes.forEach(Transacao::exibir);
        }
        System.out.println("================================================");
    }

    public void exibirResumo() {
        System.out.printf("Titular: %s | Saldo: R$ %s%n", titular, saldo.toPlainString());
    }

    private static void validarTitular(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new RegraNegocioException("Nome do titular é obrigatório");
        }
    }

    private static void validarValorPositivo(BigDecimal valor, String operacao) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraNegocioException("Valor do " + operacao + " deve ser maior que zero");
        }
    }
}
