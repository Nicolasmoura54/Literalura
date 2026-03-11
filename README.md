# 📚 Literalura

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

Aplicação de console para buscar e catalogar livros usando a API [Gutendex](https://gutendex.com/). Desenvolvido como parte do **Challenge Back-End da Alura**.

---

## ✨ Funcionalidades

- 🔍 Buscar livro pelo título e salvar no banco
- 📋 Listar livros registrados
- 👤 Listar autores registrados
- 🗓️ Listar autores vivos em determinado ano
- 🌍 Listar livros por idioma

---

## 🚀 Como Executar

**Pré-requisitos:** Java 21, Maven e PostgreSQL instalados.

**1. Crie o banco de dados:**
```sql
CREATE DATABASE literalura;
```

**2. Configure o `application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

**3. Execute:**
```bash
./mvnw spring-boot:run
```

---

## 🖥️ Menu

```
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em determinado ano
5 - Listar livros por idioma
0 - Sair
```

**Exemplo — Buscar livro:**
```
Digite o nome do livro:
> Pride and Prejudice

Livro salvo com sucesso!
Livro{titulo='Pride and Prejudice', idioma='en', downloads=63053, autor=Jane Austen}
```

**Exemplo — Autores vivos em determinado ano:**
```
Digite o ano:
> 1800

Jane Austen (1775 - 1817)
```

---

## 🛠️ Tecnologias

Java 21 · Spring Boot 3.2.5 · Spring Data JPA · PostgreSQL · Maven
