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
public class EjecutarSimulacion implements Runnable
{
    private ControladorGeneral controlador;
    private boolean hiloVivo;
    
    public EjecutarSimulacion(ControladorGeneral controlador){
        this.controlador = controlador;
        hiloVivo = true;
    }
    
    private void delay(int valor){
        double i =0;
        while(i < valor){ 
            Thread.yield();
            Thread.yield();
            i++;
       }
    }
    
    public void matarHilo(){
        hiloVivo = false;
    }
    
    public void run() {
        //delay(77777);
        try { 
        Thread.sleep(1000); 
            while(hiloVivo){
                    controlador.getControladorHilos().simularPropagacion();
                    Thread.sleep(500); 
            }
        } catch(InterruptedException e) { /* we tried */}
    }
    
}
