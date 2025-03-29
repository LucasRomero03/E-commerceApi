# E-Commerce API with Spring Boot

API completa para um sistema de e-commerce com autenticação OAuth2 e documentação Swagger.

## Tecnologias Utilizadas

- **Spring Boot** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - Construção de API REST
- **Spring Security + OAuth2** - Autenticação e autorização
- **Spring Validation** - Validação de dados de entrada
- **PostgreSQL** - Banco de dados principal
- **H2 Database** - Banco em memória para desenvolvimento/testes
- **SpringDoc OpenAPI** - Documentação automática da API
- **Spring Security Test** - Testes de segurança

## Estrutura do Projeto

```
controllers/
├── CategoryController.java
├── OrderController.java
├── ProductController.java
└── UserController.java
handlers/
```

## Principais Funcionalidades

- CRUD completo para:
  - Categorias
  - Produtos
  - Pedidos
  - Usuários
- Autenticação via OAuth2
- Autorização baseada em roles
- Validação de dados de entrada
- Documentação automática com Swagger UI
- Suporte a múltiplos bancos de dados (H2 para dev, PostgreSQL para produção)

## Como Executar o Projeto

### Pré-requisitos
- Java 17+
- Maven 3.6+
- PostgreSQL (opcional - pode usar H2 em memória)

### Configuração
1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd nome-do-projeto
   ```

2. Configure o banco de dados no `application.properties`:
   - Para H2 (padrão para desenvolvimento):
     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.username=sa
     spring.datasource.password=
     ```
   - Para PostgreSQL:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
     spring.datasource.username=postgres
     spring.datasource.password=senha
     ```

3. Build e execução:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Acessando a API

- API: `http://localhost:8080`
- Documentação Swagger: `http://localhost:8080/swagger-ui.html`
- Console H2 (se ativado): `http://localhost:8080/h2-console`

## Endpoints Principais

### Autenticação (OAuth2)
```
POST /oauth2/token
```

### Categorias
```
GET    /api/categories      - Listar todas categorias
POST   /api/categories      - Criar nova categoria
GET    /api/categories/{id} - Obter categoria por ID
PUT    /api/categories/{id} - Atualizar categoria
DELETE /api/categories/{id} - Remover categoria
```

### Produtos
```
GET    /api/products        - Listar todos produtos
POST   /api/products        - Criar novo produto
GET    /api/products/{id}   - Obter produto por ID
PUT    /api/products/{id}   - Atualizar produto
DELETE /api/products/{id}   - Remover produto
```

### Pedidos
```
GET    /api/orders          - Listar todos pedidos
POST   /api/orders          - Criar novo pedido
GET    /api/orders/{id}     - Obter pedido por ID
PUT    /api/orders/{id}     - Atualizar pedido
DELETE /api/orders/{id}     - Remover pedido
```

### Usuários
```
GET    /api/users           - Listar todos usuários
POST   /api/users           - Criar novo usuário
GET    /api/users/{id}      - Obter usuário por ID
PUT    /api/users/{id}      - Atualizar usuário
DELETE /api/users/{id}      - Remover usuário
```

## Configuração de Segurança

A API utiliza OAuth2 para autenticação. Configure os seguintes parâmetros no `application.properties`:

```properties
# Configurações OAuth2
spring.security.oauth2.authorizationserver.client.registration.client-id=client-id
spring.security.oauth2.authorizationserver.client.registration.client-secret=client-secret
spring.security.oauth2.authorizationserver.client.registration.scope=read,write
spring.security.oauth2.authorizationserver.client.registration.authorization-grant-type=authorization_code,refresh_token,client_credentials
```
