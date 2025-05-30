package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD de Médico.
 */
public class MedicoDAO {

    // Insere um novo médico
    public void create(Medico m, String realPathBase) throws Exception {
        String sql = "INSERT INTO medico(nome, especialidade) VALUES(?,?)";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getEspecialidade());
            ps.executeUpdate();
        }
    }

    // Retorna todos os médicos
    public List<Medico> readAll(String realPathBase) throws Exception {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medico ORDER BY nome";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medico m = new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("especialidade")
                );
                lista.add(m);
            }
        }
        return lista;
    }

    // Busca um médico pelo ID
    public Medico readById(int id, String realPathBase) throws Exception {
        String sql = "SELECT * FROM medico WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Medico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade")
                    );
                }
            }
        }
        return null;
    }

    // Atualiza um médico existente
    public void update(Medico m, String realPathBase) throws Exception {
        String sql = "UPDATE medico SET nome=?, especialidade=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getEspecialidade());
            ps.setInt(3, m.getId());
            ps.executeUpdate();
        }
    }

    // Remove um médico pelo ID
    public void delete(int id, String realPathBase) throws Exception {
        String sql = "DELETE FROM medico WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}