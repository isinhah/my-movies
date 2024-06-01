# API MyMovies

![NPM](https://img.shields.io/npm/l/react)

API REST desenvolvida usando **Java 17, Spring Boot, MySQL, Maven, Flyway Migrations, Lombok, Springdoc e Testes com JUnit e Mockito.**

Esta API é projetada para gerenciar um banco de dados de filmes, oferecendo funcionalidades para criar, atualizar, pesquisar e excluir filmes. Além disso, permite pesquisar de forma filtrada os filmes por meio de dados como título, nota e análise.

## Instalação

Pré-requisitos: Java 17, Maven e MySQL

1. Clone o repositório:

```bash
git clone https://github.com/isinhah/myMovies.git
```

2. Instale as dependências com Maven

3. Instale o [MySQL](https://www.mysql.com/)

## Execução

1. Inicie a aplicação com Maven no Terminal

```bash
mvn spring-boot:run
```

2. A API será acessível em http://localhost:8080
3. Documentação do Springdoc em http://localhost:8080/swagger-ui/index.html

## Endpoints

A API possui os seguintes Endpoints:

```markdown
GET /movies - Retorna uma lista paginada de todos os filmes.

PUT /movies - Atualiza um filme existente.

POST /movies - Cria um novo filme.

GET /movies/{id} - Retorna um filme pelo ID.

DELETE /movies/{id} - Exclui um filme pelo ID.

GET /movies/find/by-title - Retorna filmes pelo título.

GET /movies/find/by-review - Retorna filmes pela análise.

GET /movies/find/by-rating - Retorna filmes pela classificação.

GET /movies/all - Retorna todos os filmes.
```

## Funcionalidades

### Criação de filme

![](/assets/create_movie.png)

### Atualizar filme existente

![](/assets/update_movie.png)

### Retornar filmes pela análise

![](/assets/movies_review.png)

### Retorno de uma lista paginada de todos os filmes

![](/assets/paginated_movies.png)

## Autor

Isabel Henrique

https://www.linkedin.com/in/isabel-henrique/
