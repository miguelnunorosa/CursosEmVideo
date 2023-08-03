package io.github.miguelnunorosa.applistavip.model;

public class Pessoa {

    private String nome;
    private String apelido;
    private String curso;
    private String telefone;


    public Pessoa() {
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


    @Override
    public String toString() {
        return " Pessoa {" +
                " nome='" + nome + '\'' +
                ", apelido='" + apelido + '\'' +
                ", curso='" + curso + '\'' +
                ", telefone=' " + telefone + '\'' +
                '}';
    }


}
