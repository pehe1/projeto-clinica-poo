<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Paciente</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a> <!-- Link para o painel do paciente -->
            <a href="agendarConsulta">Agendamento de Consultas</a> <!-- Link para agendamento -->
            <a href="#">Minha Agenda</a> <!-- Link para a agenda do paciente -->                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
            <a href="meuCadastro">Meu Cadastro</a> <!-- Link para a nova funcionalidade -->                                                                                                         
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a> <!-- Link para logout -->
        </div>
    </div>

    <!-- Conteúdo principal -->
    <div class="content">
        <h1>Painel do Paciente</h1>
        <p>Bem-vindo ao seu painel, onde você poderá visualizar informações e agendar consultas.</p>
    </div>

</body>
</html>