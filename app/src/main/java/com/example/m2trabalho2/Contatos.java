package com.example.m2trabalho2;

public class Contatos {


    private String nome;
    private String telefone;
    private boolean tipoTelefone;
    private int id;


    public Contatos(String nome, String telefone, int id, boolean tipoTelefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.tipoTelefone = tipoTelefone;
        this.id = id;
    }

    public Contatos() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoTelefone(boolean tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;

    }

    public boolean isTipoTelefone() {
        return tipoTelefone;
    }

    public int getId() {
        return id;
    }
}