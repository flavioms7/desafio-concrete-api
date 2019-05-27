# Desafio Concrete Solutions
* Criação de uma aplicação que exponha uma API RESTful de criação de usuários, login e acesso ao perfil. A constrção deste desafio faz parte de uma seleção para a empresa Concrete Solutions.

### Endpoints

* Todos os endpoints devem aceitar e responder somente JSON, inclusive ao responder mensagens de erro.

```

 {"mensagem": "mensagem de erro"}
 
```

#### Cadastro
* Endpoint:
```

.../cadastro 

ou

https://desafio-concrete-api-rest.herokuapp.com/cadastro

```

* Formato da requisição:

```
{
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ]
}

```
#### Login
* Endpoint:
```
.../login 

ou

https://desafio-concrete-api-rest.herokuapp.com/login

```

* Formato da requisição:

```
{
   "email": "joao@silva.org",
   "password": "hunter2"
}

```

#### Perfil
* Endpoint:
```
.../perfil 

ou

https://desafio-concrete-api-rest.herokuapp.com/perfil

```

* Formato da requisição:

```
{
    "idUser": "055d0299-77bd-4636-becb-9610c6b2bb77", 
    "token": "0e04c0be-bbfe-4965-ac86-763ad0a62c29"
}

```
## Tecnologias Utilizadas
* H2 (Banco de dados em memória).
* Processo de build via Gradle.
* Java 8.
* Framework Spring Boot.
* Persistência com Spring Data e Hibernate.
* Cloud host Heroku.
* Servidor Tomcat Embedded.
* BCrypt (Método de criptografia hash).

## Autor
* [Flavio Miranda](https://github.com/flavioms7)




