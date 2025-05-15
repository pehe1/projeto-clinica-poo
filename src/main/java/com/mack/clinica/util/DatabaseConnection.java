package com.mack.clinica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Caminho relativo para o banco de dados dentro da aplicação
    private static final String DATABASE_PATH = "WEB-INF/db.db";

    /**
     * Método para obter uma conexão com o banco de dados SQLite.
     * @param realPathBase Caminho real do projeto (obtido pelo ServletContext).
     * @return Conexão ativa com o banco.
     * @throws RuntimeException caso ocorra erro ao conectar.
     */
    public static Connection getConnection(String realPathBase) {
        try {
            // Monta o caminho completo até o banco
            String fullPath = realPathBase + "/" + DATABASE_PATH;

            // Monta a URL de conexão do SQLite
            String url = "jdbc:sqlite:" + fullPath;

            // Retorna a conexão
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            // Exibe erro no console para diagnóstico
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();

            // Lança uma exceção mais genérica para o Servlet tratar como erro de banco
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }
}
