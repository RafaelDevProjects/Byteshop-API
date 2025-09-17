# Loja Online – API REST

## Descrição
API REST para uma loja online com operações CRUD para Clientes e Produtos, implementada com Spring Boot seguindo os princípios do Domain Driven Design.

## Requisitos
- **Java**: 17+
- **Framework**: Spring Boot 3.2.0
- **Banco de dados**: H2 (em memória)
- **Build tool**: Maven 3.6+
- **Documentação**: Swagger/OpenAPI 3.0

## Funcionalidades
- ✅ CRUD completo de Clientes
- ✅ CRUD completo de Produtos
- ✅ API RESTful (Níveis 1 e 2 de Richardson)
- ✅ Validações de entrada
- ✅ Tratamento de erros global
- ✅ Documentação interativa com Swagger UI
- ✅ Arquitetura em camadas (Controller → Service → Repository)
- ✅ DTOs para request/response
- ✅ Banco H2 com console para visualização de dados

## Como executar

### Pré-requisitos
- Java 17 ou superior instalado
- Maven 3.6 ou superior instalado
- Git para clonar o repositório

### Passo a passo
1. Clone o repositório:
```bash
git clone https://github.com/RafaelDevProjects/Byteshop-API.git
cd byteshop-api
```

2. Execute a aplicação:
```bash
mvn spring-boot:run
A aplicação estará disponível em: http://localhost:8080
```

## Acessando a documentação
### Swagger UI
A documentação interativa da API está disponível através do Swagger UI:

- URL: http://localhost:8080/swagger-ui.html

### Console do H2 Database
Para visualizar os dados no banco de dados:

- URL: http://localhost:8080/h2-console
- JDBC URL: ```jdbc:h2:mem:byteshop_db```
- Usuário: sa
- Senha: (deixe em branco)

## Endpoints da API
### Clientes
| Método | Endpoint        | Descrição                          |
|--------|-----------------|------------------------------------|
| GET    | /clientes       | Lista todos os clientes (com paginação) |
| GET    | /clientes/{id}  | Busca um cliente por ID            |
| POST   | /clientes       | Cria um novo cliente               |
| PUT    | /clientes/{id}  | Atualiza um cliente existente      |
| DELETE | /clientes/{id}  | Exclui um cliente                  |

### Produtos
| Método | Endpoint        | Descrição                           |
|--------|-----------------|-------------------------------------|
| GET    | /produtos       | Lista todos os produtos (com paginação) |
| GET    | /produtos/{id}  | Busca um produto por ID             |
| POST   | /produtos       | Cria um novo produto                |
| PUT    | /produtos/{id}  | Atualiza um produto existente       |
| DELETE | /produtos/{id}  | Exclui um produto                   |


## Exemplos de uso
### Criar um cliente
```bash
curl -X POST http://localhost:8080/clientes \
-H "Content-Type: application/json" \
-d '{
"nome": "João Silva",
"email": "joao@email.com",
"documento": "123.456.789-00"
}'
```
### Listar clientes

```bash
curl http://localhost:8080/clientes
```

### Buscar cliente por ID
```bash
curl http://localhost:8080/clientes/1
```

## Criar um produto
```bash
curl -X POST http://localhost:8080/produtos \
-H "Content-Type: application/json" \
-d '{
"nome": "Tablet",
"preco": 1200.00,
"categoria": "Eletrônicos",
"descricao": "Tablet 10 polegadas",
"ativo": true
}'
```
### Listar produtos
``` bash
curl http://localhost:8080/produtos
```

### Buscar produto por ID
``` bash
curl http://localhost:8080/produtos/1
```

## Dados de teste
A aplicação já vem com dados iniciais para teste:

### Clientes
- Ana Silva (ana@email.com)
- Carlos Souza (carlos@email.com)
- Maria Oliveira (maria@email.com)

### Produtos
- Notebook (R$ 3.500,00)
- Mouse (R$ 120,00)
- Teclado (R$ 200,00)
- Monitor (R$ 800,00)

### Estrutura do projeto


``` text
src/
├── main/
│   ├── java/
│   │   └── com/byteshop/
│   │       ├── controller/     # Controladores REST
│   │       ├── service/        # Lógica de negócio
│   │       ├── repository/     # Acesso a dados
│   │       ├── model/          # Entidades JPA
│   │       ├── dto/            # Objetos de transferência de dados
│   │       ├── mapper/         # Mapeamento entre entidades e DTOs
│   │       ├── exception/      # Tratamento de exceções
│   │       └── config/         # Configurações
│   └── resources/
│       ├── application.properties
│       └── import.sql          # Dados iniciais
``` 

## Tecnologias utilizadas
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web MVC
- H2 Database
- MapStruct
- Springdoc OpenAPI (Swagger UI)
- Validation API
- Lombok

## Decisões de projeto
1. Arquitetura em camadas: Seguimos o padrão Controller → Service → Repository para separação de concerns

2. DTOs: Utilizamos Data Transfer Objects para separar a camada de apresentação da camada de domínio

3. Validações: Implementamos validações tanto a nível de API quanto a nível de banco de dados

4. Tratamento de erros: Criamos um handler global de exceções para retornar respostas padronizadas

5. Documentação: Utilizamos Swagger para documentação automática da API

## Desenvolvedores
| Nome                                   | RM        |
|---------------------------------------|-----------|
| Rafael de Almeida Sigoli               | RM554019  |
| Rafael Jorge Del Padre                 | RM552765  |
| Giovanna Franco Gaudino Rodrigues      | RM553701  |
|Lucas Bertolassi Iori       | RM553183  |
