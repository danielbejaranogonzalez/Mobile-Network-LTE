
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import Controlador.ControladorGeneral;
import Modelo.EPC.SystemArchitectureEvolution_LTE;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class RatioAccessNetwork_RAN extends SystemArchitectureEvolution_LTE implements Serializable
{
    private String nombre;
    private String tecnologia;
    
    private LinkedList <ENodeB> grupoNodosB;
    public static String TECNOLOGIAS[]= {"LTE Advanced (Release 10)","LTE Advanced (Release 9)"};

    /*
     * Constructor de la clase
     */
    public RatioAccessNetwork_RAN(String nombre, String tecnologia){
       this.nombre =  nombre;
       this.tecnologia = tecnologia;
       grupoNodosB = new LinkedList <ENodeB>();
    }
    
    public void crearNodeB(String nombre, double tipoTorre, String altura, String nombreSitioAsociado){
        getGrupoNodosB().add(new ENodeB(nombre, tipoTorre, altura, nombreSitioAsociado));
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
     * @return the tecnologia
     */
    public String getTecnologia() {
        return tecnologia;
    }
    
    /**
     * @param tecnologia the tecnologia to set
     */
    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }
    
    /**
     * @return the grupoNodosB
     */
    public LinkedList <ENodeB> getGrupoNodosB() {
        return grupoNodosB;
    }
    
    /**
     * @param grupoNodosB the grupoNodosB to set
     */
    public void setGrupoNodosB(LinkedList <ENodeB> grupoNodosB) {
        this.grupoNodosB = grupoNodosB;
    }
}
