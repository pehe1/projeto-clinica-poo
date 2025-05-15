package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mack.clinica.model.Usuario;

public class UsuarioDAO {

    /**
     * Consulta o usuário pelo email e senha no banco.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return Objeto Usuario encontrado ou null se não encontrado.
     */
    public static Usuario buscarUsuario(String email, String senha, String realPathBase) {
        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            String sql = "SELECT id, nome, tipo FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Se encontrou o usuário, cria um objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setTipo(rs.getString("tipo"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário no banco de dados.", e);
        }
        return null;
    }
}
