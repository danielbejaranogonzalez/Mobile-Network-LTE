/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.Hilos;

import Controlador.ControladorGeneral;
import Vista.general.FramePrincipal;

/**
 *
 * @author Bejaranos
 */
public class ActualizacionDiagramasSitios implements Runnable
{

    public ControladorGeneral controlador;

    public ActualizacionDiagramasSitios(ControladorGeneral controlador)
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
        try { Thread.sleep(6000); } catch(InterruptedException e) { /* we tried */}
        while(true)
        {
            try { Thread.sleep(5); } catch(InterruptedException e) { /* we tried */}
            controlador.getControladorHilos().actualizarSitiosEnMapa();
        }
    }
}
