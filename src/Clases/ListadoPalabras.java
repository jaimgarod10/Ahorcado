package Clases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;


    /**
     *
     * @author jaim
     */
    public class ListadoPalabras {
        private final ArrayList<String> listado;
        private int tamañoListado;

        public ListadoPalabras(){
            this.listado = new ArrayList<>();
            this.tamañoListado = 0;
            refrescarListado();
        }

        private final void refrescarListado(){
            Scanner scan = null;
            try{
                scan = new Scanner(new FileReader("listado.txt"));
                while(scan.hasNext()){
                    listado.add(scan.next());
                }
            }catch(IllegalStateException | FileNotFoundException e){
                System.err.println("Error al leer el fichero!");
                System.exit(1);
            }catch (NoSuchElementException e){

            }finally{
                this.tamañoListado = this.listado.size();
                if(scan != null)
                    scan.close();
            }
        }

        public String palabraRandom(){
            int index;
            String palabra;
            Random random = new Random();

            if(tamañoListado == 0)
                refrescarListado();

            index = random.nextInt(tamañoListado);
            palabra = new String(listado.get(index));
            listado.remove(index);
            tamañoListado = listado.size();

            return palabra;
        }

}
