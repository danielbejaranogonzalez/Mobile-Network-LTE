/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Hilos;

import Controlador.ControladorGeneral;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public class ActualizacionGeneralMapa  implements Runnable
{
    
    private ControladorGeneral controlador;
    
    public ActualizacionGeneralMapa(ControladorGeneral controlador){
        this.controlador = controlador;
    }
    
    private void delay(int valor){
        double i =0;
        while(i < valor){ 
            Thread.yield();
            i++;
       }
    }

    public void run() {
        /*
         * Actualizar Mapa
         */
        try {
            //delay(66666666);
            Thread.sleep(18000);
        } catch (InterruptedException ex) {
            
        }
        while(true)
        {
            try { Thread.sleep(10); } catch(InterruptedException e) { /* we tried */}
            controlador.getControladorHilos().actualizarCapasEnMapa();
        }
    }
       
}
