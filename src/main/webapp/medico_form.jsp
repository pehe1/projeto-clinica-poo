<!-- filepath: src/main/webapp/medico_form.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Medico</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="medico?action=list">Listar Medicos</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>
<div class="content">
    <h2>
        <c:choose>
            <c:when test="${not empty medico.id}">Editar Médico</c:when>
            <c:otherwise>Novo Medico</c:otherwise>
        </c:choose>
    </h2>
    <form action="medico" method="post" class="form-container">
        <input type="hidden" name="id" value="${medico.id}"/>
        <label>Nome:</label>
        <input type="text" name="nome" value="${medico.nome}" required>
        <span style="color:red">${errors.nome}</span><br/>

        <label>Especialidade:</label>
        <input type="text" name="especialidade" value="${medico.especialidade}" required>
        <span style="color:red">${errors.especialidade}</span><br/>

        <button type="submit">Salvar</button>
        <a href="medico?action=list">Cancelar</a>
    </form>
</div>
</body>
</html>
