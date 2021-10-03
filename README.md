## Considerações iniciais

O myFilms é um projeto que tem por objetivo lembrar-me quais filmes ainda quero assistir. Essa API está sendo desenvolvida com:
* Spring Boot 2.5.4;
* Maven;
* MapStruct;
* Postgres;
* Docker;
* Swagger-UI;
* Postman.

## Executando o projeto
Para executar o projeto com sucesso é necessário possuir o Docker instalado na máquina e também uma IDE que execute Java com Spring (as mais usadas são Intellij e Eclipse).

É importante que o docker seja inicializado primeiro usando o comando `docker-compose up` via terminal dentro da pasta do projeto. Desta forma o banco de dados estará pronto para ser conectado ao projeto. Após inicializar o docker, você pode executar o projeto como uma Aplicação Spring Boot.

Você pode efetuar as requisições através do Postman ou do navegador. Ao executar a aplicação, estará disponível no navegador a interface do Swagger através da URL http://localhost:8080/swagger-ui.html para requisições, dispensando o uso do Postman.
 
## Endpoints
Até o momento tem-se um CRUD, no qual você pode fazer as requisições da seguinte forma:

### Obter todos os filmes cadastrados no Banco de Dados (GET)
* GET (http://localhost:8080/films) -> Lista todos os filmes no banco de dados.

![image](https://user-images.githubusercontent.com/38019738/135767484-6c76e5a4-ea81-4fbe-8db7-d9f476984530.png)

### Obter um filme pelo Id (GET)
* GET (http://localhost:8080/films/{id}) -> Retorna o filme correspondente ao ID passado na requisição (sem as chaves).

![image](https://user-images.githubusercontent.com/38019738/135767535-e29ddc9a-2c30-4370-a586-478db7d13f9e.png)


### Cadastrar um novo filme (POST)
* POST (http://localhost:8080/films/new) -> Adiciona um filme ao banco de dados. O formato suportado é JSON e o campo name (nome) é obrigatório. O modelo de requisição é apresentado logo abaixo.
````
{
    "name": "Matrix Revolutions",
    "releaseYear": 2003    
}
````
![image](https://user-images.githubusercontent.com/38019738/135767691-f6c73d2a-fee8-4beb-9fc5-f07d12502539.png)


### Atualizar os dados de um filme (PUT)
* PUT (http://localhost:8080/films) -> Atualiza os dados de um filme. O formato suportado é JSON e os campos id e name são obrigatórios. O modelo de requisição é apresentado logo abaixo.
````
{
    "id": 19,
    "name": "Inception",    
    "releaseYear": 2010
}
````

![image](https://user-images.githubusercontent.com/38019738/135767777-3a86863a-7a34-4600-95e5-3bed77b9a3c0.png)


### Deletar um filme do banco de dados (DELETE)
* DELETE (http://localhost:8080/films/{id}) -> Remove um filme do banco de dados. O ID do filme a ser removido deve ser passado na requisição (sem as chaves).

![image](https://user-images.githubusercontent.com/38019738/135767824-f825e47f-9f30-4acf-b931-3404c31a3513.png)
