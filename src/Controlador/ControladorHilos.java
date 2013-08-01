/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Libreria.CentrarScreen;
import Libreria.Delay;
import Modelo.GEO.CoordenadaGeografica;
import Modelo.GEO.MapaGeneral;
import Modelo.PRO.Proyecto;
import Modelo.PRO.Sitio;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import Modelo.SIM.Outdoor.Paths.Path;
import Modelo.SIM.Outdoor.Paths.PathModelo_ITU_R_M2135;
import Vista.ventanas.Simulacion.VentanaRunSimulacion;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorHilos {
    
    private ControladorGeneral controlador;
    
    public ControladorHilos(ControladorGeneral controlador){
        this.controlador = controlador;
    }
    
    
    public void actualizarSitiosEnMapa(){
          try{
              controlador.getPrincipal().getPanelDiagramas().actualizarSitios();
          }catch(NullPointerException NPE){
                   try{
                      try { Thread.sleep(3000); } catch(InterruptedException e) { }
                      controlador.getPrincipal().getPanelDiagramas().actualizarSitios();
                   }catch(NullPointerException NPE3){
                       //JOptionPane.showMessageDialog(null, "'Se ha generado un problema en el proseso de actualización de los objetos de Mapa 1. Reinicie la Aplicación.'","Error 005: NullPointerException",JOptionPane.ERROR_MESSAGE);
                   }
          }
    }

    public void actualizarEquiposDeUsuarioEnMapa() {
        try{
          controlador.getPrincipal().getPanelDiagramas().actualizarEUs();
          }catch(NullPointerException NPE){
                   try{
                      try { Thread.sleep(3000);} catch(InterruptedException e) { /* we tried */}
                      controlador.getPrincipal().getPanelDiagramas().actualizarEUs(); 
                   }catch(NullPointerException NPE3){
                       //JOptionPane.showMessageDialog(null, "'Se ha generado un problema en el proseso de actualización de los objetos de Mapa 2. Reinicie la Aplicación.'","Error 005: NullPointerException",JOptionPane.ERROR_MESSAGE);
                   }
          }
    }
    
    public void actualizarCapasEnMapa() {
        controlador.getPrincipal().getPanelDiagramas().actualizarCapasEnMapa();
    }
    
    public void actualizarCoordenadasOutdoorLinksEnMapa(){
        
        CoordenadaGeografica[][] coordenadasEnlacesOutdoor = new CoordenadaGeografica[2][0];
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 
                 coordenadasEnlacesOutdoor = new CoordenadaGeografica[2][proyectos.get(i).getGrupoOutdoorLinks().size()];
                 for(int j=0; j < proyectos.get(i).getGrupoOutdoorLinks().size(); j++){
                     OutdoorLinkRAN outdoorLinkActual = (OutdoorLinkRAN)proyectos.get(i).getGrupoOutdoorLinks().get(j);
                     Sitio sitioEncontrado = buscarSitioDeNodeB(outdoorLinkActual.geteNodeBAsociado().getNombreSitioAsociado());
                     
                     coordenadasEnlacesOutdoor[0][j] = outdoorLinkActual.getEquipoUsuario().getCoordenadaEU();
                     coordenadasEnlacesOutdoor[1][j] = sitioEncontrado.getCoordenadaSitio();
                 }
             }
             
        }
        controlador.getPrincipal().getPanelDiagramas().establecerCoordenadasEnlacesOutdoor(coordenadasEnlacesOutdoor);
    }
    
    /*
     * Encuentra un sitio por su nombre
     */
    private Sitio buscarSitioDeNodeB(String sitioBuscado){
        
        Sitio sitioEncontrado = null;
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoSitios().size(); j++){
                     if(proyectos.get(i).getGrupoSitios().get(j).getNombreSitio().equals(sitioBuscado)){
                         sitioEncontrado = proyectos.get(i).getGrupoSitios().get(j);
                     }
                 }
             }
        }
        return sitioEncontrado;
    }
    
    
    private VentanaRunSimulacion ventanaSimRun;
    public void crearVentanaCorrerSimulacion() {
    
    int cantidadPaths = 0;    
    LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
    String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
    
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                for(int j=0; j < proyectos.get(i).getGrupoOutdoorLinks().size();j++){
                       OutdoorLinkRAN link =  (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado();
                    cantidadPaths = link.getListaPaths().size();
                }
            }
        }
        try{
            controlador.getHiloSimulacion().start();
        }catch(IllegalThreadStateException ITE){
            JOptionPane.showMessageDialog(null, "'Hay un proceso de simulacion aun en ejecucion'","Error 021: IllegalThreadStateException",JOptionPane.ERROR_MESSAGE);
        }
        
        ventanaSimRun = new VentanaRunSimulacion(controlador, cantidadPaths);
        CentrarScreen.centrarVentana(ventanaSimRun);
        ventanaSimRun.setVisible(true);
    }
    
    public void pararSimulacion() {
        controlador.getEjecutarSimulacion().matarHilo();
        controlador.reInstanciarHiloSimulacion();
        ventanaSimRun.dispose();;
    }

    public void simularPropagacion() throws InterruptedException {
       //System.out.println("Test Proceso Simulacion");
    LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
    String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
    
    double pathLossDown[] = null;      
    double pathLossUp[] = null;
    double RSSI_Down[] = null;      
    double sensibilidad_Down = 0.0;   
    double sensibilidad_Up = 0.0;
    double RSSI_Up[] = null;
    double CNR_Down[] = null;      
    double CNR_Up[] = null;
    double sensib_Down[] = new double[1];
    double sensib_Up[] = new double[1];
    LinkedList PDP = null;
    
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                for(int j=0; j < proyectos.get(i).getGrupoOutdoorLinks().size();j++){
                    OutdoorLinkRAN link =  (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado();
                    
                    Sitio sitioEncontrado = buscarSitioDeNodeB(link.geteNodeBAsociado().getNombreSitioAsociado());
                    double distanciaEnlaceEnTierra = MapaGeneral.calcularDistanciaEntreDosPuntos(sitioEncontrado.getCoordenadaSitio(), link.getEquipoUsuario().getCoordenadaEU());
                    double distanciaEntreBSyEU = MapaGeneral.calcularDistanciaEntreBSyEU(
                            sitioEncontrado.getCoordenadaSitio(), link.getEquipoUsuario().getCoordenadaEU(), 
                            link.getEnlaceIndoorAsociado().getAntena().getAlturaAntena(), link.getEquipoUsuario().getAltura());
                    
                    ventanaSimRun.setParametrosLink(distanciaEnlaceEnTierra, distanciaEntreBSyEU);
                    
                    pathLossDown = new double[link.getListaPaths().size()]; 
                    pathLossUp = new double[link.getListaPaths().size()];
                    RSSI_Down = new double[link.getListaPaths().size()];
                    RSSI_Up = new double[link.getListaPaths().size()];
                    CNR_Down = new double[link.getListaPaths().size()];
                    CNR_Up = new double[link.getListaPaths().size()];
                    PDP = new LinkedList();
                    
                    for(int k=0; k<link.getListaPaths().size(); k++){
                        PathModelo_ITU_R_M2135 pathActual = (PathModelo_ITU_R_M2135)link.getListaPaths().get(k);
                        
                        pathLossDown[k] = pathActual.calcularPathLoss(true) + (pathActual.calcularMargenShadowing());
                        pathLossUp[k] = pathActual.calcularPathLoss(false) + (pathActual.calcularMargenShadowing());
                        
                        RSSI_Down[k] = pathActual.calcularRSSI(true, pathLossDown[k]);
                        RSSI_Up[k] = pathActual.calcularRSSI(false, pathLossUp[k]);
                        
                        CNR_Down[k] = pathActual.calcularCNR(true, RSSI_Down[k]);
                        CNR_Up[k] = pathActual.calcularCNR(false, RSSI_Up[k]);
                        
                        PDP.add(pathActual.calcularPowerDelayProfile(RSSI_Down[k]));
                        sensibilidad_Down = (double)pathActual.getOutdoorLink().getEquipoUsuario().getSensibilidad();
                        sensibilidad_Up = (double )pathActual.getOutdoorLink().getEnlaceIndoorAsociado().getTargetaEquipoRadio().getSensibilidad();
                    }
                }
            }
        }
        sensib_Down[0] = sensibilidad_Down;
        sensib_Up[0] = sensibilidad_Up;
        try{
        ventanaSimRun.setPathLossDown(pathLossDown);
        ventanaSimRun.setPathLossUp(pathLossUp);
        ventanaSimRun.setRSSI_Down(RSSI_Down);
        ventanaSimRun.setRSSI_Up(RSSI_Up);
        ventanaSimRun.setSensibilidades(sensib_Down, sensib_Up);
        ventanaSimRun.setCNR_Down(CNR_Down);
        ventanaSimRun.setCNR_Up(CNR_Up);
        //ventanaSimRun.setTextPathLossUp("" + pathLossUp);
        ventanaSimRun.setPDP(PDP);
        
        }catch(NullPointerException NPE){
            //JOptionPane.showMessageDialog(null, "'Se ha generado un problema en el proseso de simulacion. Verifique los parametros ingresados'","Error 021: NullPointerException",JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
