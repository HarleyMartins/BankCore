# BankCore — versão final de estudo

Aplicação bancária de console em **Java puro**, com Maven, `BigDecimal` e testes automatizados JUnit 5. Não utiliza Spring, banco de dados ou interface gráfica.

## Requisitos
- JDK 25
- IntelliJ IDEA com suporte a Maven

## Como abrir
1. Extraia o ZIP.
2. No IntelliJ, use **File → Open** e selecione `BankCore-Final`.
3. Aguarde a importação do Maven e o download do JUnit.
4. Execute `src/main/java/br/com/harley/bankcore/Main.java`.

## Como executar os testes
No IntelliJ, clique com o botão direito em `src/test/java` e escolha **Run Tests**. Também é possível executar `mvn test` no terminal, dentro da pasta do projeto.

## Funcionalidades
- Cadastro, consulta, listagem, alteração de titular e remoção de contas
- Depósitos, saques e transferências
- Extrato com data, tipo, valor e descrição
- Validação de titulares, valores e saldo
- Exceção própria para regras de negócio
- Testes automatizados dos principais comportamentos

## Estrutura
- `Main`: interface de console e leitura de entradas.
- `model`: entidades `Conta`, `Transacao` e enum `TipoTransacao`.
- `service`: operações de gerenciamento das contas.
- `exception`: exceção de regra de negócio.
- `test`: testes automatizados com JUnit 5.

## Decisões técnicas
- Dinheiro é representado por `BigDecimal`. Crie valores com `new BigDecimal("125.50")`, nunca a partir de `double`.
- `BigDecimal` é imutável: `add` e `subtract` retornam novos objetos.
- `compareTo` compara valores numericamente.
- As contas ficam em memória; os dados são perdidos ao encerrar o programa.

## Escopo
Projeto didático de console, não um sistema bancário real. Não possui autenticação, persistência, concorrência transacional ou integração financeira.
