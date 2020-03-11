package Clases;

/**
 *
 * @author jaim
 */
public class ElegirPalabra {
    private ListadoPalabras banco;
    private String palabra;
    private char[] adivinaPalabra;

    public ElegirPalabra(){
        banco = new ListadoPalabras();
        palabra = banco.palabraRandom();
        adivinaPalabra = new char[palabra.length()];

        for(int i = 0; i < palabra.length(); i++)
            adivinaPalabra[i] = '_';
    }


    public boolean intentoAcertado(char guess){
        boolean result = false;

        for(int i = 0; i < palabra.length(); i++)
            if(guess == palabra.charAt(i)){
                result = true;
                adivinaPalabra[i] = guess;
            }

        return result;
    }


    public void palabraNueva(){
        palabra = banco.palabraRandom();
        adivinaPalabra = new char[palabra.length()];
        for(int i = 0; i < palabra.length(); i++)
            adivinaPalabra[i] = '_';
    }


    public String getPalabra(){
        return new String(this.palabra);
    }

    public String getAdivinaPalabra(){
        return new String(this.adivinaPalabra);
    }

}
