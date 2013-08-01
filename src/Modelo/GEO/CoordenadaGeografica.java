/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.GEO;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class CoordenadaGeografica implements Serializable
{

    private double latitudDecimal;
    private double longitudDecimal;
    private double elevacion;
    
    private int latitudGrados;
    private int latitudMinutos;
    private float latitudSegundos;
    private char hemisferioLatitud;
    
    private int longitudGrados;
    private int longitudMinutos;
    private float longitudSegundos;
    private char hemisferioLongitud;
    
    private String latitudGradosMinutosSegundos;
    private String longitudGradosMinutosSegundos;

    public CoordenadaGeografica(double latitud, double longitud, double elevacion) {
        this.latitudDecimal = latitud;
        this.longitudDecimal = longitud;
        this.elevacion = elevacion;
        
        convertirLong_A_GradMinSeg();
        convertirLat_A_GradMinSeg();
    }
    /*
    public CoordenadaGeografica(double latitud, double longitud) {
        this.latitudDecimal = latitud;
        this.longitudDecimal = longitud;
        this.elevacion = 0;
        
        convertirLong_A_GradMinSeg();
        convertirLat_A_GradMinSeg();
    }
     */
    
    public CoordenadaGeografica(int latitudGrados, int latitudMinutos, float latitudSegundos, char hemisferioLatitud,
           int longitudGrados, int longitudMinutos, float longitudSegundos, char hemisferioLongitud, double elevacion) {
        this.latitudGrados = latitudGrados;
        this.latitudMinutos = latitudMinutos;
        this.latitudSegundos = latitudSegundos;
        this.hemisferioLatitud = hemisferioLatitud;
        
        this.longitudGrados = longitudGrados;
        this.longitudMinutos = longitudMinutos;
        this.longitudSegundos = longitudSegundos;
        this.hemisferioLongitud = hemisferioLongitud;
        
        this.elevacion = elevacion;
        
        convertirLat_A_Decimal();
        convertirLong_A_Decimal();
    }
    
    /*
     * Halla el factor de correccion de zoom para las baldosas
     */
    public static double hallarFactorCorreccionZoom(int zoom){
        /* Otra forma mas sencilla
        double factor = 0.0000053644;
        factor = factor *(Math.exp(0.693*zoom));
        return factor;
        */
        double factor = 0.000005954;
        if(zoom != 0)
        {
            for(int i=0; i < zoom; i++){
                factor = factor*2;
            }
        }
        return factor;
    }

    public void convertirLat_A_GradMinSeg() {
        // Grados
        latitudGrados = (int) getLatitudDecimal();
        latitudGrados = Math.abs(latitudGrados);
        //Minutos
        double latitudMinutosTemp;
        latitudMinutosTemp = (Math.abs(latitudDecimal) - getLatitudGrados()) * 60;
        latitudMinutos = (int) latitudMinutosTemp;
        latitudMinutos = Math.abs(latitudMinutos);

        //Segundos
        setLatitudSegundos((float)((latitudMinutosTemp - getLatitudMinutos()) * 60));
        
        
        // Hemisferio Latitud
        String latitudDecimalString = getLatitudDecimal() + "";
        if (latitudDecimalString.charAt(0) == '-') {
            setHemisferioLatitud('S');
        } else {
            setHemisferioLatitud('N');
        }
    }

    public void convertirLong_A_GradMinSeg() {

        //Grados
        longitudGrados = (int) longitudDecimal;
        longitudGrados = Math.abs(longitudGrados);
        //Minutos
        double longitudMinutosTemp;
        longitudMinutosTemp = (Math.abs(longitudDecimal) - longitudGrados) * 60;
        longitudMinutos = (int) longitudMinutosTemp;
        
        longitudMinutos = Math.abs(longitudMinutos);
        //Segundos
        setLongitudSegundos((float)((longitudMinutosTemp - longitudMinutos) * 60));
        
        
        String longitudDecimalString = getLongitudDecimal() + "";
        if (longitudDecimalString.charAt(0) == '-') {
            
            // Hemisferio Logitud
            setHemisferioLongitud('O');
        } else {
            
            // Hemisferio Logitud
            setHemisferioLongitud('E');
        }
    }
    
    public void convertirLat_A_Decimal(){
        
        //System.out.println("TEST Hemisferio LAt: " + hemisferioLatitud + " Long: " + hemisferioLongitud);   
        if(hemisferioLatitud == 'N'){
            latitudDecimal = ((double)latitudGrados)+(((double)latitudMinutos)/60)+((((double)latitudSegundos)/60)/60);
        }else if(hemisferioLatitud == 'S'){
            latitudDecimal = (-1.0)*((double)latitudGrados)+(((double)latitudMinutos)/60)+((((double)latitudSegundos)/60)/60);
        }
    }
    
    public void convertirLong_A_Decimal(){
        
        if(hemisferioLongitud == 'O'){
            longitudDecimal =  (-1.0)*(((double)longitudGrados) + (((double)longitudMinutos)/60)+((((double)longitudSegundos)/60)/60));
        }else if(hemisferioLongitud == 'E'){
            longitudDecimal = (((double)longitudGrados)+(((double)longitudMinutos)/60)+((((double)longitudSegundos)/60)/60));
        }
    }

    /**
     * @return the elevacion
     */
    public double getElevacion() {
        return elevacion;
    }

    /**
     * @param elevacion the elevacion to set
     */
    public void setElevacion(double elevacion) {
        this.elevacion = elevacion;
    }

    /**
     * @return the latitudDecimal
     */
    public double getLatitudDecimal() {
        return latitudDecimal;
    }

    /**
     * @param latitudDecimal the latitudDecimal to set
     */
    public void setLatitudDecimal(double latitudDecimal) {
        this.latitudDecimal = latitudDecimal;
    }

    /**
     * @return the longitudDecimal
     */
    public double getLongitudDecimal() {
        return longitudDecimal;
    }

    /**
     * @param longitudDecimal the longitudDecimal to set
     */
    public void setLongitudDecimal(double longitudDecimal) {
        this.longitudDecimal = longitudDecimal;
    }

    /**
     * @return the latitudGrados
     */
    public int getLatitudGrados() {
        return latitudGrados;
    }

    /**
     * @param latitudGrados the latitudGrados to set
     */
    public void setLatitudGrados(int latitudGrados) {
        this.latitudGrados = latitudGrados;
    }

    /**
     * @return the latitudMinutos
     */
    public int getLatitudMinutos() {
        return latitudMinutos;
    }

    /**
     * @param latitudMinutos the latitudMinutos to set
     */
    public void setLatitudMinutos(int latitudMinutos) {
        this.latitudMinutos = latitudMinutos;
    }

    /**
     * @return the latitudSegundos
     */
    public double getLatitudSegundos() {
        return latitudSegundos;
    }

    /**
     * @param latitudSegundos the latitudSegundos to set
     */
    public void setLatitudSegundos(float latitudSegundos) {
        this.latitudSegundos = latitudSegundos;
    }

    /**
     * @return the hemisferioLatitud
     */
    public char getHemisferioLatitud() {
        return hemisferioLatitud;
    }

    /**
     * @param hemisferioLatitud the hemisferioLatitud to set
     */
    public void setHemisferioLatitud(char hemisferioLatitud) {
        this.hemisferioLatitud = hemisferioLatitud;
    }

    /**
     * @return the hemisferioLongitud
     */
    public char getHemisferioLongitud() {
        return hemisferioLongitud;
    }

    /**
     * @param hemisferioLongitud the hemisferioLongitud to set
     */
    public void setHemisferioLongitud(char hemisferioLongitud) {
        this.hemisferioLongitud = hemisferioLongitud;
    }

    /**
     * @return the latitudGradosMinutosSegundos
     */
    public String getLatitudGradosMinutosSegundos() {
        return latitudGradosMinutosSegundos;
    }

    /**
     * @return the longitudGradosMinutosSegundos
     */
    public String getLongitudGradosMinutosSegundos() {
        return longitudGradosMinutosSegundos;
    }

    /**
     * @return the longitudGrados
     */
    public int getLongitudGrados() {
        return longitudGrados;
    }

    /**
     * @param longitudGrados the longitudGrados to set
     */
    public void setLongitudGrados(int longitudGrados) {
        this.longitudGrados = longitudGrados;
    }

    /**
     * @return the longitudMinutos
     */
    public int getLongitudMinutos() {
        return longitudMinutos;
    }

    /**
     * @param longitudMinutos the longitudMinutos to set
     */
    public void setLongitudMinutos(int longitudMinutos) {
        this.longitudMinutos = longitudMinutos;
    }

    /**
     * @return the longitudSegundos
     */
    public double getLongitudSegundos() {
        return longitudSegundos;
    }

    /**
     * @param longitudSegundos the longitudSegundos to set
     */
    public void setLongitudSegundos(float longitudSegundos) {
        this.longitudSegundos = longitudSegundos;
    }
}
