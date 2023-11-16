package curso.java.PrimeiraTarefaJava;

public class JavaOperadoresDiversos {

    public static void main(String[] args) {

        // Unário ++ --

        int valorA = 10;

        valorA++;

        System.out.println("Operadores Unários (++) (--)\n");
        System.out.println("Resultado: "+(valorA--));
        System.out.println("Novo Resultado: "+valorA);

        // Lógicos (true false)
        // == igualdade
        // != desigualdade
        // < menor
        // <= menor ou igual
        // > maior
        // >= maior ou igual

        int valorB = 11;
        int valorC = 12;

        boolean resultado = (valorB >= valorC);
        System.out.println("\n\nOperadores Lógicos:");
        System.out.println("Resultado: "+resultado);

        // Relacionais (true false) - Comum em laços de repetição (loops)
        // && é o "E" Lógico
        // || é o "OU" Lógico
        // ! é a "NEGAÇÃO" Lógica

        boolean relacional =  !(valorA == valorC);


        System.out.println("\n\nOperadores Relacional:");
        System.out.println("Resultado: "+relacional);

        int valorD = valorA;
        valorD += valorA;
        valorD -= valorA;
        valorD /= valorA;
        valorD *= valorA;

        System.out.println("\n\nOperadores Atribuição:");
        System.out.println("Resultado: "+valorD);



    }

}
