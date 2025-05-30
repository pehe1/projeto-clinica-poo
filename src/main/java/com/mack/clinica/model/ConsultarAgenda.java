package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultarAgenda extends JFrame {
    private JTextField txtPaciente;
    private JTextField txtMedico;
    private JFormattedTextField txtData;
    private JButton btnBuscar;
    private JTable tabelaResultados;

    public ConsultarAgenda() {
        setTitle("Consultar Agenda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de filtros
        JPanel painelFiltros = new JPanel(new GridLayout(2, 4, 5, 5));
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros"));

        txtPaciente = new JTextField();
        txtMedico = new JTextField();
        txtData = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));

        painelFiltros.add(new JLabel("Paciente:"));
        painelFiltros.add(txtPaciente);
        painelFiltros.add(new JLabel("Médico:"));
        painelFiltros.add(txtMedico);
        painelFiltros.add(new JLabel("Data:"));
        painelFiltros.add(txtData);

        btnBuscar = new JButton("Buscar");
        painelFiltros.add(btnBuscar);

        add(painelFiltros, BorderLayout.NORTH);

        // Tabela de resultados
        tabelaResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaResultados);
        add(scrollPane, BorderLayout.CENTER);

        // Ação do botão Buscar
        btnBuscar.addActionListener(e -> buscarConsultas());
    }

    private void buscarConsultas() {
        String nomePaciente = txtPaciente.getText();
        String MedicoNome = txtMedico.getText();
        String dataConsulta = txtData.getText();

        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> consultas = consultaDAO.buscarConsultas(nomePaciente, MedicoNome, dataConsulta);

        // Atualizar a tabela com os resultados
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Paciente");
        modelo.addColumn("Médico");
        modelo.addColumn("Data");
        modelo.addColumn("Hora");

        for (Consulta c : consultas) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getPaciente().getNome(),
                c.getMedico().getNome(),
                c.getData(),
                c.getHora()
            });
        }

        tabelaResultados.setModel(modelo);
    }
}
