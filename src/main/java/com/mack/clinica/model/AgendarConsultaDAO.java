package com.mack.clinica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mack.clinica.util.DatabaseConnection;

public class AgendarConsultaDAO {

    private String realPathBase;

    public AgendarConsultaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public boolean agendarConsulta(int pacienteId, int profissionalId, String dataHora) {
        String sql = "INSERT INTO consultas (paciente_id, profissional_id, data_hora, status, observacoes) VALUES (?, ?, ?, 'agendada', '')";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pacienteId);
            stmt.setInt(2, profissionalId);
            stmt.setString(3, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("entrou aqui");
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listarMedicos() {
        List<Usuario> medicos = new ArrayList<>();
        String sql = "SELECT id, nome FROM usuarios WHERE tipo = 'medico'";
    
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                medicos.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médicos: " + e.getMessage());
        }
    
        return medicos;
    }
    
}
