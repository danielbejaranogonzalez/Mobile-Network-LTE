/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.PRO;

import Modelo.GEO.CoordenadaGeografica;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Sitio implements Serializable{
    
    private CoordenadaGeografica coordenadaSitio;
    private String nombreSitio;
    private String direccionSitio;
    private String ciudad;
    private String pais;
    private String tipoSitio;
    
    public static String[] tiposSitios = {"Estacion Base", "Armario de Conexiones Outdoor", "Central de Conmutacion", "Network Operations Center (NOC)"};
    
    /*
     * Crear Sitio con Coordenadas geograficas Decimales.
     */
    public Sitio(double latitud, double longitud, double elevacion, String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio){
        
        coordenadaSitio = new CoordenadaGeografica(latitud, longitud, elevacion);
        this.nombreSitio = nombreSitio;
        this.direccionSitio = direccionSitio;
        this.ciudad = ciudad;
        this.pais = pais;
        this.tipoSitio = tipoSitio;
    }
    
    /*
     * Crear Sitio con corrdenadas geograficas Grados, Minutos y Segundos.
     */
    public Sitio(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion,
           String nombreSitio, String direccionSitio, String pais, String ciudad, String tipoSitio){
        
        coordenadaSitio = new CoordenadaGeografica(latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud, 
        longitudGrados, longitudMinutos , longitudSegundos, hemisferioLongitud, elevacion);
        
        this.nombreSitio = nombreSitio;
        this.direccionSitio = direccionSitio;
        this.ciudad = ciudad;
        this.pais = pais;
        this.tipoSitio = tipoSitio;
    }

    /**
     * @return the coordenadaSitio
     */
    public CoordenadaGeografica getCoordenadaSitio() {
        return coordenadaSitio;
    }

    /**
     * @param coordenadaSitio the coordenadaSitio to set
     */
    public void setCoordenadaSitio(CoordenadaGeografica coordenadaSitio) {
        this.coordenadaSitio = coordenadaSitio;
    }

    /**
     * @return the nombreSitio
     */
    public String getNombreSitio() {
        return nombreSitio;
    }

    /**
     * @param nombreSitio the nombreSitio to set
     */
    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    /**
     * @return the direccionSitio
     */
    public String getDireccionSitio() {
        return direccionSitio;
    }

    /**
     * @param direccionSitio the direccionSitio to set
     */
    public void setDireccionSitio(String direccionSitio) {
        this.direccionSitio = direccionSitio;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the tipoSitio
     */
    public String getTipoSitio() {
        return tipoSitio;
    }

    /**
     * @param tipoSitio the tipoSitio to set
     */
    public void setTipoSitio(String tipoSitio) {
        this.tipoSitio = tipoSitio;
    }
}
