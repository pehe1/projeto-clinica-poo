<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Médico</title></head>
<body>
<h2>Cadastro de Médico</h2>

<!-- Formulário que envia para o servlet MedicoServlet -->
<form action="medicos" method="post">
    <!-- Campo oculto para enviar o ID do médico (necessário na edição) -->
    <input type="hidden" name="id" value="${medico.id}" />
    Nome: <input type="text" name="nome" value="${medico.nome}" required /><br/>
    <input type="submit" value="Salvar"/>
</form>

<!-- Link para voltar à listagem -->
<a href="medicos">Voltar</a>
</body>
</html>
