package com.example.setoneditoractionlistener;

public class biblioteca {
    public static String dninie(String dninie){
        //X7233395 ->X?
        //COGE LA PRIMERA X. ES X,Y,Z? DIVIDE EL RESTO DE LA CADENA Y SE QUEDA CON "7233395"
        //DESDE EL ELEMENTO 1 AL FINAL
        //SE QUEDA CON EL RESTO DE LA DIVISION ENTRE 23 Y DEPENDE DE LO QUE DE PUES COGE LA POSICION DE LA LETRA CON CHARAT.
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int pos = "XYZ".indexOf(dninie.charAt(0));
        if(pos!=1){
            dninie = String.valueOf(pos) + dninie.substring(1);
        }
        int numero = Integer.valueOf(dninie);
        return String.valueOf(letras.charAt(numero%23));
    };
}
