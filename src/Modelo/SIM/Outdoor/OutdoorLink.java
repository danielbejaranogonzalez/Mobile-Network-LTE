/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class OutdoorLink implements Serializable{
    
    private String nombreEnlaceOutdoor;
    private String tipoEnlaceOutdoor;
    
    public static String TIPOS_OUTDOOR_LINK_REDMOVIL [] = {"Uu", "X2-C", "S1-MME", "S10", "S11", "SS/58"};
    
    public OutdoorLink(String nombreEnlaceOutdoor, String tipoEnlaceOutdoor){
        this.nombreEnlaceOutdoor = nombreEnlaceOutdoor;
        this.tipoEnlaceOutdoor = tipoEnlaceOutdoor;
    }

    /**
     * @return the nombreEnlaceOutdoor
     */
    public String getNombreEnlaceOutdoor() {
        return nombreEnlaceOutdoor;
    }

    /**
     * @param nombreEnlaceOutdoor the nombreEnlaceOutdoor to set
     */
    public void setNombreEnlaceOutdoor(String nombreEnlaceOutdoor) {
        this.nombreEnlaceOutdoor = nombreEnlaceOutdoor;
    }

    /**
     * @return the tipoEnlaceOutdoor
     */
    public String getTipoEnlaceOutdoor() {
        return tipoEnlaceOutdoor;
    }

    /**
     * @param tipoEnlaceOutdoor the tipoEnlaceOutdoor to set
     */
    public void setTipoEnlaceOutdoor(String tipoEnlaceOutdoor) {
        this.tipoEnlaceOutdoor = tipoEnlaceOutdoor;
    }

}
