/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.PRO;

import Controlador.ControladorGeneral;
import Modelo.EPC.EnvolvedPacketCore;
import Modelo.RAN.RatioAccessNetwork_RAN;
import Modelo.EPC.ServingGateway_SWG;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.RAN.ENodeB;
import Modelo.RAN.IndoorLink;
import Modelo.SIM.Outdoor.OutdoorLink;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import Modelo.SIM.Outdoor.SimuladorPropagacion;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Proyecto implements Serializable{
    
    private String nombre;
    private String tipoProyecto;
    private boolean proyectoPredeterminado;
    
    private LinkedList<Sitio> grupoSitios;
    private LinkedList<EquipoUsuario> grupoEUs;
    private LinkedList<RatioAccessNetwork_RAN> grupoRANs;
    private LinkedList<OutdoorLink> grupoOutdoorLinks;
    private EnvolvedPacketCore epc;
    private SimuladorPropagacion simuladorPropagacion;
    private String URLProyecto;

    
    public Proyecto(String nombre, String tipoProyecto, boolean proyectoPredeterminado){
         
         this.setNombre(nombre);
         this.setTipoProyecto(tipoProyecto);
         this.proyectoPredeterminado = proyectoPredeterminado;
         
         grupoSitios = new LinkedList<Sitio> ();
         grupoEUs = new LinkedList<EquipoUsuario>();
         grupoRANs = new LinkedList<RatioAccessNetwork_RAN>();
         grupoOutdoorLinks = new LinkedList<OutdoorLink>();
         
         simuladorPropagacion = new SimuladorPropagacion();
         epc = new EnvolvedPacketCore();
         URLProyecto = "";
    }

    /*
     * Crea un nuevo Sitio 
     */
    public void crearSitio(double latitud, double longitud, double elevacion, String nombre, String direccionSitio, String pais, String ciudad, String tipoSitio)
    {
        grupoSitios.add( new Sitio(latitud,longitud,elevacion, nombre, direccionSitio, pais, ciudad, tipoSitio));
    }
    
    public void crearSitio(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion,
           String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio) {
        
        grupoSitios.add( new Sitio( latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud,
                longitudGrados, longitudMinutos, longitudSegundos, hemisferioLongitud, elevacion, nombreSitio, direccionSitio, pais, ciudad, tipoSitio));
    }
    
    /*
     * crea una nueva red RAN LTE
     */
    public void crearRAN(String nombre, String tecnologia)
    {
        getGrupoRANs().add(new RatioAccessNetwork_RAN(nombre, tecnologia));
    }
    
    /*
     * crea una nueva red OutdoorLink
     */
    public void crearOutdoorLink(String nombreOutdoorLink, String tipoEnlace, String tecnologiaEnlace, EquipoUsuario equipoUsuario,
            ENodeB eNodeBAsociado, IndoorLink enlaceIndoorAsociado){
        getGrupoOutdoorLinks().add(new OutdoorLinkRAN(nombreOutdoorLink, tipoEnlace, tecnologiaEnlace, equipoUsuario, eNodeBAsociado, enlaceIndoorAsociado));
    }
    
    /*
     * crea una nueva red Equipo Usuario
     */
    public void crearEquipoUsuario(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion, String nombreEU, 
           float altura, String tipoEU, float sensibilidad, String tecnologia){
         getGrupoEUs().add(new EquipoUsuario(latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud, longitudGrados,
                 longitudMinutos, longitudSegundos, hemisferioLongitud, elevacion, nombreEU, altura, tipoEU, sensibilidad, tecnologia));
    }
        
    public void crearEquipoUsuario(double latGradosDecimal, double longGradosDecimal, double elevacion, String nombreEU, float altura, String tipoEU, float sensibilidad, String tecnologia) {
        getGrupoEUs().add(new EquipoUsuario(latGradosDecimal, longGradosDecimal, elevacion, nombreEU, altura, tipoEU, sensibilidad, tecnologia));
    }


    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipoProyecto
     */
    public String getTipoProyecto() {
        return tipoProyecto;
    }

    /**
     * @param tipoProyecto the tipoProyecto to set
     */
    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    /**
     * @return the grupoSitios
     */
    public LinkedList<Sitio> getGrupoSitios() {
        return grupoSitios;
    }

    /**
     * @return the grupoEUs
     */
    public LinkedList<EquipoUsuario> getGrupoEUs() {
        return grupoEUs;
    }
    
    /**
     * @return the grupoRANs
     */
    public LinkedList<RatioAccessNetwork_RAN> getGrupoRANs() {
        return grupoRANs;
    }

    /**
     * @return the epc
     */
    public EnvolvedPacketCore getEpc() {
        return epc;
    }

    /**
     * @return the isProyectoPredeterminado
     */
    public boolean isProyectoPredeterminado() {
        return proyectoPredeterminado;
    }

    /**
     * @param isProyectoPredeterminado the isProyectoPredeterminado to set
     */
    public void setProyectoPredeterminado(boolean isProyectoPredeterminado) {
        this.proyectoPredeterminado = isProyectoPredeterminado;
    }
    /**
     * @return the grupoOutdoorLinks
     */
    public LinkedList<OutdoorLink> getGrupoOutdoorLinks() {
        return grupoOutdoorLinks;
    }

    /**
     * @return the simuladorPropagacion
     */
    public SimuladorPropagacion getSimuladorPropagacion() {
        return simuladorPropagacion;
    }

    /**
     * @return the URLProyecto
     */
    public String getURLProyecto() {
        return URLProyecto;
    }

    /**
     * @param URLProyecto the URLProyecto to set
     */
    public void setURLProyecto(String URLProyecto) {
        this.URLProyecto = URLProyecto;
    }
}