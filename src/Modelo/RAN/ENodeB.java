/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import Modelo.PRO.Sitio;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class ENodeB implements Serializable{
    
    private String nombre;
    private String nombreSitioAsociado;
    
    private LinkedList <Antena> antenas;
    private LinkedList <ElementoAtenuacion> atenuadores;
    private LinkedList <Radio> radios;
    private LinkedList <IndoorLink> indoorLinks;
    private Torre torre;
   
    
    /*
     * Constructor de la clase ENodoB: crea la instanacia de las listas de objetos antenas, conectores,
     * lineasTX, radiosLTE
     */
    
    public ENodeB(String nombre, double tipoTorre, String altura, String nombreSitioAsociado){
        
        this.nombre = nombre;
        this.nombreSitioAsociado = nombreSitioAsociado;
        
        torre = new Torre(altura, tipoTorre);
        
        radios = new LinkedList();
        antenas = new LinkedList();
        atenuadores = new LinkedList();
        indoorLinks = new LinkedList();
    }
    
    /*
     * Crea una antena y la agrega a la lista de antenas del eNodoB
     */
    public void crearAntena(String nombreAntena, String tipoAntena, double gananciaMaxima, double anguloAzimut, double anguloElevacion,
             String polarizacion, double HPBW_Horizantal, double impedancia, double frecuenciaMaxima, double frecuenciaMinima, double alturaAntena){
        
        antenas.add( new Antena(nombreAntena, tipoAntena, gananciaMaxima, anguloAzimut, anguloElevacion,
             polarizacion, HPBW_Horizantal, impedancia, frecuenciaMaxima, frecuenciaMinima, alturaAntena) );
    }
    
    /*
     * Crea un radio y la agrega a la lista de radios del eNodoB
     */
    public void crearRadio(String nombre, String fabricante, boolean status){
        radios.add(new Radio(nombre, fabricante, status));
    }

    /*
     * Crea un elemento de atenuacion
     */
    public void crearElementoAtenuacion(String nombreDispositivo, String tipoAtenuador, double atenuacion){
        atenuadores.add(new ElementoAtenuacion(nombreDispositivo, tipoAtenuador, atenuacion));
    }
    
    /*
     * Crea un Indoor Link
     */
    public void crearIndoorLink(String nombreLink, TarjetaRadio targetaEquipoRadio, Antena antena, ElementoAtenuacion[] listaAtenuadores){
        getIndoorLinks().add(new IndoorLink( nombreLink, targetaEquipoRadio, antena, listaAtenuadores));
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
     * @return the antenas
     */
    public LinkedList <Antena> getAntenas() {
        return antenas;
    }

    /**
     * @return the radiosLTE
     */
    public LinkedList <Radio> getRadios() {
        return radios;
    }

    /**
     * @return the torre
     */
    public Torre getTorre() {
        return torre;
    }

    /**
     * @param torre the torre to set
     */
    public void setTorre(Torre torre) {
        this.torre = torre;
    }

    /**
     * @return the nombreSitioAsociado
     */
    public String getNombreSitioAsociado() {
        return nombreSitioAsociado;
    }

    /**
     * @param nombreSitioAsociado the nombreSitioAsociado to set
     */
    public void setNombreSitioAsociado(String nombreSitioAsociado) {
        this.nombreSitioAsociado = nombreSitioAsociado;
    }

    /**
     * @return the atenuadores
     */
    public LinkedList <ElementoAtenuacion> getAtenuadores() {
        return atenuadores;
    }

    /**
     * @return the indoorLinks
     */
    public LinkedList <IndoorLink> getIndoorLinks() {
        return indoorLinks;
    }
}
