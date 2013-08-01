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
public class ActualizacionDiagramasEU  implements Runnable{
    
    public ControladorGeneral controlador;

    public ActualizacionDiagramasEU(ControladorGeneral controlador)
    {
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
        //delay(55555555);
        try { Thread.sleep(5000); } catch(InterruptedException e) { }
        while(true)
        {
            try { Thread.sleep(1); } catch(InterruptedException e) { }
            controlador.getControladorHilos().actualizarEquiposDeUsuarioEnMapa();
        }
    }
}
