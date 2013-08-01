/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class LineasTX implements Serializable{
    
    private String nombreLineaTX;
    private String tipoLineaTX;
    private double longitud;
    private double factorAtenuacion;
    private String unidadFactorAtenuacion;
    private double frecuencia;
    
    public static String[] TIPOS_LINEAS_TX = {"Cable Coaxial", "Cable Bifilar", "Guia de Onda"};
    public static String[] UNIDAD_FACTOR_ATENUACION = {"[dB/m]", "[dB/100m]"};
    
    public LineasTX(String nombreLineaTX, String tipoLineaTX, double longitud, double factorAtenuacion, String unidadFactorAtenuacion, double frecuencia){
        
        this.nombreLineaTX = nombreLineaTX;
        this.tipoLineaTX = tipoLineaTX;
        this.longitud = longitud;
        this.factorAtenuacion = factorAtenuacion;
        this.unidadFactorAtenuacion = unidadFactorAtenuacion;
        this.frecuencia = frecuencia;
    }
    
    public void obtenerAtenuacionTotal(){
        
    }
}
