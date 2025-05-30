package com.mack.clinica.model;

public class Consulta {
    private String dataHora;
    private String status;
    private String medicoNome;
    private String pacienteNome;

    // Getters e Setters
    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }
    
    public String getPacienteNome(){
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome){
        this.pacienteNome = pacienteNome;
    }
}