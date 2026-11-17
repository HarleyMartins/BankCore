package br.com.harley.bankcore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private static final DateTimeFormatter FORMATO_DATA =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final TipoTransacao tipo;
    private final BigDecimal valor;
    private final String descricao;
    private final LocalDateTime dataHora;

    //static = pertence à classe, e não individualmente a cada objeto
    //final = não pode receber outra referência depois de inicializada

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo da transação não pode ser nulo");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição da transação é obrigatória");
        }
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public TipoTransacao getTipo() { return tipo; }
    public BigDecimal getValor() { return valor; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }

    public void exibir() {
        System.out.printf("%s | %-24s | R$ %s | %s%n",
                dataHora.format(FORMATO_DATA), tipo, valor.toPlainString(), descricao);
    }
}
