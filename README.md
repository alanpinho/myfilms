## Considerações iniciais

O myFilms é um projeto que tem por objetivo lembrar-me quais filmes ainda quero assistir. Essa API está sendo desenvolvida com:
* Spring Boot 2.5.4;
* Maven;
* MapStruct;
* H2;
* Postman.
 
## Endpoints
Por enquanto, o banco de dados é o H2 para facilitar os testes via Postman.
Até o momento tem-se um CRUD, no qual você pode fazer as requisições da seguinte forma:

### Obter todos os filmes cadastrados no Banco de Dados (GET)
* GET (http://localhost:8080/films) -> Lista todos os filmes no banco de dados

![image](https://user-images.githubusercontent.com/38019738/135019720-c90211fd-ac28-4c7d-a8bc-caf2b0c32ecb.png)

### Obter um filme pelo Id (GET)
* Requisição do tipo GET para o endpoint http://localhost:8080/films/{id} (sem as chaves), substituindo o valor id pelo número correspondente ao filme no banco.

![image](https://user-images.githubusercontent.com/38019738/135279860-aa312324-7511-40fe-9987-6189bfce3b0a.png)


### Cadastrar um novo filme (POST)
* Para cadastrar um novo filme no banco de dados, é necessário que uma requisição POST seja feita para http://localhost:8080/films/new. O tipo de dado que deve ser passado é um JSON e o campo name é obrigatório. Atualmente há apenas dois campos, nome (name) e ano de lançamento (releaseYear).

![image](https://user-images.githubusercontent.com/38019738/135281180-84acc595-d08e-4fcf-805d-da034fe38383.png)



### Atualizar os dados de um filme (PUT)
* Para atualizar um filme uma requisição PUT deve ser feita para o endpoint http://localhost:8080/films com os dados em JSON. Os campos id e name são obrigatórios. Isso porque para editar um filme você precisa saber qual é o ID desse filme no banco e passar um novo nome ou fazer uma correção para aquele filme;

![image](https://user-images.githubusercontent.com/38019738/135282180-732609c6-6f71-4118-8240-4ef1f9619438.png)


### Deletar um filme do banco de dados (DELETE)
Para remover um filme do banco uma requisição DELETE deve ser feita para o endponint http://localhost:8080/films/{id} (sem as chaves), substituindo o valor id pelo número correspondente ao filme no banco.

![image](https://user-images.githubusercontent.com/38019738/135282949-c136607b-0143-466d-8152-08701ea87d8a.png)


