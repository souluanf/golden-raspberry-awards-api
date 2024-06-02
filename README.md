# Golden Raspberry Awards API

API RESTful para obter o produtor com maior intervalo entre dois prêmios consecutivos e o que obteve dois prêmios mais
rápido.

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=bugs)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=souluanf_golden-raspberry-awards-api&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=souluanf_golden-raspberry-awards-api)

## Sumário

- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Configuração](#configuração)
- [Execução](#execução)
    - [Executando os Testes de Integração](#executando-os-testes-de-integração)
    - [Executando a Aplicação com Maven](#executando-a-aplicação-com-maven)
    - [Executando a Aplicação com Docker Compose](#executando-a-aplicação-com-docker-compose)
- [Acesso ao Banco de Dados](#acesso-ao-banco-de-dados)
    - [Credenciais](#credenciais)
- [Contato](#contato)

## Funcionalidades

- Leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards a partir de um arquivo
  CSV.
- Inserção dos dados em uma base de dados ao iniciar a aplicação.
- Endpoint para obter o produtor com maior intervalo entre dois prêmios consecutivos e o que obteve dois prêmios mais
  rápido.

## Requisitos

- JDK 21
- Maven 3.6+

## Configuração

**Instalação do JDK e Maven:**

- [Instruções para instalação do JDK](https://docs.oracle.com/en/java/javase/21/install/overview-jdk-installation.html)
- [Instruções para instalação do Maven](https://maven.apache.org/install.html)

**Configuração do arquivo CSV:**

O arquivo CSV usado para a leitura dos dados pode ser configurado de várias maneiras no arquivo de
configuração `application.yml`:

```yaml
golden-raspberry-awards:
  csv:
    file: csv/movielist.csv                   # Caminho relativo ao diretório raiz do projeto
    #file: src/main/resources/csv/movielist.csv # Caminho relativo ao classpath do projeto
    #file: /Users/luan/workspace/golden-raspberry-awards-api/src/main/resources/csv/movielist.csv # Caminho absoluto
```

- **Classpath**: Usando um caminho relativo ao classpath do projeto (`src/main/resources/csv/movielist.csv`).
- **Caminho Relativo**: Usando um caminho relativo ao diretório raiz do projeto (`csv/movielist.csv`).
- **Caminho Absoluto**: Usando um caminho absoluto no sistema de
  arquivos (`/Users/luan/workspace/golden-raspberry-awards-api/src/main/resources/csv/movielist.csv`).

## Execução

### Executando os Testes de Integração

Para executar os testes de integração e validar o funcionamento da API e o processamento correto dos dados do CSV,
utilize o comando Maven abaixo:

```bash
mvn test
```

### Executando a Aplicação com Maven

Para executar o projeto usando Maven, execute o comandos abaixo:

```bash
mvn spring-boot:run
```

### Executando a Aplicação com Docker Compose

Para executar o projeto usando Docker Compose, execute o comando abaixo:

```bash
docker-compose up -d
```

### Acesso à Documentação da API

- **OpenApi UI (local):** [http://localhost:8080](http://localhost:8080)

## Acesso ao Banco de Dados

Para acessar o banco de dados H2 utilizado pelo projeto, utilize a seguinte URL:

- **Console H2 (local):** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Credenciais

|                            **URL**                             | **Username** | **Password** |
|:--------------------------------------------------------------:|:------------:|:------------:|
| `jdbc:h2:mem:movies_db;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL` |    `user`    |              |

## Exemplo de Requisição

```bash
curl -X 'GET' 'http://localhost:8080/api/v1/movies/producers-award-intervals' -H 'accept: application/json'
```

## Exemplo de Resposta

Status: 200

```json
{
  "min": [
    {
      "producer": "Joel Silver",
      "interval": 1,
      "previousWin": 1990,
      "followingWin": 1991
    }
  ],
  "max": [
    {
      "producer": "Matthew Vaughn",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```

## Contato

Para suporte ou feedback:

- **Nome:** Luan Fernandes
- **Email:**  [contact@luanfernandes.dev](mailto:contact@luanfernandes.dev)
- **Website:** [https://luanfernandes.dev](https://luanfernandes.dev)
- **LinkedIn:** [https://linkedin.com/in/souluanf](https://linkedin.com/in/souluanf)