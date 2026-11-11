# Validador de Senhas em Java

Projeto 03/49 da trilha pessoal de portfólio.

## Tecnologias

- Java 25
- Maven
- JUnit 6

## Estado do desenvolvimento

Etapas 1 e 2: comprimento mínimo e presença de letras maiúsculas e minúsculas implementados.

A aplicação ainda não possui interface interativa nem
a política completa de validação.

## Regra implementada

A senha deve possuir pelo menos 12 caracteres.
Valores nulos e vazios são rejeitados.

Nesta etapa, comprimento é calculado pelo método
String.length(), que conta unidades UTF-16.

## Executar testes

```powershell
mvn clean verify
```

## Executar aplicação

```powershell
java -jar target/validador-senhas-java-1.0.0.jar
```

## Segurança

O projeto não registra nem armazena senhas.
As regras de composição são exercícios de validação,
não uma implementação completa de autenticação segura.
## Etapa 2 — Letras maiúsculas e minúsculas

O validador identifica pelo menos uma letra maiúscula
ou minúscula, inclusive caracteres acentuados.

Os métodos retornam false para null e strings vazias.
Cada regra pode ser testada independentemente.

A validação completa e o menu interativo serão
implementados nas próximas etapas.
## Etapa 3 — Números, símbolos e espaços

Foram adicionadas três verificações independentes:

- Pelo menos um dígito reconhecido por Character.isDigit().
- Pelo menos um caractere especial, excluindo letras,
  dígitos e caracteres de espaço em branco.
- Ausência de espaços em branco, inclusive tabulações
  e espaços não separáveis.

Valores null são rejeitados por todas as regras.

A política de composição é didática e não substitui
uma política de autenticação segura.

A validação consolidada e a interface interativa
serão implementadas nas próximas etapas.