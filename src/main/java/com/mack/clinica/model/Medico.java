package com.mack.clinica.model;

public class Medico {
    private int id;
    private String nome;
    private String especialidade;

    public Medico() {}

    public Medico(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Medico(int id, String nome, String especialidade) {
        this(nome, especialidade);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}