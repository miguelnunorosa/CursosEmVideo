package io.github.miguelnunorosa.applistavip.model;

public class Curso {

    private String nomeCurso;

    public Curso(String courseName) {
        this.nomeCurso = courseName;
    }


    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

}
