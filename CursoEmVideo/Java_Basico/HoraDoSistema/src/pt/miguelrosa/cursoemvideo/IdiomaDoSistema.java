package pt.miguelrosa.cursoemvideo;

import java.util.Locale;

public class IdiomaDoSistema {

    public static void main(String[] args) {

        Locale idioma = new Locale(System.getProperty("user.language"));

        System.out.println("Lingua do sistema: " + idioma.getLanguage());


    }

}
