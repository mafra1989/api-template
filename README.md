# RENNER API - Orquestrador de Vendas

## Visão Geral
Esse projeto é uma API REST para montar a matriz tributária dos produtos, autorizar as vendas junto a SEFAZ, 
informar a nota fiscal para o canal (e para o cliente), como também, registrar todas as vendas em seu banco de dados.

## Tecnologias e Padrões Utilizados

* Clean Architecture
* Spring Boot 3.1.2
* Swagger UI (springdoc)
* Lombok
* MapStruct
* Apache Kafka
* Spring Cloud OpenFeign
* Circuit Breaker
* Caffeine Cache
* Mockito Framework
* Fixture Factory
* Docker e Docker Compose
* Banco de Dados H2

## Executando o Projeto

### Instalar Docker
https://docs.docker.com/get-docker/

### Após instalação do Docker, seguir os passos:
1. Clonar este repositório
2. Vá para o diretório raiz do projeto: cd ./api-template
3. Rodar o comando: docker-compose up

### Verificando imagens docker rodando
```
docker ps
```
Para visualizar a documentação Swagger, acesse: http://localhost:8080/swagger-ui.html

