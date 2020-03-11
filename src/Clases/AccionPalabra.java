package Clases;

import Main.Ahorcado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionPalabra implements ActionListener {

    private final Juego juego;

    public AccionPalabra(Juego juego){
        this.juego = juego;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton srcBtn = (JButton)e.getSource();
        String btnText = srcBtn.getText();

        if(btnText.equals(Ahorcado.RESET_TXT)){
            juego.juegoNuevo();
            return;
        }

        if(!juego.gana()){
            srcBtn.setEnabled(false);
            juego.adivinaLetra(btnText.charAt(0));
        }else{

        }

    }

}
