package curso.java.PrimeiraTarefaJava;

public class JavaOperadoresAritmeticosShort {

    public static void main(String[] args) {

        // Tipos de dados primitivos
        // short <----

        // Operadores Aritméticos
        // + soma
        // - subtração
        // * multiplicação
        // / divisão
        // % módulo de divisão

        // exemplos
        short valorShortA = 10;
        short valorShortB = 3;

        int soma = valorShortA + valorShortB;
        int subtracao = valorShortA - valorShortB;
        int multiplicacao = valorShortA * valorShortB;
        float divisaoFloat =  (float) valorShortA / valorShortB;
        double divisaoDouble = (double) valorShortA / valorShortB;

        // sout + tab
        System.out.println("Operações Aritiméticas com short:\n");
        System.out.println("(+): " + soma);
        System.out.println("(-): " + subtracao);
        System.out.println("(*): " + multiplicacao);
        System.out.println("(/) float: " + divisaoFloat);
        System.out.println("(/) double: " + divisaoDouble);

    }


}
