# Élin Duxus Desafio

## Como rodar a implementação?

### Pré-requisitos
- Java 8
- Maven

Obs: eu segui parte da estrutura de projeto definida no pom.xml, por isso realizei essa implementação com Java 8.

### Rodando a aplicação

- Baixe o repositório
- Abra o projeto em sua IDE de preferência (Intellij, Eclipse, etc...)
- Execute a classe ``DuxusdesafioApplication.java`` ou rode via terminal:

```bash
  mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Telas
- Cadastro de integrantes: `http://localhost:8080/integrantes.html`
- Montagem de times: `http://localhost:8080/times.html`

## Console H2
Acesse `http://localhost:8080/h2-console` para visualizar o banco de dados.
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: (vazio)

Obs: Utilizei o banco de dados H2 para ter mais praticidade na inicialização.

## Endpoints

### Integrantes

| Método | Endpoint | Descrição |
|---|---|---|
| POST | /integrantes | Cadastra um integrante |
| GET | /integrantes | Lista todos os integrantes |

**Exemplo de cadastro:**
```json
POST /integrantes
{
    "nome": "Michael Jordan",
    "funcao": "ala"
}
```

### Times

| Método | Endpoint | Descrição |
|---|---|---|
| POST | /times | Cadastra um time |

**Exemplo de cadastro:**
```json
POST /times
{
    "nomeDoClube": "Chicago Bulls",
    "data": "1994-01-01",
    "idIntegrante": [1, 2, 3]
}
```

### Processamento de dados

| Método | Endpoint | Parâmetros | Descrição |
|---|---|---|---|
| GET | /times/por-data | data | Retorna o time de uma data específica |
| GET | /times/integrante-mais-usado | dataInicial, dataFinal (opcionais) | Retorna o integrante mais usado no período |
| GET | /times/integrantes-recorrentes | dataInicial, dataFinal (opcionais) | Retorna os integrantes do time mais recorrente |
| GET | /times/funcao-recorrente | dataInicial, dataFinal (opcionais) | Retorna a função mais recorrente no período |
| GET | /times/clube-recorrente | dataInicial, dataFinal (opcionais) | Retorna o clube mais recorrente no período |
| GET | /times/contagem-clubes | dataInicial, dataFinal (opcionais) | Retorna a contagem de aparições por clube |
| GET | /times/contagem-funcao | dataInicial, dataFinal (opcionais) | Retorna a contagem de aparições por função |

**Exemplos de requisições:**
```
GET http://localhost:8080/times/por-data?data=1994-01-01

GET http://localhost:8080/times/integrante-mais-usado
GET http://localhost:8080/times/integrante-mais-usado?dataInicial=1993-01-01&dataFinal=1995-01-01

GET http://localhost:8080/times/contagem-funcao
GET http://localhost:8080/times/contagem-funcao?dataInicial=1993-01-01&dataFinal=1995-01-01
```