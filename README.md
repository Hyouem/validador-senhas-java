# Validador de Senhas em Java

Projeto 03/49 da trilha pessoal de portfólio.

## Tecnologias

- Java 25
- Maven
- JUnit 6

## Estado do desenvolvimento

Etapa 1: validação de comprimento mínimo implementada.

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