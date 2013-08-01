/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Torre implements Serializable{

    /**
     * @return the tiposTorres
     */
    public static String[] getTiposTorres() {
        return tiposTorres;
    }

    /**
     * @param aTiposTorres the tiposTorres to set
     */
    public static void setTiposTorres(String[] aTiposTorres) {
        tiposTorres = aTiposTorres;
    }

    private String tipoTorre;
    private double altura;
    private static String[] tiposTorres = {"Torre Arriostrada","Torre Autosoportada","Torre Monopolo", "Mástil"};
    
    public Torre(String tipoTorre, double altura){
         this.altura = altura;
         this.tipoTorre = tipoTorre;
    }

    /**
     * @return the tipoTorre
     */
    public String getTipoTorre() {
        return tipoTorre;
    }

    /**
     * @param tipoTorre the tipoTorre to set
     */
    public void setTipoTorre(String tipoTorre) {
        this.tipoTorre = tipoTorre;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

}
