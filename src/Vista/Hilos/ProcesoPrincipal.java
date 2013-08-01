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
public class ProcesoPrincipal implements Runnable
{

    private ControladorGeneral controlador;

    public ProcesoPrincipal(ControladorGeneral controlador){

        this.controlador = controlador;

    }

    public void run() {
        FramePrincipal inicio = new FramePrincipal(this.controlador);
        Thread.yield();
    }
}
