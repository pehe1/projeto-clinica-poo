package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    // Método para buscar consultas por paciente
    public static List<Consulta> buscarConsultasPorPaciente(int pacienteId, String realPathBase) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.data_hora, c.status, u.nome AS medico_nome " +
                     "FROM consultas c " +
                     "JOIN usuarios u ON c.profissional_id = u.id " +
                     "WHERE c.paciente_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId); // Define o ID do paciente na consulta
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Cria um objeto Consulta e preenche com os dados do banco
                    Consulta consulta = new Consulta();
                    consulta.setDataHora(rs.getString("data_hora"));
                    consulta.setStatus(rs.getString("status"));
                    consulta.setMedicoNome(rs.getString("medico_nome"));
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar consultas do paciente.", e);
        }

        return consultas; // Retorna a lista de consultas
    }
}