package com.mack.clinica.model;

import java.sql.Timestamp;

//Classe de domínio para a Ficha Clínica do paciente.

public class FichaClinica {
    private int id;
    private int pacienteId;
    private int medicoId;
    private String anotacoes;
    private String prescricoes;
    private Timestamp data;

    public FichaClinica() {}

    public FichaClinica(int id, int pacienteId, int medicoId, String anotacoes, String prescricoes, Timestamp data) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.anotacoes = anotacoes;
        this.prescricoes = prescricoes;
        this.data = data;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }
    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public String getAnotacoes() {
        return anotacoes;
    }
    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getPrescricoes() {
        return prescricoes;
    }
    public void setPrescricoes(String prescricoes) {
        this.prescricoes = prescricoes;
    }

    public Timestamp getData() {
        return data;
    }
    public void setData(Timestamp data) {
        this.data = data;
    }
}