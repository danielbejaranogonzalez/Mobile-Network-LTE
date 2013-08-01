/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor;

import Modelo.SIM.Outdoor.Paths.PathModelo_ITU_R_M2135;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.RAN.IndoorLink;
import Modelo.SIM.Outdoor.OutdoorLink;
import Modelo.RAN.ENodeB;
import Modelo.SIM.Outdoor.Paths.Path;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author User
 */
public class OutdoorLinkRAN extends OutdoorLink implements Serializable{
    
    private String tecnologiaEnlace;
    private ENodeB eNodeBAsociado;
    private EquipoUsuario equipoUsuario;
    private IndoorLink enlaceIndoorAsociado;
    
    private LinkedList<PathModelo_ITU_R_M2135> listaPaths;
    
    public static String TECNOLOGIAS_OUTDOOR_LINK_RAN[] = {"LTE Advanced (Release 10)","LTE Advanced (Release 9)"};

    public OutdoorLinkRAN(String nombreOutdoorLink, String tipoEnlace, String tecnologiaEnlace, EquipoUsuario equipoUsuario, 
            ENodeB eNodeBAsociado, IndoorLink enlaceIndoorAsociado) {
        super(nombreOutdoorLink, tipoEnlace);
        
        this.tecnologiaEnlace = tecnologiaEnlace;
        this.equipoUsuario = equipoUsuario;
        this.eNodeBAsociado = eNodeBAsociado;
        this.enlaceIndoorAsociado = enlaceIndoorAsociado;
        
        listaPaths = new LinkedList<PathModelo_ITU_R_M2135>();
    }
    
    public void crearPath(PathModelo_ITU_R_M2135 nuevoPath){
        listaPaths.add(nuevoPath);
    }
    
    public double calcularProbabilidadLOS(String tipoEscenario, double distancia) {
        return ModeloITU_R_M2135.hallarProbabilidadDeLOS(tipoEscenario, distancia);
    }

    /**
     * @return the tecnologiaEnlace
     */
    public String getTecnologiaEnlace() {
        return tecnologiaEnlace;
    }

    /**
     * @param tecnologiaEnlace the tecnologiaEnlace to set
     */
    public void setTecnologiaEnlace(String tecnologiaEnlace) {
        this.tecnologiaEnlace = tecnologiaEnlace;
    }

    /**
     * @return the eNodeBAsociado
     */
    public ENodeB geteNodeBAsociado() {
        return eNodeBAsociado;
    }

    /**
     * @param eNodeBAsociado the eNodeBAsociado to set
     */
    public void seteNodeBAsociado(ENodeB eNodeBAsociado) {
        this.eNodeBAsociado = eNodeBAsociado;
    }

    /**
     * @return the equipoUsuario
     */
    public EquipoUsuario getEquipoUsuario() {
        return equipoUsuario;
    }

    /**
     * @param equipoUsuario the equipoUsuario to set
     */
    public void setEquipoUsuario(EquipoUsuario equipoUsuario) {
        this.equipoUsuario = equipoUsuario;
    }

    /**
     * @return the enlaceIndoorAsociado
     */
    public IndoorLink getEnlaceIndoorAsociado() {
        return enlaceIndoorAsociado;
    }

    /**
     * @param enlaceIndoorAsociado the enlaceIndoorAsociado to set
     */
    public void setEnlaceIndoorAsociado(IndoorLink enlaceIndoorAsociado) {
        this.enlaceIndoorAsociado = enlaceIndoorAsociado;
    }

    /**
     * @return the listaPaths
     */
    public LinkedList<PathModelo_ITU_R_M2135> getListaPaths() {
        return listaPaths;
    }

    
}
