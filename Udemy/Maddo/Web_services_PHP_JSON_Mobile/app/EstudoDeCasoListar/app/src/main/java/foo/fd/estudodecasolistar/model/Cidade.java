package foo.fd.estudodecasolistar.model;


/**
 * All class atributes are from database table 'cidade'.
 *
 * We can access the private data from get and setter
 * */
public class Cidade {

    private int id;
    private int estadoID;
    private String nome;


    public Cidade() {} //empty constructor "only for creating objects to testing"

    public Cidade(int id, int estadoID, String nome) {
        this.id = id;
        this.estadoID = estadoID;
        this.nome = nome;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getEstadoID() {return estadoID;}

    public void setEstadoID(int estadoID) {this.estadoID = estadoID;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

}
