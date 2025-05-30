package com.mack.clinica.model;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public Paciente() {}

    public Paciente(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Paciente(int id, String nome, String email, String telefone, String cpf) {
        this(nome, email, telefone, cpf);
        this.id = id;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}