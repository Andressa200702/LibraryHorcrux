# Horcrux — API de Livraria

API REST para gerenciamento de uma livraria, desenvolvida com Spring Boot e Java. Permite cadastrar livros, clientes e registrar vendas, com controle automático de estoque.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web (MVC)
- Spring Data JPA (Hibernate)
- MySQL
- H2 Database (uso em testes/desenvolvimento)
- Lombok
- Bean Validation (Jakarta Validation)
- Maven

## Estrutura do projeto

```
src/main/java/com/example/Horcrux
├── controller/       # Endpoints REST
├── models/entity/    # Entidades JPA
├── repository/        # Interfaces JpaRepository
├── service/           # Regras de negócio
└── HorcruxApplication.java
```

## Como rodar

1. Configure a conexão com o banco no `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/horcrux
   spring.datasource.username=root
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Instale as dependências e compile:
   ```bash
   ./mvnw clean install -U
   ```

3. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. A API sobe em `http://localhost:8080`.

## Modelo de dados

- **Livro**: título, autor, ISBN, ano de publicação, preço, estoque
- **Cliente**: nome, e-mail (único), CPF (único, 11 dígitos), telefone
- **Venda**: relaciona um Livro e um Cliente, quantidade, valor total (calculado automaticamente) e data (preenchida automaticamente)

## Regras de negócio

- CPF e e-mail de cliente não podem se repetir no cadastro
- Título, autor, e-mail e CPF são campos obrigatórios
- Preço do livro deve ser maior que zero
- Toda venda desconta a quantidade vendida do estoque do livro
- Não é possível vender mais unidades do que há em estoque
- Valor total da venda e data são calculados automaticamente pelo sistema, sem precisar ser enviados na requisição

## Endpoints

### Livros
| Método | Rota                    | Descrição                          |
|--------|-------------------------|--------------------------------------|
| GET    | /livros                  | Lista todos os livros                |
| GET    | /livros/{id}             | Busca um livro por id                  |
| GET    | /livros/buscar?titulo=   | Busca livros por título (parcial)       |
| POST   | /livros                  | Cria um novo livro                       |
| PUT    | /livros/{id}             | Atualiza um livro existente               |
| DELETE | /livros/{id}             | Remove um livro                            |

### Clientes
| Método | Rota                | Descrição                     |
|--------|---------------------|----------------------------------|
| GET    | /clientes            | Lista todos os clientes         |
| GET    | /clientes/{id}       | Busca um cliente por id           |
| POST   | /clientes            | Cria um novo cliente               |
| PUT    | /clientes/{id}       | Atualiza um cliente existente        |
| DELETE | /clientes/{id}       | Remove um cliente                     |

### Vendas
| Método | Rota                          | Descrição                              |
|--------|-------------------------------|-------------------------------------------|
| GET    | /vendas                        | Lista todas as vendas                    |
| GET    | /vendas/{id}                   | Busca uma venda por id                     |
| GET    | /vendas/cliente/{clienteId}     | Lista vendas de um cliente específico        |
| POST   | /vendas                        | Registra uma nova venda                       |
| DELETE | /vendas/{id}                   | Remove uma venda                                |

## Exemplo de requisição — criar uma venda

```json
POST /vendas
{
  "livro": { "id": 1 },
  "cliente": { "id": 1 },
  "quantidade": 2
}
```

A resposta traz o `valorTotal` (preço do livro × quantidade) e a `dataVenda` já preenchidos automaticamente.

## Testando

O projeto foi testado manualmente com o Thunder Client (extensão do VS Code), cobrindo os fluxos de CRUD completo e as validações de negócio (CPF/e-mail duplicado, estoque insuficiente, campos obrigatórios).
