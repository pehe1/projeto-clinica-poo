package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD da Ficha Clínica.
 */
public class FichaClinicaDAO {

    // Cria uma nova ficha clínica
    public void create(FichaClinica ficha, String realPathBase) throws Exception {
        String sql = "INSERT INTO ficha_clinica(paciente_id, medico_id, anotacoes, prescricoes, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ficha.getPacienteId());
            ps.setInt(2, ficha.getMedicoId());
            ps.setString(3, ficha.getAnotacoes());
            ps.setString(4, ficha.getPrescricoes());
            ps.setTimestamp(5, ficha.getData());
            ps.executeUpdate();
        }
    }

    // Retorna todas as fichas clínicas
    public List<FichaClinica> readAll(String realPathBase) throws Exception {
        List<FichaClinica> lista = new ArrayList<>();
        String sql = "SELECT * FROM ficha_clinica ORDER BY data DESC";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FichaClinica ficha = new FichaClinica(
                    rs.getInt("id"),
                    rs.getInt("paciente_id"),
                    rs.getInt("medico_id"),
                    rs.getString("anotacoes"),
                    rs.getString("prescricoes"),
                    rs.getTimestamp("data")
                );
                lista.add(ficha);
            }
        }
        return lista;
    }

    // Busca uma ficha clínica pelo ID
    public FichaClinica readById(int id, String realPathBase) throws Exception {
        String sql = "SELECT * FROM ficha_clinica WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new FichaClinica(
                        rs.getInt("id"),
                        rs.getInt("paciente_id"),
                        rs.getInt("medico_id"),
                        rs.getString("anotacoes"),
                        rs.getString("prescricoes"),
                        rs.getTimestamp("data")
                    );
                }
            }
        }
        return null;
    }

    // Atualiza uma ficha clínica existente
    public void update(FichaClinica ficha, String realPathBase) throws Exception {
        String sql = "UPDATE ficha_clinica SET paciente_id=?, medico_id=?, anotacoes=?, prescricoes=?, data=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ficha.getPacienteId());
            ps.setInt(2, ficha.getMedicoId());
            ps.setString(3, ficha.getAnotacoes());
            ps.setString(4, ficha.getPrescricoes());
            ps.setTimestamp(5, ficha.getData());
            ps.setInt(6, ficha.getId());
            ps.executeUpdate();
        }
    }

    // Remove uma ficha clínica pelo ID
    public void delete(int id, String realPathBase) throws Exception {
        String sql = "DELETE FROM ficha_clinica WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}