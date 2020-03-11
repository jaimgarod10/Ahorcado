package Main;

import Clases.Juego;
import Clases.AccionPalabra;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jaim
 */
public class Ahorcado extends JFrame{

    public static final String RESET_TXT = "reset";
    public static final int STATUS_FONT_SIZE = 22, BTN_TAM_FONT = 15, FRM_VGAP = 18, FRM_H = 700, FRM_V = 800;

    private final Juego juego;
    private final AhorcadoPanel ahorcado;
    private final JLabel lblEstado;
    private final BotonesLetras botones;

    public Ahorcado(){
        super();
        juego = new Juego(this);
        ahorcado = new AhorcadoPanel();
        lblEstado = new JLabel();
        botones = new BotonesLetras();
    }

    private void iniciarVentana(){

        this.setLayout(new BorderLayout(0, FRM_VGAP));
        this.setSize(FRM_H, FRM_V);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.add(ahorcado, BorderLayout.CENTER);

        lblEstado.setFont(new Font("Arial", Font.PLAIN, STATUS_FONT_SIZE));
        lblEstado.setHorizontalAlignment(JLabel.CENTER);
        this.add(lblEstado, BorderLayout.NORTH);

        this.add(botones, BorderLayout.SOUTH);
    }

    public void añadirAhorcado(){
        ahorcado.añadirFallo();
        this.repaint();
    }

    public void actualizarEstado(){
        lblEstado.setFont(new java.awt.Font("Tahoma", 1,30));
        if(juego.gana()){
            lblEstado.setText("¡Felicidades, has acertado la palabra! Era: " + juego.getAdivinaPalabra());
            botones.desactivarLetras();
        }else if(ahorcado.ahorcadoCompleto()){
            lblEstado.setText("¡Has perdido! La palabra era: " + juego.getPalabra());
            botones.desactivarLetras();
        } else{
            lblEstado.setText("Lo que has acertado por ahora: " + juego.getAdivinaPalabra());
        }
    }

    public void resetearAhorcado(){
        ahorcado.resetearIntentos();
        botones.resetearBotones();
        this.repaint();
    }

    public static void main(String[] args) {
        Ahorcado ventana = new Ahorcado();
        ventana.iniciarVentana();
        ventana.getContentPane().setBackground(new Color(147, 214, 183));
    }

    private class BotonesLetras extends JPanel{

        public static final int NUM_COL = 9;
        public static final int NUM_FILAS = 3;

        private JButton btnLetras[];

        private BotonesLetras(){
            super();

            Font font = new Font("Arial", Font.PLAIN, BTN_TAM_FONT);
            AccionPalabra btnListener = new AccionPalabra(juego);
            this.btnLetras = new JButton[NUM_COL * NUM_FILAS];

            this.setLayout(new GridLayout(NUM_FILAS, NUM_COL));

            for(int i = 0; i < btnLetras.length; i++){
                btnLetras[i] = new JButton(getLetra(i));
                btnLetras[i].addActionListener(btnListener);
                btnLetras[i].setFont(font);
                this.add(btnLetras[i]);
            }
        }

        private void desactivarLetras(){
            for(int i = 0; i < btnLetras.length - 1; i++)
                btnLetras[i].setEnabled(false);
        }

        private void resetearBotones(){
            for(int i = 0; i < btnLetras.length; i++)
                btnLetras[i].setEnabled(true);
        }

        private final String getLetra(int num){
            switch(num){
                case 0: return "a";
                case 1: return "b";
                case 2: return "c";
                case 3: return "d";
                case 4: return "e";
                case 5: return "f";
                case 6: return "g";
                case 7: return "h";
                case 8: return "i";
                case 9: return "j";
                case 10: return "k";
                case 11: return "l";
                case 12: return "m";
                case 13: return "n";
                case 14: return "o";
                case 15: return "p";
                case 16: return "q";
                case 17: return "r";
                case 18: return "s";
                case 19: return "t";
                case 20: return "u";
                case 21: return "v";
                case 22: return "w";
                case 23: return "x";
                case 24: return "y";
                case 25: return "z";
                case 26: return RESET_TXT;
                default: return "";
            }
        }

    }

    private class AhorcadoPanel extends JPanel{

        public static final int CONT = 9;

        private int fallo;

        public AhorcadoPanel(){
            super();

            this.fallo = 0;
        }

        @Override
        public void paintComponent(Graphics g){
            int ancho = this.getWidth();
            int alto = this.getHeight();
            int anchoBloque = ancho/10;
            int altoBloque = alto/10;

            switch(fallo){
                //boca
                case 9: g.drawArc(ancho/2 - anchoBloque/2, 5 * altoBloque / 4, anchoBloque, altoBloque/2, 0, 180);
                    //ojo izq
                case 8: g.fillOval(ancho/2 + anchoBloque/4, altoBloque/2, anchoBloque/4, altoBloque/4);
                    //ojo derecho
                case 7: g.fillOval(ancho/2 - anchoBloque/2, altoBloque/2, anchoBloque/4, altoBloque/4);
                    //pierna izq
                case 6: g.drawLine(ancho/2, alto/2, ancho/2 + anchoBloque, alto/2 + (2 * altoBloque));
                    //pierna derecha
                case 5: g.drawLine(ancho/2, alto/2, ancho/2 - anchoBloque, alto/2 + (2 * altoBloque));
                    //brazo izq
                case 4: g.drawLine(ancho/2, 2 * altoBloque, ancho/2 + anchoBloque, 4 * altoBloque);
                    //brazo derecho
                case 3: g.drawLine(ancho/2, 2 * altoBloque, ancho/2 - anchoBloque, 4 * altoBloque);
                    //cuerpo
                case 2: g.drawLine(ancho/2, 2 * altoBloque, ancho/2, alto/2);
                    //cabeza
                case 1: g.drawOval(ancho/2 - anchoBloque, 0, 2 * anchoBloque, 2 * altoBloque);
                default: break;
            }
        }

        private void resetearIntentos(){
            this.fallo = 0;
        }

        private void añadirFallo(){
            if(fallo < CONT)
                fallo++;
            this.repaint();
        }

        private boolean ahorcadoCompleto(){
            return (fallo == CONT);
        }
    }

}
