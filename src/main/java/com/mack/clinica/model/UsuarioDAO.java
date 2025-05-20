package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    //Método para buscar os dados do paciente pelo ID
    public static Usuario buscarPacientePorId(int id, String realPathBase) {
        //Consulta SQL para buscar os dados do paciente
        String sql = "SELECT id, nome, email, celular, cpf FROM usuarios WHERE id = ? AND tipo = 'paciente'";
        Usuario paciente = null;

        try (Connection conn = DatabaseConnection.getConnection(realPathBase); // Conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); //Define o ID do paciente na consulta
            try (ResultSet rs = stmt.executeQuery()) { //Executa a consulta
                if (rs.next()) { //Verifica se há resultados
                    //Preenche o objeto "Usuario" com os dados do paciente
                    paciente = new Usuario();
                    paciente.setId(rs.getInt("id"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setEmail(rs.getString("email"));
                    paciente.setCelular(rs.getString("celular"));
                    paciente.setCpf(rs.getString("cpf"));
                }
            }
        } catch (SQLException e) { //Trata erros de SQL
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar os dados do paciente.", e);
        }

        return paciente; //Retorna o objeto "Usuario" preenchido ou null
    }
}