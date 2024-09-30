E-Commerce Simulado - Arquitetura de Microsserviços</h1>

Este projeto simula um sistema de e-commerce utilizando uma arquitetura de microsserviços. O principal objetivo deste projeto é o estudo e a implementação de uma arquitetura distribuída, abordando conceitos como comunicação entre serviços, uso de tecnologias de mensageria como Kafka, gerenciamento de configurações centralizado com Spring Cloud Config, e uso de containers com Docker e Docker Compose para orquestração dos microsserviços.
## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Docker]()
- [Kafka]()
- [MongoDB]()
- [Spring Data MongoDB]()
- [Spring Cloud Config]()
- [Spring Cloud Config]()
- [Spring Cloud Discovery Server (Eureka)]()
- [Spring Cloud Discovery Client]()
- [Flyway]()



## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/ecom-microserv.git
```

- Construir o projeto:
```
./mvnw clean package
```


## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  CLIENT
```
    POST /api/v1/customer
```

```
    PUT /api/v1/customer
```

```
    GET /api/v1/customer
```

```
    GET /api/v1/customer/exists/{ID}
```

```
    GET /api/v1/customer/{ID}
```

```
    DELETE /api/v1/customer/{ID}
```

- PRODUCT

```
    POST /api/v1/product
```

```
    GET /api/v1/product/{ID}
```

```
    GET /api/v1/product
```

- ORDER

```
    POST /api/v1/order
```

- OBJETIVO  DO PROJETO

O foco deste projeto é o estudo da arquitetura de microsserviços, com ênfase nos seguintes pontos:
Implantação de um API Gateway para a gestão centralizada das requisições.
Comunicação entre os microsserviços via HTTP e Kafka.
Gerenciamento de configurações centralizado usando Spring Cloud Config.
Uso do Docker para containerização dos serviços.
Orquestração com Docker Compose para gerenciar os microsserviços de forma integrada.
Este projeto é uma excelente oportunidade para aprender e aplicar as melhores práticas em arquitetura de microsserviços, comunicação entre serviços, mensageria assíncrona e uso de containers.
