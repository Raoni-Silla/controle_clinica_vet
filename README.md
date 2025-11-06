# 🚀 Sistema de Controle de Clínica Veterinária (Java/JPA)

Este é um projeto de estudo focado em construir um sistema de gerenciamento para uma clínica veterinária. O projeto foi originalmente um exercício acadêmico em C++ e foi completamente reimaginado e portado para uma arquitetura Java moderna.

O objetivo principal é praticar e demonstrar conceitos de:
* Programação Orientada a Objetos (POO)
* Persistência de Dados com **JPA (Java Persistence API)** e **Hibernate**
* Modelagem de Relacionamentos (`@ManyToOne`, `@OneToMany`, etc.)
* Padrões de Design (como o **DAO Genérico e Atômico**)
* Gerenciamento de Banco de Dados (MariaDB)
* Boas práticas de Git (Conventional Commits)

---

## ✨ Funcionalidades Implementadas

O sistema é controlado via um menu de console interativo e atualmente suporta o gerenciamento (CRUD) completo de todas as entidades principais:

* 🧑‍⚕️ **Gerenciamento de Tutores:**
    * Cadastrar novo tutor (com validação de CPF)
    * Listar todos os tutores
    * Buscar tutor por ID
    * Excluir tutor (com validação de segurança do DAO)

* 🐶 **Gerenciamento de Animais:**
    * Cadastrar novo animal
    * Valida a existência da Raça e do Tutor antes do cadastro
    * Listar, Buscar e Excluir animais

* 🩺 **Gerenciamento de Veterinários:**
    * CRUD completo, com validação de dados (CPF, Data de Nascimento)
    * Valida a existência da Cidade antes do cadastro

* 📝 **Gerenciamento de Consultas:**
    * Cadastrar nova consulta
    * Valida a existência do Animal e do Veterinário
    * Valida regras de negócio (ex: data da consulta não pode ser no passado, valor não pode ser zero)
    * Listar, Buscar e Excluir consultas

* 🗺️ **Gerenciamento de Dados Mestres:**
    * CRUD completo para **Cidades**, **Estados (UFs)** e **Raças**.

---

## 💻 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Persistência:** JPA (Java Persistence API)
* **Provedor ORM:** Hibernate
* **Banco de Dados:** MariaDB (ou MySQL)
* **Gerenciamento de Dependências:** Maven
* **Utilitários:** Lombok
* **Controle de Versão:** Git & GitHub

---

## 🛠️ Como Executar (Setup Local)

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Raoni-Silla/controle_clinica_vet.git](https://github.com/Raoni-Silla/controle_clinica_vet.git)
    cd controle_clinica_vet
    ```

2.  **Configure o Banco de Dados:**
    * Abra seu MariaDB (ou MySQL) e crie um *schema* (banco de dados) novo.
        ```sql
        CREATE DATABASE clinica_vet;
        ```
    * Abra o arquivo: `src/main/resources/META-INF/persistence.xml`.
    * **IMPORTANTE:** Altere as duas linhas a seguir com seu usuário e senha do banco:
        ```xml
        <property name="javax.persistence.jdbc.user" value="SEU_USUARIO_AQUI" />
        <property name="javax.persistence.jdbc.password" value="SUA_SENHA_AQUI" />
        ```

3.  **Compile e Execute:**
    * Abra o projeto na sua IDE (ex: IntelliJ ou Eclipse).
    * O Maven irá baixar e configurar automaticamente o Hibernate e o driver do MariaDB.
    * Encontre a classe `controle.clinica.Main.java` e execute o método `main()`.

---
