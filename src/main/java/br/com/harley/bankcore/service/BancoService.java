package br.com.harley.bankcore.service;

import br.com.harley.bankcore.exception.RegraNegocioException;
import br.com.harley.bankcore.model.Conta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoService {
    private final List<Conta> contas = new ArrayList<>();

    public void cadastrarConta(Conta conta) {
        if (conta == null) {
            throw new RegraNegocioException("Conta não pode ser nula");
        }
        if (buscarConta(conta.getTitular()) != null) {
            throw new RegraNegocioException("Já existe uma conta para esse titular");
        }
        contas.add(conta);
    }

    public List<Conta> listarContas() {
        return Collections.unmodifiableList(contas);
    }

    public Conta buscarConta(String titular) {
        if (titular == null || titular.isBlank()) return null;
        return contas.stream()
                .filter(conta -> conta.getTitular().equalsIgnoreCase(titular.trim()))
                .findFirst()
                .orElse(null);
    }

    public Conta buscarContaObrigatoria(String titular) {
        Conta conta = buscarConta(titular);
        if (conta == null) throw new RegraNegocioException("Conta não encontrada");
        return conta;
    }

    public void alterarTitular(String titularAtual, String novoTitular) {
        Conta conta = buscarContaObrigatoria(titularAtual);
        Conta existente = buscarConta(novoTitular);
        if (existente != null && existente != conta) {
            throw new RegraNegocioException("Já existe uma conta com esse titular");
        }
        conta.alterarTitular(novoTitular);
    }

    public void depositar(String titular, BigDecimal valor) {
        buscarContaObrigatoria(titular).depositar(valor);
    }

    public void sacar(String titular, BigDecimal valor) {
        buscarContaObrigatoria(titular).sacar(valor);
    }

    public void transferir(String origem, String destino, BigDecimal valor) {
        buscarContaObrigatoria(origem).transferirPara(
                buscarContaObrigatoria(destino), valor);
    }

    public void removerConta(String titular) {
        Conta conta = buscarContaObrigatoria(titular);
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
            throw new RegraNegocioException("Só é possível remover conta com saldo zero");
        }
        contas.remove(conta);
    }
}
