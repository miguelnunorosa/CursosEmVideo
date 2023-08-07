package io.github.miguelnunorosa.appgaseta.util;



public class UtilGasEta {

    public void metodoEstatico(){} //no need this: ClassName class_name = new ClassNAme();
    public static void metodoNaoEstatico(){} //need this: ClassName class_name = new ClassNAme();



    public static String calculateBestOption(double gasolina, double etanol){
        //ex: gasolina price: R$ 5,12
        //ex: etanol price: R$ 3,99
        //ideal price = gasolina * 0,70  = R$ 3,548

        double idealPrice = gasolina * 0.70;
        String returnMessage;

        if(etanol <= idealPrice){
            returnMessage = "Abastecer com etanol";
        }else{
            returnMessage = "Abastecer com gasolina";
        }

        return returnMessage;
    }


}
