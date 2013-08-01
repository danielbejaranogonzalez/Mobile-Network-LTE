/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Hilos;

import Controlador.ControladorGeneral;

/**
 *
 * @author User
 */
public class ActualizacionEnlacesOutdoor implements Runnable{
    public ControladorGeneral controlador;
    
    public ActualizacionEnlacesOutdoor(ControladorGeneral controlador){
        this.controlador = controlador;
    }

    private void delay(int valor){
        double i =0;
        while(i < valor){
            Thread.yield();
            Thread.yield();
            i++;
       }
    }
    
    public void run() {
        //delay(35555555);
        try { Thread.sleep(18000); 
            while(true){
                Thread.sleep(1); 
                controlador.getControladorHilos().actualizarCoordenadasOutdoorLinksEnMapa();
            }
        } catch(InterruptedException e) { }
    }
}
