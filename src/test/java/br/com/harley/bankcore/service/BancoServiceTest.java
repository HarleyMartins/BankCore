package br.com.harley.bankcore.service;

import br.com.harley.bankcore.exception.RegraNegocioException;
import br.com.harley.bankcore.model.Conta;
import br.com.harley.bankcore.model.TipoTransacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BancoServiceTest {
    private BancoService banco;
    private Conta ana;
    private Conta bruno;

    @BeforeEach
    void configurar() {
        banco = new BancoService();
        ana = new Conta("Ana", new BigDecimal("1000.00"));
        bruno = new Conta("Bruno", new BigDecimal("500.00"));
        banco.cadastrarConta(ana);
        banco.cadastrarConta(bruno);
    }

    @Test
    void deveBuscarContaSemDiferenciarMaiusculas() {
        assertSame(ana, banco.buscarConta("aNa"));
    }

    @Test
    void naoDeveCadastrarTitularDuplicado() {
        assertThrows(RegraNegocioException.class,
                () -> banco.cadastrarConta(new Conta("ANA", BigDecimal.ZERO)));
    }

    @Test
    void deveDepositarComPrecisaoDecimal() {
        banco.depositar("Ana", new BigDecimal("0.20"));
        assertEquals(0, ana.getSaldo().compareTo(new BigDecimal("1000.20")));
    }

    @Test
    void deveSacar() {
        banco.sacar("Ana", new BigDecimal("250.25"));
        assertEquals(0, ana.getSaldo().compareTo(new BigDecimal("749.75")));
    }

    @Test
    void naoDevePermitirSaqueSemSaldo() {
        assertThrows(RegraNegocioException.class,
                () -> banco.sacar("Ana", new BigDecimal("1000.01")));
        assertEquals(0, ana.getSaldo().compareTo(new BigDecimal("1000.00")));
    }

    @Test
    void deveTransferirERegistrarNosDoisExtratos() {
        banco.transferir("Ana", "Bruno", new BigDecimal("125.50"));
        assertEquals(0, ana.getSaldo().compareTo(new BigDecimal("874.50")));
        assertEquals(0, bruno.getSaldo().compareTo(new BigDecimal("625.50")));
        assertEquals(TipoTransacao.TRANSFERENCIA_ENVIADA, ana.getTransacoes().getFirst().getTipo());
        assertEquals(TipoTransacao.TRANSFERENCIA_RECEBIDA, bruno.getTransacoes().getFirst().getTipo());
    }

    @Test
    void naoDeveTransferirParaPropriaConta() {
        assertThrows(RegraNegocioException.class,
                () -> banco.transferir("Ana", "Ana", new BigDecimal("10.00")));
    }

    @Test
    void deveRejeitarValoresNulosZeroOuNegativos() {
        assertThrows(RegraNegocioException.class, () -> banco.depositar("Ana", null));
        assertThrows(RegraNegocioException.class,
                () -> banco.depositar("Ana", BigDecimal.ZERO));
        assertThrows(RegraNegocioException.class,
                () -> banco.sacar("Ana", new BigDecimal("-1.00")));
    }

    @Test
    void deveAlterarTitular() {
        banco.alterarTitular("Ana", "Ana Martins");
        assertNull(banco.buscarConta("Ana"));
        assertSame(ana, banco.buscarConta("Ana Martins"));
    }

    @Test
    void soDeveRemoverContaComSaldoZero() {
        assertThrows(RegraNegocioException.class, () -> banco.removerConta("Ana"));
        banco.sacar("Bruno", new BigDecimal("500.00"));
        banco.removerConta("Bruno");
        assertNull(banco.buscarConta("Bruno"));
    }

    @Test
    void deveRejeitarTitularInvalidoESaldoInicialNegativo() {
        assertThrows(RegraNegocioException.class, () -> new Conta("   ", BigDecimal.ZERO));
        assertThrows(RegraNegocioException.class,
                () -> new Conta("Carlos", new BigDecimal("-0.01")));
    }
}
