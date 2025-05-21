package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    //Método para buscar os dados do paciente pelo ID
    public static Usuario buscarPacientePorId(int id, String realPathBase) {
        //Consulta o SQL para buscar os dados do paciente
        String sql = "SELECT id, nome, email, celular, cpf FROM usuarios WHERE id = ? AND tipo = 'paciente'";
        Usuario paciente = null; //Inicializa o objeto paciente como null

        try (Connection conn = DatabaseConnection.getConnection(realPathBase); //Obtém a conexão com o banco de dados
             PreparedStatement stmt = conn.prepareStatement(sql)) { //Prepara a consulta SQL
            
            stmt.setInt(1, id); //Define o ID na consulta

            //Executa a consulta e obtém o resultado
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    //Preenche o objeto paciente com os dados retornados
                    paciente = new Usuario(); 
                    paciente.setId(rs.getInt("id"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setEmail(rs.getString("email"));
                    paciente.setCelular(rs.getString("celular"));
                    paciente.setCpf(rs.getString("cpf"));
                }
            }
        } catch (SQLException e) { //Captura exceções SQL
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar os dados do paciente.", e);
        }
        //Retorna o objeto paciente com os dados encontrados ou null se não encontrado
        return paciente;
    }

    //Novo método para buscar um usuário pelo e-mail e senha
    public static Usuario buscarUsuario(String email, String senha, String realPathBase) {
        String sql = "SELECT id, nome, tipo FROM usuarios WHERE email = ? AND senha = ?";
        Usuario usuario = null;

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email); //Define o e-mail na consulta
            stmt.setString(2, senha); //Define a senha na consulta

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    //Preenche o objeto Usuario com os dados retornados
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTipo(rs.getString("tipo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o usuário.", e);
        }

        return usuario; //Retorna o usuário encontrado ou null
    }
}