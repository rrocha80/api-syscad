# api-syscad

API para CRUD de cadastro de pessoas utilizando as seguintes tecnologias:

- Spring Boot 2
- Maven
- Java 8
Obs.: Com "Autentication basic"


# Credenciais:

Usuário: rodrigo
Senha: user

# Levantando a aplicação

Para levantar a aplicação com Docker, é necessário executa o maven package estando dentro da pasta do projeto ou via IDE e em seguida o docker compose:

pré-requisito: 
Ter uma máquina com o docker instalado, ex.: Windows.

>> mvn package
>> docker-compose up -d

# Utilizando a aplicação

Para consumir a API é necessário usa o "Postman" ou chamar o endereço a seguir num browser:

>> http://localhost:9000/api-syscad/pessoa/source
>> http://localhost:9000/api-syscad/pessoa/listar_todos 

Obs.: Não criei variáveis de ambiente, caso use docker é necessário executar o comando da máquina onde subiu a API. 
