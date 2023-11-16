package curso.java.PrimeiraTarefaJava;

public class JavaOperadoresAritmeticos {

    public static void main(String[] args) {

        // Tipos de dados primitivos
        // byte
        // short
        // int
        // long
        // float
        // double

        // Operadores Aritméticos
        // + soma
        // - subtração
        // * multiplicação
        // / divisão
        // % módulo de divisão
        // a faixa -128 / 127 representa valores inteiros

        // exemplos
        byte valorByteA = 1; //o número um é do tipo inteiro
        byte valorByteB = 2;

        int somaByte = valorByteA + valorByteB;
        int subtracaoByte = valorByteA - valorByteB;
        int multiplicacaoByte = valorByteA * valorByteB;
        float divisaoByteFloat = (float) valorByteA / valorByteB;  // 9.0 cast casting
        double divisaoByteDouble = (double) valorByteA / valorByteB; // 9.00

        // sout + tab
        System.out.println("Operações Aritiméticas com byte:\n"); // \n nova linha (NL) (LF)
        System.out.println("(+): " + somaByte);
        System.out.println("(-): " + subtracaoByte);
        System.out.println("(*): " + multiplicacaoByte);
        System.out.println("(/) float: " + divisaoByteFloat);
        System.out.println("(/) double: " + divisaoByteDouble);

    }

}
