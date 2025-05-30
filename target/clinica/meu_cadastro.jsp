<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Meu Cadastro</title>
    <link rel="stylesheet" href="/css/style.css"> <!-- Importa o CSS para estilização -->
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a> <!-- Link para o painel do paciente -->
            <a href="agendarConsulta">Agendar Consulta</a> <!-- Link para agendamento de consultas -->
            <a href="minhaAgenda">Minha Agenda</a> <!--Link para a agenda de consultas do paciente--> 
            <a href="meuCadastro">Meu Cadastro</a> <!-- Link para a página atual -->
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a> <!-- Link para logout -->
        </div>
    </div>

    <!-- Conteúdo principal -->
    <div class="content">
        <h1>Meu Cadastro</h1>
        <%
            //Obtém o objeto "paciente" enviado pelo Servlet
            Usuario paciente = (Usuario) request.getAttribute("paciente");
            if (paciente != null) { //Verifica se o objeto não é nulo
        %>
            <!-- Exibe os dados do paciente em uma tabela -->
            <table class="styled-table">
                <tr>
                    <th>Nome</th>
                    <td><%= paciente.getNome() %></td> <!-- Exibe o nome do paciente -->
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= paciente.getEmail() %></td> <!-- Exibe o email do paciente -->
                </tr>
                <tr>
                    <th>Celular</th>
                    <td><%= paciente.getCelular() %></td> <!-- Exibe o celular do paciente -->
                </tr>
                <tr>
                    <th>CPF</th>
                    <td><%= paciente.getCpf() %></td> <!-- Exibe o CPF do paciente -->
                </tr>
            </table>
        <%
            } else { //Caso o objeto "paciente" seja nulo
        %>
            <p>Erro ao carregar os dados do paciente.</p> <!-- Mensagem de erro -->
        <%
            }
        %>
    </div>

</body>
</html>