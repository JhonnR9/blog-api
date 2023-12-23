

# Blog api
Api de blogs criada em java e Spring Framework!

A api ainda  esta em desnvolvimento! Nao me cobrem tanto :(

## Tecnologias usadas
- ### Java
  Bem é o java normal
  Link: [https://openjdk.org/projects/jdk/17/](https://openjdk.org/projects/jdk/17/)
- ### Spring-framework
  O Spring Framework fornece um modelo abrangente de programação e configuração para aplicativos empresariais modernos baseados em Java - em qualquer tipo de plataforma de implantação.
  Link: [https://spring.io/projects/spring-framework/](https://spring.io/projects/spring-framework/)
- ### Lombok
  O Projeto Lombok é uma biblioteca java que se conecta automaticamente ao seu editor e cria ferramentas, apimentando seu java. Nunca mais escreva outro método getter ou equals, com uma anotação sua classe tem um construtor completo, automatiza suas variáveis ​​de registro e muito mais.
  Link: [https://projectlombok.org/](https://projectlombok.org/)
- ### Gradle build tool
  O Gradle é um gerenciador de dependências muito usado em desenvolvimento Java.
  Link: [https://gradle.org/](https://gradle.org/)

## Como instalar

*   Clone este repositório usando:

    ```git bash
    $ git clone git@github.com:JhonnR9/blog-api.git
    ```


Certifique-se de que esteja com o Java instalado e com o PATH configurado corretamente.

É recomendado usar o IDE **IntelliJ IDEA** encontrado em [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/), ou qualquer IDE Java de sua preferência.

Inicie o arquivo BlogTutorialApiApplication.java encontrado em src



## Base url
http://localhost:8080/api-blogs
## Endpoints

- ###  GET : /blogs



Retorna uma lista com todos os blogs

Nao é necesario nenhum corpo para a requisiçao

exemplo de requisiçao bem sucedida

status 200 ok

````json
  [
    {
      "id": 0,
      "title": "This is a blog",
      "description": "This blog is amazin",
      "createAt": "2023-12-23T13:51:47.5174464",
      "updatedAt": "2023-12-23T13:51:47.5174464"
    },
    {
      "id": 1,
      "title": "This is a another blog",
      "description": "ok this work",
      "createAt": "2023-12-23T13:51:50.9805113",
      "updatedAt": "2023-12-23T13:51:50.9805113"
    }
  ]
````
- ### POST: /blogs

Criaçao de um novo blog

#### Corpo da requisiçao exemplo
````json
{
  "title": "This is a blog",
  "description": "This blog is amazing"
}
````

Exemplo de resposta bem sucedida

status 201 created
````json
  {
	"id": 0,
	"title": "This is a blog",
	"description": "This blog is amazing",
	"createAt": "2023-12-23T13:51:47.5174464",
	"updatedAt": "2023-12-23T13:51:47.5174464"
  }
````
- ### DELETE /blogs/:id

Remove um blog se existir é necessario passar o id

nao é necessario nenhum corpo para essa requisiçao

caso for bem sucedido vai retornar:

status 204 No Content

caso nao encontre sera:

status 422 Unprocessable Entity

- ### PATCH /blogs/:id
atualiza  um blog existente todos os paramtros sao opcionais

#### Corpo da requisiçao exemplo
````json
{
  "description": "This blog is amazing updated"
}
````

Exemplo de resposta bem sucedida

status 200 ok
````json
{
  "id": 3,
  "title": "This is a blog",
  "description": "This blog is amazin updated  ",
  "createAt": "2023-12-23T14:20:13.7471106",
  "updatedAt": "2023-12-23T14:20:30.4773233"
}
````

- ### GET /blogs/:id
#### Retorna um blog que tenha o id

Nao é necessario nenhum corpo pra essa requisçao

Exemplo de resposta bem sucedida

status 200 ok
````json
{
  "id": 1,
  "title": "This is a blog",
  "description": "This blog is amazin updated  ",
  "createAt": "2023-12-23T14:20:13.7471106",
  "updatedAt": "2023-12-23T14:20:30.4773233"
}
````