# cupom

# 🎟️ API de Cupons — Desafio Técnico Jr

Esta é uma API REST desenvolvida em **Java 21 com Spring Boot**, criada para gerenciar cupons de desconto.  
O projeto segue as regras de negócio propostas no desafio técnico, utilizando **H2 Database (em memória)** e **Spring Data JPA**.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3+**
    - Spring Web
    - Spring Data JPA
    - H2 Database
    - Validation (Jakarta)
- **JUnit 5** + **Mockito** (para testes)
- **Maven**
- **OpenAPI / Swagger (opcional)**

---

## ⚙️ Configuração do Projeto

### 📦 Clonar o repositório
```bash
git clone https://github.com/seu-usuario/cupom-api.git
cd cupom-api
```
### 🗃️ Banco de Dados H2
Com a aplicação rodando você pode acessar o H2 pelo endereço
```bash
http://localhost:8080/h2-console
```
Configurações do H2
```bash
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:
```
### 📑 Endpoints Principais
Com a aplicação em pé você pode criar um cupom:
```bash
POST http://localhost:8080/api/cupons
```
Body Json:
```bash
{
  "code": "CS123",
  "description": "Copo",
  "discountValue": 7.50,
  "expirationDate": "2025-12-01T15:30:00Z",
  "published": true
}
```
### Regras de Negócio:

- code deve ter 6 caracteres alfanuméricos (não alfanuméricos são removidos).

- discountValue ≥ 0.5

- expirationDate não pode estar no passado.

- Cupom pode ser criado já publicado.

```bash
GET http://localhost:8080/api/cupons/1
```
```bash
DELETE http://localhost:8080/api/cupons/1
```