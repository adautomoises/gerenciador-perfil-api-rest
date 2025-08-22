# Gerenciador de Perfis do GitHub

API REST em Kotlin e Spring Boot para gerenciar perfis de usuários do GitHub com autenticação JWT.

## Tecnologias

- Kotlin, Spring Boot 3
- Spring Security com JWT
- Spring Data JPA (Hibernate)
- PostgreSQL & Flyway
- Gradle
- JUnit 5 & MockK

## Como Executar

1.  **Pré-requisitos:** JDK 21+, Gradle 8+, PostgreSQL.
2.  **Clone o repositório:**
    ```bash
    git clone https://github.com/adautomoises/gerenciador-perfil-api-rest.git
    cd gerenciador-perfil-api-rest
    ```
3.  **Configure o Ambiente:**
    - Crie um banco de dados PostgreSQL chamado `gerenciador_perfil_db`.
    - No arquivo `src/main/resources/application.properties` coloque a sua senha do banco:
      ```properties
      spring.datasource.password=sua_senha_secreta_aqui
      ```

4.  **Execute:**
    Usando uma IDE de sua preferência e devidamente configurada, execute o projeto.
    A API estará rodando em `http://localhost:8080`.

## Autenticação (JWT)

O acesso às rotas é protegido. Siga os passos para se autenticar:

1.  **Faça login para obter o token:**
    `POST /auth/login`
    ```json
    {
        "login": "admin",
        "password": "admin"
    }
    ```
    **Resposta:**
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

2.  **Use o token para acessar rotas protegidas:**
    Em todas as outras requisições, adicione o header:
    `Authorization: Bearer <seu_token_jwt_aqui>`

## Principais Endpoints

- `POST /auth/login`: Autentica um usuário e retorna um token JWT.
- `GET /users/roles`: Lista todos os usuários e seus perfis (rota protegida).
- `POST /users`: Cadastra os 30 usuários do github (rota protegida).
- `POST /manage-profile`: Atribui um perfil a um usuário (rota protegida).
- `POST /roles`: Cria um novo perfil (rota protegida).
