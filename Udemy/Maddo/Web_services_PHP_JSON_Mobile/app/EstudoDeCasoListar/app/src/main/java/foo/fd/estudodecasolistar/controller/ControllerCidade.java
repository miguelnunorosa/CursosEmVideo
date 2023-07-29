package foo.fd.estudodecasolistar.controller;

import java.util.List;

import foo.fd.estudodecasolistar.model.Cidade;

public class ControllerCidade {
    //requisicões do processamento ao web service
    /**
     * @param cidades
     * @param nome
     * @return
     **/
    public Cidade buscarObjeto(List<Cidade> cidades, String nome) {

        for (Cidade obj : cidades) {
            if (obj.getNome().equals(nome)) {
                return obj;
            }
        }

        return null;
    }
}
