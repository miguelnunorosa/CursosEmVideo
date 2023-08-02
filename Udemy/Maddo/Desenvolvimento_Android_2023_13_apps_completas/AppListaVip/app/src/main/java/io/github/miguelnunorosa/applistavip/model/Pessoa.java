package io.github.miguelnunorosa.applistavip.model;

public class Pessoa {

    private String nome;
    private String apelido;
    private String curso;
    private String telefone;

    public Pessoa(){}

    public Pessoa(String nome, String apelido, String curso, String telefone) {
        this.nome = nome;
        this.apelido = apelido;
        this.curso = curso;
        this.telefone = telefone;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
