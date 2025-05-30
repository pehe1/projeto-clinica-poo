package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Classe DAO para operações CRUD de Paciente
public class PacienteDAO {

    //Insere um novo paciente no banco de dados
    public void create(Paciente p, String realPathBase) throws Exception {
        String sql = "INSERT INTO paciente(nome, email, telefone, cpf) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getCpf());
            ps.executeUpdate();
        }
    }

    //Retorna todos os pacientes cadastrados
    public List<Paciente> readAll(String realPathBase) throws Exception {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY nome";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("cpf")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    //Busca um paciente pelo ID
    public Paciente readById(int id, String realPathBase) throws Exception {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("cpf")
                    );
                }
            }
        }
        return null;
    }

    //Atualiza os dados de um paciente existente
    public void update(Paciente p, String realPathBase) throws Exception {
        String sql = "UPDATE paciente SET nome=?, email=?, telefone=?, cpf=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getCpf());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        }
    }

    
    //Remove um paciente pelo ID
    public void delete(int id, String realPathBase) throws Exception {
        String sql = "DELETE FROM paciente WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}