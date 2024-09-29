# Journal - API de Registro de Ideias

Este projeto é uma API CRUD desenvolvida em **Java** com **Spring Boot**, utilizando **PostgreSQL** como banco de dados, e configurada para execução em um ambiente **AWS Lambda**. A API permite o gerenciamento de projetos e suas respectivas ideias, incluindo o rastreamento de alterações históricas em cada ideia.

## Funcionalidades

- **CRUD de Projetos**: Criar, listar, atualizar e excluir projetos.
- **CRUD de Ideias**: Criar, listar, atualizar e excluir ideias vinculadas a projetos.
- **Ideias Filhas**: Uma ideia pode ter outras ideias filhas.
- **Histórico de Alterações**: O sistema mantém o histórico de alterações de cada ideia, permitindo visualizar o conteúdo anterior e as mudanças feitas ao longo do tempo.

## Tecnologias Utilizadas

- **Java 11+**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger** (para documentação da API)
- **Docker** (opcional, para desenvolvimento local com o banco de dados)
- **AWS Lambda** com **Spring Cloud Function**
- **Liquibase** (para controle de versionamento do banco de dados)

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **Java 11+**
- **Maven**
- **PostgreSQL**
- **AWS CLI** (para deploy em AWS Lambda)
- **Docker** (opcional, para desenvolvimento local com o banco de dados)

## Configuração do Projeto

### 1. Clonar o Repositório

Clone este repositório em sua máquina local:

```bash
git clone https://github.com/seu-usuario/journal.git
cd journal
```

## 2. Configurar o Banco de Dados

Configure o PostgreSQL com as credenciais corretas e crie o banco de dados `journal_db`. No arquivo `src/main/resources/application.yml`, atualize as credenciais do banco:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/journal_db
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## 3. Rodar as Migrações do Banco de Dados
O projeto utiliza Liquibase para controle de versão do banco de dados. Execute as migrações para criar as tabelas necessárias:

```bash
mvn liquibase:update
```

## 4. Executar o Projeto Localmente
   Para executar o projeto localmente:

```bash
mvn spring-boot:run
```
A API estará disponível em: [http://localhost:8080](http://localhost:8080).

## 5. Endpoints Disponíveis

Swagger: [http://localhost:8080/docs](http://localhost:8080/docs)

### Projetos

- `POST /api/projects`: Cria um novo projeto.
- `GET /api/projects`: Lista todos os projetos.
- `GET /api/projects/{id}`: Busca um projeto por ID.
- `DELETE /api/projects/{id}`: Exclui um projeto.

### Ideias

- `POST /api/ideas`: Cria uma nova ideia.
- `GET /api/ideas/project/{projectId}`: Lista todas as ideias de um projeto.
- `PUT /api/ideas/{id}`: Atualiza o conteúdo de uma ideia.
- `DELETE /api/ideas/{id}`: Exclui uma ideia.

### Histórico de Ideias

- O histórico de alterações de uma ideia é automaticamente registrado sempre que uma ideia é atualizada.

## 6. Deploy na AWS Lambda

### Empacotar o Projeto

Primeiro, empacote o projeto como um arquivo jar:

```bash
mvn clean package
```

### Criar a Função Lambda

Utilize o **AWS CLI** ou o console da AWS para criar uma função Lambda, configurando o ambiente Java e fazendo o upload do arquivo `.jar` gerado.

### Configurar o AWS API Gateway

Crie uma API no **API Gateway** e conecte-a à função Lambda para expor os endpoints de sua API.
