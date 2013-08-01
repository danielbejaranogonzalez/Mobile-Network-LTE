/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import Modelo.RAN.Antena;
import Modelo.RAN.ENodeB;
import Modelo.PRO.Sitio;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class IndoorLink implements Serializable{
    
    private String nombreLink;
    private TarjetaRadio targetaEquipoRadio;
    private Antena antena;
    private ElementoAtenuacion[] listaAtenuadores;
    
    public IndoorLink(String nombreLink, TarjetaRadio targetaEquipoRadio, Antena antena, ElementoAtenuacion[] listaAtenuadores){
        
        this.nombreLink = nombreLink;
        this.targetaEquipoRadio = targetaEquipoRadio;
        this.antena = antena;
        this.listaAtenuadores = listaAtenuadores;
    }
    
    public double obtenerPerdidasTotalesLink(){
        double perdidasTotalesLink = 0;
        double perdidasPorAtenuadores = 0;
        for(int i=0; i < getListaAtenuadores().length; i++){
            perdidasPorAtenuadores = perdidasPorAtenuadores - getListaAtenuadores()[i].getAtenuacion();
        }
        perdidasTotalesLink = getAntena().getGananciaMaxima() - perdidasPorAtenuadores;
        
        return perdidasTotalesLink;
    }

    /**
     * @return the nombreLink
     */
    public String getNombreLink() {
        return nombreLink;
    }

    /**
     * @param nombreLink the nombreLink to set
     */
    public void setNombreLink(String nombreLink) {
        this.nombreLink = nombreLink;
    }

    /**
     * @return the targetaEquipoRadio
     */
    public TarjetaRadio getTargetaEquipoRadio() {
        return targetaEquipoRadio;
    }

    /**
     * @param targetaEquipoRadio the targetaEquipoRadio to set
     */
    public void setTargetaEquipoRadio(TarjetaRadio targetaEquipoRadio) {
        this.targetaEquipoRadio = targetaEquipoRadio;
    }

    /**
     * @return the antena
     */
    public Antena getAntena() {
        return antena;
    }

    /**
     * @param antena the antena to set
     */
    public void setAntena(Antena antena) {
        this.antena = antena;
    }

    /**
     * @return the listaAtenuadores
     */
    public ElementoAtenuacion[] getListaAtenuadores() {
        return listaAtenuadores;
    }

    /**
     * @param listaAtenuadores the listaAtenuadores to set
     */
    public void setListaAtenuadores(ElementoAtenuacion[] listaAtenuadores) {
        this.listaAtenuadores = listaAtenuadores;
    }

}
