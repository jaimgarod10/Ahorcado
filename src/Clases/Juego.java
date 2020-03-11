package Clases;

import Main.Ahorcado;

/**
 *
 * @author jaim
 */
public class Juego {

    private final ElegirPalabra palabra;
    private final Ahorcado frmAhorcado;

    public Juego(Ahorcado frmAhorcado){
        this.frmAhorcado = frmAhorcado;
        this.palabra = new ElegirPalabra();
    }

    public boolean adivinaLetra(char intento){
        boolean resultado;

        if(!Character.isAlphabetic(intento))
            return false;

        resultado = palabra.intentoAcertado(Character.toLowerCase(intento));

        if(!resultado)
            frmAhorcado.añadirAhorcado();

        frmAhorcado.actualizarEstado();

        return resultado;
    }

    public boolean gana(){
        return (palabra.getAdivinaPalabra().indexOf('_')) < 0;
    }

    public void juegoNuevo(){
        frmAhorcado.resetearAhorcado();
        palabra.palabraNueva();
        frmAhorcado.actualizarEstado();
    }

    public String getAdivinaPalabra(){
        return new String(palabra.getAdivinaPalabra());
    }

    public String getPalabra(){
        return palabra.getPalabra();
    }

}
