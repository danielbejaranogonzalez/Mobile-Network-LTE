package Main;

import Controlador.ControladorGeneral;
import Vista.Hilos.ActualizacionDiagramasEU;
import Vista.Hilos.ActualizacionDiagramasSitios;
import Vista.Hilos.ActualizacionEnlacesOutdoor;
import Vista.Hilos.ActualizacionGeneralMapa;
import Vista.Hilos.ProcesoPrincipal;
import Vista.general.FramePrincipal;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bejarano
 */
public class InterfazAPPMain {

    private Thread hiloPrincipal;
    private Thread hiloActualizacionSitios;
    private Thread hiloActualizacionEUs;
    private Thread hiloActualizacionGeneralMapa;
    private Thread hiloActualizacionEnlacesOutdoor;

    private ProcesoPrincipal procesoPrincipal;
    private ActualizacionDiagramasSitios actualizacionSitios;
    private ActualizacionDiagramasEU actualizacionEUs;
    private ActualizacionGeneralMapa actualizacionGeneralMapa;
    private ActualizacionEnlacesOutdoor actualizacionEnlacesOutdoor;

    private ControladorGeneral controlador;

    public InterfazAPPMain(ControladorGeneral controlador){

        controlador = new ControladorGeneral();

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            procesoPrincipal = new ProcesoPrincipal(controlador);
            actualizacionSitios = new ActualizacionDiagramasSitios(controlador);
            actualizacionEUs = new ActualizacionDiagramasEU(controlador);
            actualizacionGeneralMapa = new ActualizacionGeneralMapa(controlador);
            actualizacionEnlacesOutdoor = new ActualizacionEnlacesOutdoor(controlador);
                    
            hiloPrincipal = new Thread(procesoPrincipal);
            hiloActualizacionSitios = new Thread(actualizacionSitios);
            hiloActualizacionEUs = new Thread(actualizacionEUs);
            hiloActualizacionGeneralMapa = new Thread(actualizacionGeneralMapa);
            hiloActualizacionEnlacesOutdoor = new Thread(actualizacionEnlacesOutdoor);
            
            hiloPrincipal.start();
            hiloActualizacionSitios.start();
            hiloActualizacionEUs.start();
            hiloActualizacionGeneralMapa.start();
            hiloActualizacionEnlacesOutdoor.start();

	}catch (Exception e){
		e.printStackTrace();
	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new InterfazAPPMain(new ControladorGeneral());
    }
}
