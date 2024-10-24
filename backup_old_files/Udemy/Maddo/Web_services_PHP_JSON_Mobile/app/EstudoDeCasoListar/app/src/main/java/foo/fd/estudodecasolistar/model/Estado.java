package foo.fd.estudodecasolistar.model;


/**
 * All class atributes are from database table 'estado'.
 *
 * We can access the private data from get and setter
 * */
public class Estado {

    private int id;
    private String sigla;
    private String nome;


    public Estado() {} //empty constructor "only for creating objects to testing"

    public Estado(int id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getSigla() { return sigla; }

    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }



}
