/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.MOVIL;

import Modelo.GEO.CoordenadaGeografica;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class EquipoUsuario implements Serializable{

    /**
     * @param aTecnologias the tecnologias to set
     */
    public static void setTecnologias(String[] aTecnologias) {
        tecnologias = aTecnologias;
    }
    
    private String nombreEstacionMovil;
    private float altura;
    private String tipoEU;
    private float sensibilidad;
    private double potenciaMaxima;
    private String tecnologia;
    
    private CoordenadaGeografica coordenadaEU;
    
    private static String tiposEU[] = {"Telefono Movil", "PC", "Tablet", "Camara CCTV 4G"};
    private static String tecnologias[] = {"LTE Advanced (Release 10)", "LTE Advanced (Release 9)"};
    
    public EquipoUsuario(double latitud, double longitud, double elevacion, String nombreEstacionMovil, float altura, String tipoEU, float sensibilidad, String tecnlogia){
        
        coordenadaEU = new CoordenadaGeografica(latitud, longitud, elevacion);
        this.nombreEstacionMovil = nombreEstacionMovil;
        this.altura = altura;
        this.tipoEU = tipoEU;
        this.sensibilidad = sensibilidad;
        this.tecnologia = tecnlogia;
        potenciaMaxima = 21.0;
    }

    public EquipoUsuario(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion, String nombreEU, 
           float altura, String tipoEU, float sensibilidad, String tecnologia) {
        
        coordenadaEU = new CoordenadaGeografica(latitudGrados, latitudMinutos, latitudSegundos, hemisferioLatitud, 
                longitudGrados, longitudMinutos , longitudSegundos, hemisferioLongitud, elevacion);
        this.nombreEstacionMovil = nombreEU;
        this.altura = altura;
        this.tipoEU = tipoEU;
        this.sensibilidad = sensibilidad;
        this.tecnologia = tecnologia;
        potenciaMaxima = 21.0;
    }

    /**
     * @return the nombreEstacionMovil
     */
    public String getNombreEstacionMovil() {
        return nombreEstacionMovil;
    }

    /**
     * @param nombreEstacionMovil the nombreEstacionMovil to set
     */
    public void setNombreEstacionMovil(String nombreEstacionMovil) {
        this.nombreEstacionMovil = nombreEstacionMovil;
    }

    /**
     * @return the altura
     */
    public float getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }

    /**
     * @return the tipoEU
     */
    public String getTipoEU() {
        return tipoEU;
    }

    /**
     * @param tipoEU the tipoEU to set
     */
    public void setTipoEU(String tipoEU) {
        this.tipoEU = tipoEU;
    }

    /**
     * @return the sensibilidad
     */
    public float getSensibilidad() {
        return sensibilidad;
    }

    /**
     * @param sensibilidad the sensibilidad to set
     */
    public void setSensibilidad(float sensibilidad) {
        this.sensibilidad = sensibilidad;
    }

    /**
     * @return the tecnlogia
     */
    public String getTecnlogia() {
        return tecnologia;
    }

    /**
     * @param tecnlogia the tecnlogia to set
     */
    public void setTecnlogia(String tecnlogia) {
        this.tecnologia = tecnlogia;
    }

    /**
     * @return the coordenadaEU
     */
    public CoordenadaGeografica getCoordenadaEU() {
        return coordenadaEU;
    }

    /**
     * @param coordenadaEU the coordenadaEU to set
     */
    public void setCoordenadaEU(CoordenadaGeografica coordenadaEU) {
        this.coordenadaEU = coordenadaEU;
    }
    
    /**
     * @return the tiposEU
     */
    public static String[] getTiposEU() {
        return tiposEU;
    }

    /**
     * @param aTiposEU the tiposEU to set
     */
    public static void setTiposEU(String[] aTiposEU) {
        tiposEU = aTiposEU;
    }

    /**
     * @return the tecnologias
     */
    public static String[] getTecnologias() {
        return tecnologias;
    }


    /**
     * @return the potenciaMaxima
     */
    public double getPotenciaMaxima() {
        return potenciaMaxima;
    }
    
}
