/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.barras;

import Controlador.ControladorGeneral;
import javax.swing.JToolBar;

/**
 *
 * @author Usuario
 */
public class BarraHerramientasPrincipal extends JToolBar{
    
    private HerramientasPrincipales barraHerramientas;
    private HerramientasProyecto herramientasProyecto;
    private HerramientasNodeB herramientasNodeB;
    private HerramientasSimulacion herramientasSimulacion;
    
    public BarraHerramientasPrincipal(ControladorGeneral controlador){
        this.setFloatable(false);
        
        barraHerramientas = new HerramientasPrincipales(controlador);
        herramientasProyecto = new HerramientasProyecto(controlador);
        herramientasNodeB = new HerramientasNodeB(controlador);
        herramientasSimulacion = new HerramientasSimulacion(controlador);
        
        add(barraHerramientas);
        add(herramientasProyecto);
        add(herramientasNodeB);
        add(herramientasSimulacion);
    }
    
    
}
