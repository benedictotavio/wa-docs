# Tecnologias utilizadas

  - **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security, JWT, Swagger, Mockito, MockServer, Docker, PostgreSQL, Maven, Git.

  - **Frontend:** React, Bootstrap, Axios, React Router.

# ARQUITETURA

A arquitetura modular é um padrão de design que organiza um sistema em módulos independentes e coesos, onde cada módulo é responsável por uma funcionalidade específica. Esse tipo de arquitetura permite que a aplicação seja facilmente escalável, testável e mantida, já que cada módulo pode ser desenvolvido, atualizado ou substituído de forma isolada. No contexto de uma aplicação de gestão de usuários, a arquitetura modular pode ser implementada de maneira a separar as responsabilidades de forma clara entre os diferentes componentes.

## Estrutura de pastas
A estrutura de pastas é uma forma de organizar o código-fonte de uma aplicação em diretórios lógicos. Essa organização ajuda a manter o código-fonte organizado, fácil de entender e manter.

### Módulos:
   - **user**: Contém os componentes relacionados ao usuário.
     - **auth**: Contém os componentes relacionados à autenticação e autorização do usuário.
     - user entity
     - user controller
     - user service
     - user repository
     - user dto
     - user mapper
     - user use case
   - **team**: Contém os componentes relacionados à equipe.
   - **project**: Contém os componentes relacionados aos projetos.
   - **folder**: Contém os componentes relacionados às pastas.
   - **request**: Contém os componentes relacionados às requisições.
   - **documentation**: Contém os componentes relacionados à documentação.
   - **mockserver**: Contém os componentes relacionados ao mock server.
  - **utils**: Contém os componentes relacionados às ferramentas.
  - **config**: Contém os componentes relacionados à configuração.


# MODELAGEM DO BANCO DE DADOS

![Diagrama de Entidade Relacionamento (DER)](/images/MODELAGEM_WA_DOCS.drawio.png)

## ENTIDADES
1. Usuário (User)
2. Equipe (Team)
3. Projetos (Project)
4. Pastas (Folder)
5. Requisições (Request)
6. Documentação (Documentation)
7. Mockserver (MockServer)


### Usuário (User)

Nesse campo definiremos o modelo de usuário que será utilizado na aplicação, ele poderá ser um usuário comum ou um administrador. O mesmo tera um modulo Auth para autenticação e autorização.


#### Atributos

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Long | Identificador único do usuário. |
| username | String | Nome de usuário único. |
| email | String | Endereço de e-mail do usuário. |
| password | String | Senha do usuário. |
| role | String | Função do usuário (ex: "user" ou "admin"). |
| createdAt | LocalDateTime | Data de criação do usuário. |
| updatedAt | LocalDateTime | Data de atualização do usuário. |
| teams | List<Team> | Lista de equipes às quais o usuário pertence. |


> Senha deve ser criptografada.

> O campo role deve ser um enum.

> campo ira fazer join com a tabela TEAM. (relacionamento 1:N)

#### Auth

Use o Spring Security para autenticação e autorização, permitindo que os usuários se autentiquem e obtenham tokens JWT para acessar recursos protegidos.


##### Endpoints

1. Crie um endpoint de registro que recebe o nome de usuário, a senha e o email do usuário e cria um novo usuário.

  - Exemplo de requisição:

     ```http
    POST api/auth/signIn HTTP/1.1
    Host: example.com

    {
      "username": "john_doe",
      "password": "password123", // Senha criptografada
      "role": "user",
      "email": "john.doe@example.com",
    }
    ```

2. Crie um endpoint de logout que invalide o token JWT do usuário.
  - Exemplo de requisição:

    ```http
    POST api/auth/logout HTTP/1.1
    Host: example.com
    Authorization: Bearer {token}
    ```

3. Crie um endpoint de login que recebe o nome de usuário e a senha do usuário e retorna um token JWT.
  - Exemplo de requisição:
    ```http
    POST api/auth/login HTTP/1.1
    Host: example.com

    {
      "username": "john_doe",
      "password": "password123"
    }
    ```


4. Crie um endpoint que altera o role do usuário.

  - Exemplo de requisição:
    ```http
    PUT api/auth/role HTTP/1.1
    Host: example.com
    Authorization: Bearer {token}

    {
      "role": "admin" // ou "user"
    }
    ```

5. Crie um endpoint que altera a senha do usuário.
  - Exemplo de requisição:
    ```http
    PUT api/auth/password HTTP/1.1
    Host: example.com
    Authorization: Bearer {token} ;// token de autenticação ADMIN

    {
      "password": "new_password"
    }
    ```


#### Criptografia de senha

- Utilize o Spring Security para criptografar a senha do usuário antes de armazenar no banco de dados.

- _Argon2_ é uma biblioteca de hash de senha segura e eficiente.

- **Biblioteca Argon2**: de.mkammerer

  ```java
  @Configuration
  public class SecurityConfig {

      @Bean
      public PasswordEncoder passwordEncoder() {
          // Cria o Argon2PasswordEncoder com os parâmetros padrão
          return new Argon2PasswordEncoder();
      }
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
      // Configurações personalizadas para maior segurança
      return new Argon2PasswordEncoder(16, 32, 1, 4096); // ajuste os parâmetros conforme necessário
  }
  ```

#### Endpoints

1. Crie um endpoint que retorna o usuário atual.

  - Exemplo de requisição:
    ```http
    GET api/auth/me HTTP/1.1
    Host: example.com
    Authorization: Bearer {token}
    ```

2. Crie um endpoint que altera o usuário atual.

  - Exemplo de requisição:
    ```http
    PUT api/auth/me HTTP/1.1
    Host: example.com
    Authorization: Bearer {token}

    {
      "username": "john_doe",
      "password": "password123",
      "email": "john.doe@example.com",
    }
    ```

3. Crie um endpoint que deleta o usuário atual.

  - Exemplo de requisição:
    ```http
    DELETE api/auth/{user_id} HTTP/1.1
    Host: example.com
    Authorization: Bearer {token} // token de autenticação ADMIN
    ```

### Equipe (Team)