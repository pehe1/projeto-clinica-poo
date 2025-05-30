package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FichaClinica extends JFrame {
    private JTextArea txtAnotacoes;
    private JTextArea txtPrescricoes;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public FichaClinica() {
        setTitle("Ficha Clínica");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de anotações
        JPanel painelAnotacoes = new JPanel(new BorderLayout());
        painelAnotacoes.setBorder(BorderFactory.createTitledBorder("Anotações"));
        txtAnotacoes = new JTextArea(5, 40);
        painelAnotacoes.add(new JScrollPane(txtAnotacoes), BorderLayout.CENTER);

        // Painel de prescrições
        JPanel painelPrescricoes = new JPanel(new BorderLayout());
        painelPrescricoes.setBorder(BorderFactory.createTitledBorder("Prescrições"));
        txtPrescricoes = new JTextArea(5, 40);
        painelPrescricoes.add(new JScrollPane(txtPrescricoes), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        // Adicionar painéis à janela
        add(painelAnotacoes, BorderLayout.NORTH);
        add(painelPrescricoes, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnSalvar.addActionListener(e -> {
            // Funcionalidade de salvar será implementada futuramente
            JOptionPane.showMessageDialog(this, "Funcionalidade de salvar ainda não implementada.");
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
