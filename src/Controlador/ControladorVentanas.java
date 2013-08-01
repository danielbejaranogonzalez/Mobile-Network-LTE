/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Libreria.CentrarScreen;
import Modelo.GEO.CoordenadaGeografica;
import Modelo.GEO.MapaGeneral;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.PRO.Proyecto;
import Modelo.RAN.Antena;
import Modelo.RAN.ENodeB;
import Modelo.RAN.Radio;
import Modelo.RAN.TarjetaRadio;
import Modelo.PRO.Sitio;
import Modelo.RAN.ElementoAtenuacion;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import Modelo.SIM.Outdoor.Paths.PathModelo_ITU_R_M2135;
import Vista.ventanas.RAN.Components.indoorLink.VentanaCrearIndoorLink;
import Vista.ventanas.RAN.Components.antenas.VentanaCrearAntena;
import Vista.ventanas.RAN.Components.atenuadores.VentanaCrearAtenuador;
import Vista.ventanas.RAN.Components.radios.VentanaNuevoRadio;
import Vista.ventanas.Simulacion.VentanaNuevoPathModeloITU;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorVentanas {
    
    private ControladorGeneral controlador;
    
    public ControladorVentanas(ControladorGeneral controlador){
        this.controlador = controlador;
    }
        
    public void crearNuevoProyecto(String nombreProyecto, String tipoProyecto, boolean isPredeterminado){
        controlador.getLibroDeProyectos().crearProyecto(nombreProyecto, tipoProyecto, isPredeterminado);
        
        String predeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        controlador.getPrincipal().getPanelArboles().establecerProyectoPredeterminado(predeterminado);
        actualizarArbolPrincipal();
    }
    
    public void eliminarProyecto(int i, String proyectoPredeterminado){
        controlador.getLibroDeProyectos().getProyectos().remove(i);
        establecerNuevoProyectoPredeterminado(proyectoPredeterminado);
        actualizarArbolPrincipal();
    }
    
    public void establecerNuevoProyectoPredeterminado(String proyectoPredeterminado) {
            
         controlador.getLibroDeProyectos().setProyectoPredeterminado(proyectoPredeterminado);
         String predeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
         controlador.getPrincipal().getPanelArboles().establecerProyectoPredeterminado(predeterminado);
         actualizarArbolPrincipal();
    }
    
    public void crearNuevoSitioCoordenadasDecimales(double latitud, double longitud, double elevacion, String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio){
        int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
                for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        controlador.getLibroDeProyectos().getProyectos().get(i).crearSitio(latitud, longitud, elevacion, nombreSitio, direccionSitio, pais, ciudad, tipoSitio); 
                    }
                } 
          actualizarArbolPrincipal();
    }
    
    public void crearNuevoSitioCoordenadasGradMinSeg(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion,
           String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio){
           
           int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
                for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        controlador.getLibroDeProyectos().getProyectos().get(i).crearSitio(latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud,
                                longitudGrados, longitudMinutos, longitudSegundos, hemisferioLongitud, elevacion, nombreSitio, direccionSitio, pais, ciudad, tipoSitio);
                        controlador.getPrincipal().getPanelArboles().establecerTipoSitio(controlador.getLibroDeProyectos().getProyectos().get(i).getGrupoSitios().getLast().getTipoSitio());
                    }
                }
           actualizarArbolPrincipal();
    }
    
    public void editarSitioCoordenadasDecimales(double latitud, double longitud, String sitioNombreAntiguo, String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio){
        int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
        for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        //controlador.getLibroDeProyectos().getProyectos().get(i).crearSitio(latitud, longitud, nombreSitio, direccionSitio, pais, ciudad); 
                        LinkedList <Sitio> sitios = controlador.getLibroDeProyectos().getProyectos().get(i).getGrupoSitios();
                        for(int j=0; j < sitios.size(); j++){
                            if(sitios.get(j).getNombreSitio().equals(sitioNombreAntiguo)){
                                sitios.get(j).setNombreSitio(nombreSitio);
                                sitios.get(j).setDireccionSitio(direccionSitio);
                                sitios.get(j).setPais(pais);
                                sitios.get(j).setCiudad(ciudad);
                                sitios.get(j).getCoordenadaSitio().setLatitudDecimal(latitud);
                                sitios.get(j).getCoordenadaSitio().setLongitudDecimal(longitud);
                                sitios.get(i).setTipoSitio(tipoSitio);
                            }
                        }
                    }
                } 
        actualizarArbolPrincipal();
    }
    
    public void editarSitioCoordenadasGradMinSeg(int latitudGrados, int latitudMinutos, float latitudSegundos, 
            char hemisferioLatitud, int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, 
            String sitioNombreAntiguo, String nombreSitio, String direccionSitio, String pais, String ciudad,  String tipoSitio){
        
        int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
        for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        //controlador.getLibroDeProyectos().getProyectos().get(i).crearSitio(latitud, longitud, nombreSitio, direccionSitio, pais, ciudad); 
                        LinkedList <Sitio> sitios = controlador.getLibroDeProyectos().getProyectos().get(i).getGrupoSitios();
                        for(int j=0; j < sitios.size(); j++){
                            if(sitios.get(j).getNombreSitio().equals(sitioNombreAntiguo)){
                                sitios.get(j).setNombreSitio(nombreSitio);
                                sitios.get(j).setDireccionSitio(direccionSitio);
                                sitios.get(j).setPais(pais);
                                sitios.get(j).setCiudad(ciudad);
                                sitios.get(j).setTipoSitio(tipoSitio);
                                
                                sitios.get(j).getCoordenadaSitio().setLatitudGrados(latitudGrados);
                                sitios.get(j).getCoordenadaSitio().setLatitudMinutos(latitudMinutos);
                                sitios.get(j).getCoordenadaSitio().setLatitudSegundos(latitudSegundos);
                                sitios.get(j).getCoordenadaSitio().setHemisferioLatitud(hemisferioLatitud);
                                sitios.get(j).getCoordenadaSitio().setLongitudGrados(longitudGrados);
                                sitios.get(j).getCoordenadaSitio().setLongitudMinutos(longitudMinutos);
                                sitios.get(j).getCoordenadaSitio().setLongitudSegundos(longitudSegundos);
                                sitios.get(j).getCoordenadaSitio().setHemisferioLongitud(hemisferioLongitud);
                                sitios.get(j).getCoordenadaSitio().convertirLat_A_Decimal();
                                sitios.get(j).getCoordenadaSitio().convertirLong_A_Decimal();
                            }
                        }
                    }
                } 
        actualizarArbolPrincipal();
    }
    
    public void eliminarSitio(String nombreSitio){
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                
                Proyecto proyectoPredeterminado = proyectos.get(i);
                for(int j = 0; j < proyectoPredeterminado.getGrupoSitios().size(); j++){
                    
                    if(proyectoPredeterminado.getGrupoSitios().get(j).getNombreSitio().equals(nombreSitio)){
                        proyectoPredeterminado.getGrupoSitios().remove(j);
                    }
                }
            }
        } 
        actualizarArbolPrincipal();
    }
    
    public void crearNuevaRAN(String nombreRAN, String tecnologiaRAN){
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 //crear nueva Ran
                 proyectos.get(i).crearRAN(nombreRAN, tecnologiaRAN);
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void eliminarRAN(String RANaEliminar) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 //eliminar la Ran seleccionada
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANaEliminar)){
                         proyectos.get(i).getGrupoRANs().remove(j);
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void editarRAN(String nombreRAN, String nuevoNombreRAN, String technologia) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 //eliminar la Ran seleccionada
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         proyectos.get(i).getGrupoRANs().get(j).setNombre(nuevoNombreRAN);
                         proyectos.get(i).getGrupoRANs().get(j).setTecnologia(technologia);
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void actualizarArbolPrincipal(){
        controlador.getPrincipal().getPanelArboles().actualizarArbol(controlador.getLibroDeProyectos().getProyectos());
        
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        Proyecto proyectoPredeterminado = null;
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 proyectoPredeterminado = proyectos.get(i);
             }
        }
        controlador.getPrincipal().getPanelConexiones().actualizarArbol(proyectoPredeterminado);
    }

    public void crearNuevoENodeB(String nombreENodeB, String sitioAsociado, String RANAsociada, double alturaTorre , String tipoTorre) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANAsociada)){
                         proyectos.get(i).getGrupoRANs().get(j).crearNodeB(nombreENodeB, alturaTorre, tipoTorre, sitioAsociado);
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void editarENodeB(String nombreENodeBAnterior, String nuevoNombreENodeB, String nuevoSitio, String RANAsociadaAnterior, String nuevaRANAsociada, double nuevaAlturaTorre, String nuevoTipoTorre) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(nuevaRANAsociada.equals(RANAsociadaAnterior)){
                         if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANAsociadaAnterior)){
                             LinkedList<ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                             for(int k=0; k < listaNodos.size(); k++){
                                 if(listaNodos.get(k).getNombre().equals(nombreENodeBAnterior)){
                                     listaNodos.get(k).setNombre(nuevoNombreENodeB);
                                     listaNodos.get(k).getTorre().setAltura(nuevaAlturaTorre);
                                     listaNodos.get(k).getTorre().setTipoTorre(nuevoTipoTorre);
                                     listaNodos.get(k).setNombreSitioAsociado(nuevoSitio);
                                 }
                             }
                         }
                     }else{
                         if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(RANAsociadaAnterior)){
                             LinkedList<ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                             for(int k=0; k < listaNodos.size(); k++){
                                 if(listaNodos.get(k).getNombre().equals(nombreENodeBAnterior)){
                                     listaNodos.remove(k);
                                 }
                             }
                         }if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nuevaRANAsociada)){
                             proyectos.get(i).getGrupoRANs().get(j).crearNodeB(nuevoNombreENodeB, nuevaAlturaTorre, nuevoTipoTorre, nuevoSitio);
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void eliminarENodeB(String nombreNodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     for(int k=0; k < proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB().size(); k++){
                         if(proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB().get(k).getNombre().equals(nombreNodeB)){
                             proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB().remove(k);
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void crearNuevaAntena(String nombreAntena, String tipoAntena, double gananciaMaxima, double anguloAzimut, double anguloElevacion, String polarizacion, 
            double HPBW_Horizantal, double impedancia, double frecuenciaMaxima, double frecuenciaMinima, double alturaAntena, String nombreRAN, String nombreENodeB){
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 listaNodos.get(k).crearAntena(nombreAntena, tipoAntena, gananciaMaxima, 
                                         anguloAzimut, anguloElevacion, polarizacion, HPBW_Horizantal, 
                                         impedancia, frecuenciaMaxima, frecuenciaMinima, alturaAntena);
                                 controlador.getControladorBotones().actualizarGestionAntenas(nombreRAN, nombreENodeB);
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void crearVentanaNuevaAntena(ControladorGeneral controlador, String nombreRAN, String nombreENodeB){
        VentanaCrearAntena ventanaNuevaAntena = new VentanaCrearAntena(new JFrame(), true, controlador, nombreRAN, nombreENodeB);
        CentrarScreen.centrarVentana(ventanaNuevaAntena);
        ventanaNuevaAntena.setVisible(true);
    }
    
    public void eliminarAntena(String nombreAntena, String nombreRAN, String nombreENodeB){
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Antena> antenas= listaNodos.get(k).getAntenas();
                                 for(int l=0; l < antenas.size(); l++){
                                    if(antenas.get(l).getNombreAntena().equals(nombreAntena)){
                                        antenas.remove(l);
                                        controlador.getControladorBotones().actualizarGestionAntenas(nombreRAN, nombreENodeB);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void editarAntena(String nombreAntena, String tipoAntena, double gananciaMaxima, double anguloAzimut, double anguloElevacion,
             String polarizacion, double HPBW_Horizantal, double impedancia, double frecuenciaMaxima, double frecuenciaMinima, double altura, String nombreRAN, 
             String nombreENodeB, String antenaActual) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Antena> antenas= listaNodos.get(k).getAntenas();
                                 for(int l=0; l < antenas.size(); l++){
                                    if(antenas.get(l).getNombreAntena().equals(antenaActual)){
                                        antenas.get(l).setNombreAntena(nombreAntena);       antenas.get(l).setTipoAntena(tipoAntena);
                                        antenas.get(l).setGananciaMaxima(gananciaMaxima);   antenas.get(l).setAnguloAzimut(anguloAzimut);
                                        antenas.get(l).setAnguloElevacion(anguloElevacion); antenas.get(l).setPolarizacion(polarizacion);
                                        antenas.get(l).setHPBW_Horizantal(HPBW_Horizantal); antenas.get(l).setImpedancia(impedancia);                
                                        antenas.get(l).setFrecuenciaMaxima(frecuenciaMaxima);antenas.get(l).setFrecuenciaMinima(frecuenciaMinima);
                                        antenas.get(l).setAlturaAntena(altura);
                                                
                                        controlador.getControladorBotones().actualizarGestionAntenas(nombreRAN, nombreENodeB);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void crearNuevoEUCoordenadasDecimales(double latGradosDecimal, double longGradosDecimal, double elevacion, String nombreEU, float altura, String tipoEU, float sensibilidad, String tecnologia) {
        int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
                for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        controlador.getLibroDeProyectos().getProyectos().get(i).crearEquipoUsuario(latGradosDecimal, longGradosDecimal, elevacion, nombreEU, altura, tipoEU, sensibilidad, tecnologia); 
                    }
                } 
          actualizarArbolPrincipal();
    }

    public void crearNuevoEUCoordenadasGradMinSeg(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion, String nombreEU, float altura, String tipoEU, 
           float sensibilidad, String tecnologia) {
        
            int cantidadProyectos = controlador.getLibroDeProyectos().getProyectos().size();
                for(int i=0; i < cantidadProyectos; i++){
                    String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                    if(controlador.getLibroDeProyectos().getProyectos().get(i).getNombre().equals(proyectoPredeterminado)){
                        controlador.getLibroDeProyectos().getProyectos().get(i).crearEquipoUsuario(latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud,
                                longitudGrados, longitudMinutos, longitudSegundos, hemisferioLongitud, elevacion, nombreEU, altura, tipoEU, sensibilidad, tecnologia);
                    }
                }
          actualizarArbolPrincipal();
    }

    public void crearVentanaNuevoEquipoRadio(ControladorGeneral controlador, String nombreRAN, String nombreENodeB) {
        VentanaNuevoRadio ventanaNuevaAntena = new VentanaNuevoRadio(new JFrame(), true, controlador, nombreRAN, nombreENodeB);
        CentrarScreen.centrarVentana(ventanaNuevaAntena);
        ventanaNuevaAntena.setVisible(true);
    }

    public void crearNuevoEquipoRadio(String nombreRAN, String nombreENodeB, String nombreEquipo, String fabricante, String estado, boolean status) {

        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 listaNodos.get(k).crearRadio(nombreEquipo, fabricante, status);
                                 controlador.getControladorBotones().actualizarGestionRadios(nombreRAN, nombreENodeB);
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void eliminarRadio(String nombreRadio, String nombreRAN, String nombreENodeB) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreRadio)){
                                        radios.remove(l);
                                        controlador.getControladorBotones().actualizarGestionRadios(nombreRAN, nombreENodeB);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void editarEquipoRadio(String nombreRAN, String nombreENodeB, String nombreEquipoActual, String nombreEquipo, String fabricante,
            boolean status) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){//si el proyecto es predeterminado
                 
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreEquipoActual)){
                                        radios.get(l).setNombre(nombreEquipo);    
                                        radios.get(l).setFabricante(fabricante);  
                                        radios.get(l).setEstado(status);
                                        controlador.getControladorBotones().actualizarGestionRadios(nombreRAN, nombreENodeB);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void crearNuevaTargetaEquipoRadio(String nombreRAN, String nombreENodeB, String nombreRadio, int numeroSlot, 
            String fabricante, String tecnologia, String tipoTargeta, String modulacion, String tecnologiaMultiplexacion, 
            double potenciaMax, double sensibilidad, String duplexMode, String bandaFrecuencia, double frecuenciaInferiorUpLink, 
            double frecuenciaSuperiorUpLink, double anchoBandaUpLink, double frecuenciaInferiorDownLink, double frecuenciaSuperiorDownLink, 
            double anchoBandaDownLink) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 //si el proyecto es predeterminado
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreRadio)){
                                        radios.get(l).crearInterfaceRadio(numeroSlot, fabricante, tecnologia, tipoTargeta, modulacion, tecnologiaMultiplexacion, potenciaMax, sensibilidad, 
                                                duplexMode, bandaFrecuencia, frecuenciaInferiorUpLink, frecuenciaSuperiorUpLink, anchoBandaUpLink, frecuenciaInferiorDownLink, 
                                                frecuenciaSuperiorDownLink, anchoBandaDownLink);
                                        controlador.getControladorBotones().actualizarGestionTargetasDeEquipos(nombreRAN, nombreENodeB, nombreRadio);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void eliminarTargetaRadio(String nombreRAN, String nombreENodeB, String nombreRadio, String slot) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){//si el proyecto es predeterminado
                 
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreRadio)){
                                        radios.get(l).elimiarInterfaceRadio(Integer.parseInt(slot));
                                        controlador.getControladorBotones().actualizarGestionTargetasDeEquipos(nombreRAN, nombreENodeB, nombreRadio);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void editarTargetaEquipoRadio(String nombreRAN, String nombreENodeB, String nombreRadio, int numeroSlotActual, int numeroSlotNuevo, 
            String fabricante, String tecnologia, String tipoTargeta, String modulacion, String tecnologiaMultiplexacion, 
            double potenciaMax, double sensibilidad, String duplexMode, String bandaFrecuencia, double frecuenciaInferiorUpLink, 
            double frecuenciaSuperiorUpLink, double anchoBandaUpLink, double frecuenciaInferiorDownLink, double frecuenciaSuperiorDownLink, 
            double anchoBandaDownLink) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 //si el proyecto es predeterminado
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 LinkedList <Radio> radios= listaNodos.get(k).getRadios();
                                 for(int l=0; l < radios.size(); l++){
                                    if(radios.get(l).getNombre().equals(nombreRadio)){
                                        
                                        radios.get(l).elimiarInterfaceRadio(numeroSlotActual);
                                        
                                        radios.get(l).editarInterfaceRadio(numeroSlotNuevo, fabricante, tecnologia, tipoTargeta, modulacion, tecnologiaMultiplexacion, potenciaMax, sensibilidad, 
                                                duplexMode, bandaFrecuencia, frecuenciaInferiorUpLink, frecuenciaSuperiorUpLink, anchoBandaUpLink, frecuenciaInferiorDownLink, 
                                                frecuenciaSuperiorDownLink, anchoBandaDownLink);
                                        
                                        controlador.getControladorBotones().actualizarGestionTargetasDeEquipos(nombreRAN, nombreENodeB, nombreRadio);
                                    }
                                 }
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void crearVentanaNuevoAtenuador(ControladorGeneral controlador, String nombreRAN, String nombreENodeB) {
        VentanaCrearAtenuador ventanaCrearAtenuador = new VentanaCrearAtenuador(new JFrame(), true, controlador, nombreRAN, nombreENodeB);
        CentrarScreen.centrarVentana(ventanaCrearAtenuador);
        ventanaCrearAtenuador.setVisible(true);
    }

    public void crearNuevoElementoAtenuacion(String nombreAtenuador, String tipoAtenuador, double atenuacion, String nombreRAN, String nombreENodeB) {
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 listaNodos.get(k).crearElementoAtenuacion(nombreAtenuador, tipoAtenuador, atenuacion);
                                 controlador.getControladorBotones().actualizarGestionElementosAtenuacion(nombreRAN, nombreENodeB);
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }
    
    public void crearVentanaNuevoIndoorLink(ControladorGeneral controlador, String nombreRAN, String nombreENodeB) {
        VentanaCrearIndoorLink ventanaCrearIndoorLink = new VentanaCrearIndoorLink(new JFrame(), true, controlador, nombreRAN, nombreENodeB);
        CentrarScreen.centrarVentana(ventanaCrearIndoorLink);
        ventanaCrearIndoorLink.setVisible(true);
    }

    public void crearNuevoIndoorLink(String nombreIndoorLink, String equipoRadio, int slotTarjeta, String nombreAntena, String[] nombresAtenuadores, String nombreRAN, String nombreENodeB) {
        // crearIndoorLink...
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
             if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                 for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                     if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                         LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                         for(int k=0; k < listaNodos.size(); k++){
                             if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                 
                                 TarjetaRadio targetaEquipoRadio = null;
                                 Antena antenaLink = null;
                                 ElementoAtenuacion[] listaAtenuadores = new ElementoAtenuacion[nombresAtenuadores.length];
                                 
                                 for(int l=0; l<listaNodos.get(k).getRadios().size(); l++){
                                     if(listaNodos.get(k).getRadios().get(l).getNombre().equals(equipoRadio)){
                                         TarjetaRadio[] tarjetas = listaNodos.get(k).getRadios().get(l).getInterfacesRadio();
                                         for(int m=0; m<tarjetas.length; m++){
                                                try{
                                                     if(tarjetas[m].getNumeroSlot() == slotTarjeta){
                                                         targetaEquipoRadio = tarjetas[m];
                                                     }
                                                }catch(NullPointerException NPE){}
                                         }
                                     }
                                 }
                                 for(int l=0; l<listaNodos.get(k).getAntenas().size(); l++){
                                     if(listaNodos.get(k).getAntenas().get(l).getNombreAntena().equals(nombreAntena)){
                                         antenaLink = listaNodos.get(k).getAntenas().get(l);
                                     }
                                 }
                                 for(int l=0; l<listaNodos.get(k).getAtenuadores().size(); l++){
                                     for(int m=0; m<nombresAtenuadores.length; m++){
                                         if(listaNodos.get(k).getAtenuadores().get(l).getNombreDispositivo().equals(nombresAtenuadores[m])){
                                             listaAtenuadores[m] = listaNodos.get(k).getAtenuadores().get(l);
                                         }
                                     }
                                 }
                                 
                                 listaNodos.get(k).crearIndoorLink(nombreIndoorLink, targetaEquipoRadio, antenaLink, listaAtenuadores);
                                 controlador.getControladorBotones().actualizarGestionIndoorLink(nombreRAN, nombreENodeB);
                             }
                         }
                     }
                 }
             }
        }
        actualizarArbolPrincipal();
    }

    public void crearNuevoOutdoorLink( String nombreOutdoorLink, String tipoEnlace, String tecnologiaEnlace, 
            String nombreEquipoUsuario, String nombreRAN, String nombreENodeB, String nombreIndoorLink ) {
        
            if(tecnologiaEnlace.equals("LTE Advanced (Release 10)")||tecnologiaEnlace.equals("LTE Advanced (Release 9)")){
                LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
                String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                EquipoUsuario equipoUsuario= null;  
                
                for(int i=0; i < proyectos.size(); i++){
                        //si el proyecto es predeterminado
                         if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                             for(int j=0; j < proyectos.get(i).getGrupoEUs().size(); j++){
                                 if(proyectos.get(i).getGrupoEUs().get(j).getNombreEstacionMovil().equals(nombreEquipoUsuario)){
                                     equipoUsuario = proyectos.get(i).getGrupoEUs().get(j);
                                 }
                             }
                             for(int j=0; j < proyectos.get(i).getGrupoRANs().size(); j++){
                                 if(proyectos.get(i).getGrupoRANs().get(j).getNombre().equals(nombreRAN)){
                                     LinkedList <ENodeB> listaNodos = proyectos.get(i).getGrupoRANs().get(j).getGrupoNodosB();
                                     for(int k=0; k < listaNodos.size(); k++){
                                         if(listaNodos.get(k).getNombre().equals(nombreENodeB)){
                                             for(int l = 0; l < listaNodos.get(k).getIndoorLinks().size();l++){
                                                 if(listaNodos.get(k).getIndoorLinks().get(l).getNombreLink().equals(nombreIndoorLink)){
                                                     proyectos.get(i).crearOutdoorLink(nombreOutdoorLink, tipoEnlace, tecnologiaEnlace, 
                                                             equipoUsuario, listaNodos.get(k), listaNodos.get(k).getIndoorLinks().get(l));
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
              }
            actualizarArbolPrincipal();
     }

    public void crearVentanaNuevoPath() {
               
    LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
    String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
    double distanciaEnlaceEnTierra  = 0;
       for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                for(int j=0; j < proyectos.get(i).getGrupoOutdoorLinks().size();j++){
                   OutdoorLinkRAN link =  (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado();
                   Sitio sitioEncontrado = buscarSitioDeNodeB(link.geteNodeBAsociado().getNombreSitioAsociado());
                   distanciaEnlaceEnTierra = MapaGeneral.calcularDistanciaEntreDosPuntos(sitioEncontrado.getCoordenadaSitio(), link.getEquipoUsuario().getCoordenadaEU());
                }
            }
            actualizarArbolPrincipal();
       }
       VentanaNuevoPathModeloITU ventanaCrearPath = new VentanaNuevoPathModeloITU(new JFrame(), true, controlador, distanciaEnlaceEnTierra);
       CentrarScreen.centrarVentana(ventanaCrearPath);
       ventanaCrearPath.setVisible(true);
    }

    public void crearNuevoPathModeloITU(CoordenadaGeografica coordenadaPuntoRompimiento, int numeroPath, String tipoEscenario, 
            String tipoPath, double alturaPromedioEdificios, double anchuraPromedioCalles, String distrubucionUrbanistica, 
            double temperaturaAmbiente, double anguloVectorUnitario, double minimaProbabilidadShadowing, String rangoShadowing, int cantidadGrupos
            ) {
        
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        Sitio sitioAsociado = null;
        
        for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
            OutdoorLinkRAN outdoorLinkPrederminado = (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado();
                
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                for(int j=0; j < proyectos.get(i).getGrupoSitios().size(); j++){
                     if(proyectos.get(i).getGrupoSitios().get(j).getNombreSitio().equals(outdoorLinkPrederminado.geteNodeBAsociado().getNombreSitioAsociado())){
                         sitioAsociado = proyectos.get(i).getGrupoSitios().get(j);
                     }
                 }
            outdoorLinkPrederminado.crearPath(
                    new PathModelo_ITU_R_M2135(sitioAsociado, outdoorLinkPrederminado.getEnlaceIndoorAsociado(), outdoorLinkPrederminado, coordenadaPuntoRompimiento, numeroPath, tipoEscenario, 
                tipoPath, alturaPromedioEdificios, anchuraPromedioCalles, distrubucionUrbanistica, temperaturaAmbiente, anguloVectorUnitario, minimaProbabilidadShadowing, rangoShadowing, cantidadGrupos));
            
            }
        }
        controlador.getControladorBotones().actualizarPathsConexionOutdoorPredeterminada();
        actualizarArbolPrincipal();
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
    
    public void obtenerProbabilidadLOSOutdoorLinkPredeterminado(String tipoEsenario, double distancia){
        
    LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
    String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
    
       for(int i=0; i < proyectos.size(); i++){
            //si el proyecto es predeterminado
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                for(int j=0; j < proyectos.get(i).getGrupoOutdoorLinks().size();j++){
                    OutdoorLinkRAN link =  (OutdoorLinkRAN)proyectos.get(i).getSimuladorPropagacion().getOutdoorLinkPredeterminado();
                    
                    double probabilidad = link.calcularProbabilidadLOS(tipoEsenario, distancia);
                    JOptionPane.showMessageDialog(null, "'La probabilidad de que la conexion sea LOS es de "+ probabilidad +"'",
                            "Calculo de Probabilidad de LOS",JOptionPane.INFORMATION_MESSAGE);
                }
            }
       }
    }
    
}//FIn de la Clase
