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
public class ElementoAtenuacion implements Serializable{
    
    private String nombreDispositivo;
    private String tipoAtenuador;
    private double atenuacion;
    private double frecuencia;
    
    public static String[] TIPOS_ATENUADORES = {"Linea de Transmision", "Conector", "Spliter", "Duplexor", "Latiguillo", "Atenuador"};
        
    public ElementoAtenuacion(String nombreDispositivo, String tipoAtenuador, double atenuacion){
        this.nombreDispositivo = nombreDispositivo;
        this.tipoAtenuador = tipoAtenuador;
        this.atenuacion = atenuacion;
        
    }

    /**
     * @return the nombreDispositivo
     */
    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    /**
     * @param nombreDispositivo the nombreDispositivo to set
     */
    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    /**
     * @return the tipoAtenuador
     */
    public String getTipoAtenuador() {
        return tipoAtenuador;
    }

    /**
     * @param tipoAtenuador the tipoAtenuador to set
     */
    public void setTipoAtenuador(String tipoAtenuador) {
        this.tipoAtenuador = tipoAtenuador;
    }

    /**
     * @return the atenuacion
     */
    public double getAtenuacion() {
        return atenuacion;
    }

    /**
     * @param atenuacion the atenuacion to set
     */
    public void setAtenuacion(double atenuacion) {
        this.atenuacion = atenuacion;
    }

    /**
     * @return the frecuencia
     */
    public double getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }
    
}