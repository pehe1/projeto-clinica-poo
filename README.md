# 🏥 Sistema de Agendamento e Gestão de Consultas Médicas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/Licença-Acadêmica-blue?style=for-the-badge)

Projeto final da disciplina de **Programação Orientada a Objetos com Java** — Universidade Presbiteriana Mackenzie  
Desenvolvido por alunos de Engenharia da Computação

---

## 📌 Descrição

Este sistema foi desenvolvido para atender clínicas e consultórios médicos, oferecendo uma plataforma web de agendamento e gestão de consultas. Pacientes podem visualizar seu histórico e dados cadastrais, enquanto profissionais da saúde e administradores têm acesso a funcionalidades específicas para controle de atendimentos, prontuários e gerenciamento de usuários.

O sistema aplica os fundamentos da **Programação Orientada a Objetos (POO)** utilizando **Java com Servlets e JSP**, com arquitetura **MVC** e persistência de dados em **SQLite**.

---

## 🎯 Objetivos do Projeto

- Implementar um sistema web funcional com base em **POO**
- Utilizar **Java + Servlets + JSP + HTML/CSS** com arquitetura MVC
- Controlar sessões e rotas protegidas via `HttpSession`
- Gerenciar dados via banco de dados **SQLite** com DAOs específicos

---

## 🧠 Tecnologias e Arquitetura

- **Java (Servlets + JSP)**
- **HTML/CSS**
- **Banco de Dados: SQLite**
- **MVC (Model-View-Controller)**
- **Apache Maven**
- **Jetty (servidor embutido)**

---
## 🗂️ Estrutura do Projeto (Maven)

```text
clinica/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mack/clinica/
│       │       ├── controller/  -> Servlets
│       │       ├── model/       -> Classes de domínio e DAO
│       │       └── util/        -> Conexão com o banco
│       └── webapp/
│           ├── *.jsp            -> Páginas JSP
│           ├── css/styles.css   -> Estilo da aplicação
│           └── WEB-INF/web.xml  -> Configuração do projeto
├── db.db                        -> Banco de dados SQLite
```
---

## 🔐 Funcionalidades Implementadas

### 👤 Paciente
- Visualizar dados cadastrais
- Visualizar consultas agendadas

### 👨‍⚕️ Administrador
- Cadastro, edição, exclusão e listagem de pacientes
- Cadastro, edição, exclusão e listagem de médicos
- Consulta da agenda (por paciente, médico ou data)
- Estrutura inicial para prontuário médico (ficha clínica)

---

## 🧪 Banco de Dados

**Tabelas principais:**
- `usuarios`: armazena dados de pacientes, médicos e administradores
- `consultas`: agenda médica vinculada a pacientes e profissionais
- `prontuarios`: estrutura de ficha clínica para cada atendimento

---

## ✅ Status

✔️ Agendamento e autenticação já implementados  
✔️ Dashboards distintos por tipo de usuário  
✔️ Prontuário estrutural criado (em fase inicial)
✔️ Visualização de dados cadastrais
✔️ Listagem de consultas agendadas
---

## 👥 Integrantes

- Danilo — [LinkedIn](https://www.linkedin.com/in/danilomoreiraalmeida/)
- Miqueias — [LinkedIn](https://www.linkedin.com/in/miqueiassaldanha/)
- Pedro — [LinkedIn](https://www.linkedin.com/in/pedrocavalcantebarrense/)
- Thiago - [LinkedIn](https://www.linkedin.com/in/thiago-scaff-809b23242/)
---

## 📘 Orientador

Prof. Nilton Canto — Universidade Presbiteriana Mackenzie

---
