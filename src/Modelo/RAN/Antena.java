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
public class Antena implements Serializable{
    
    private static String[] TIPOS_ANTENAS = {"Antena Sectorial", "Antena Omnidireccional"};
    private static String[] TIPOS_POLARIZACIONES = {"Horizontal", "Vertical", "Dual"};
    
    private String nombreAntena;
    private String tipoAntena;
    private double gananciaMaxima;
    private double anguloAzimut;
    private double anguloElevacion;
    
    private String polarizacion;
    private double HPBW_Horizantal;
    private double impedancia;
    
    private double frecuenciaMaxima;
    private double frecuenciaMinima;
    private double HPBW_Vertical;
    private double alturaAntena;
    
    private double PARAMETRO_K[] = {1.5, 0.7, 0.4, 0.6, 0.2, 0.0};
    
    public Antena(String nombreAntena, String tipoAntena, double gananciaMaxima, double anguloAzimut, double anguloElevacion,
             String polarizacion, double HPBW_Horizantal, double impedancia, double frecuenciaMaxima, double frecuenciaMinima, double altura){
        
        this.nombreAntena = nombreAntena;
        this.tipoAntena = tipoAntena;
        this.gananciaMaxima = gananciaMaxima;
        this.anguloAzimut = anguloAzimut;
        this.anguloElevacion = anguloElevacion;
        this.polarizacion = polarizacion;
        this.HPBW_Horizantal = HPBW_Horizantal;
        this.impedancia = impedancia;
        this.frecuenciaMaxima = frecuenciaMaxima;
        this.frecuenciaMinima = frecuenciaMinima;
        this.alturaAntena = altura;
        CalcularHPBW_Vertical();
    }
    
    /*
     * METODOS FUNCIONALES
     */
    public double obtenerGananciaInstantaneaAntenaSectorial(double phi, double theta){
        /*
         * Calculos segun Recomendacion UIT-R F.1336-2
         * Diagramas de radiación de referencia de antenas omnidireccionales, sectoriales
         * y otros tipos de antenas de sistemas de punto a multipunto para su utilización en
         * estudios de compartición en la gama de frecuencias de 1 GHz a aproximadamente 70 GHz
         */
        double alfa = 0;
        double psiSubAlfa, psi = 0;
        double x;
        double Gref=0;
        double lamdaK, xK = 0;
        double frecuenciaCentral;
        
        if((HPBW_Horizantal >=0 && HPBW_Horizantal <= 120)&&(phi >=-180 && phi <=180) && (theta >= -90 && theta <= 90)){
            if(tipoAntena.equals("Antena Sectorial")){
                if(polarizacion.equals("Horizontal")||polarizacion.equals("Vertical")||polarizacion.equals("Dual")){
                    if(polarizacion.equals("Vertical")){
                            double tempTheta;
                            tempTheta = theta;
                            theta = phi; phi = tempTheta;
                    }
                    
                    theta = Math.toRadians(theta);
                    phi = Math.toRadians(phi);
                    
                    CalcularHPBW_Vertical();
                    alfa = Math.atan((Math.atan(Math.abs(theta))/(Math.sin(phi))));
                    
                    psiSubAlfa = 1/(Math.sqrt((Math.pow((Math.cos(alfa)/HPBW_Horizantal),2))+(Math.pow((Math.sin(alfa)/HPBW_Vertical),2))));
                    
                    psi = Math.toDegrees(Math.acos((Math.cos(phi))*(Math.cos(Math.abs(theta)))));
                    x = psi/psiSubAlfa;
                    
                    lamdaK = 12 - (10*Math.log10(1+(8*PARAMETRO_K[5])));
                    xK = Math.sqrt(1-(0.36*PARAMETRO_K[5]));
                    
                    frecuenciaCentral = ((frecuenciaMaxima-frecuenciaMinima)/2)+frecuenciaMinima;
                    // si esta entre el 0.8 GHz y 6 GHz
                    if(frecuenciaCentral >= 800 && frecuenciaCentral <= 6000){
                        if(x >= 0 && x < xK){
                            Gref = gananciaMaxima - (12*(Math.pow(x, 2)));
                        }else if(x >= xK && x < 4){
                            Gref = gananciaMaxima - 12 + (10*Math.log10((Math.pow(x, -1.5)) + PARAMETRO_K[5]));
                        }else if(x >= 4){
                            Gref = gananciaMaxima - lamdaK - (15*(Math.log10(x)));
                        }
                    }else if(frecuenciaCentral > 6000 && frecuenciaCentral <= 70000){
                        // si esta entre el 6 GHz y 70 GHz
                        if(x >= 0 && x < 1){
                            Gref = gananciaMaxima - (12*(Math.pow(x, 2)));
                        }else if(x >= 1){
                            Gref = gananciaMaxima - 12 - (15*(Math.log10(x)));
                        }
                    }
                    else{
                        Gref = 0;
                    }
                }
            }if(theta == 0 && phi == 0){
                Gref = gananciaMaxima;
            }
        }
        
        return Math.abs(Gref);
    }
    
    public void CalcularHPBW_Vertical(){
        HPBW_Vertical =(31000*(Math.pow(10, (-0.1*gananciaMaxima)))) / HPBW_Horizantal;
    }
    /*
    public static void main(String[] args) {
        Antena antena = new Antena("Test", "Antena Sectorial", 17.0 , 80, 30, "Horizontal", 65, 50, 1824, 1896,20);
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-180, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-160, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-140, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-120, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-100, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-80, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-60, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-40, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-20, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(-10, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(0, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(10, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(20, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(40, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(60, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(80, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(100, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(120, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(140, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(160, 0));
        System.out.println("Test Ecuacion: " + antena.obtenerGananciaInstantaneaAntenaSectorial(180, 0));
    }
    /*
    
    /*
     * METODOS GET Y SET
     * @return the nombreAntena
     */
    public String getNombreAntena() {
        return nombreAntena;
    }

    /**
     * @param nombreAntena the nombreAntena to set
     */
    public void setNombreAntena(String nombreAntena) {
        this.nombreAntena = nombreAntena;
    }

    /**
     * @return the tipoAntena
     */
    public String getTipoAntena() {
        return tipoAntena;
    }

    /**
     * @param tipoAntena the tipoAntena to set
     */
    public void setTipoAntena(String tipoAntena) {
        this.tipoAntena = tipoAntena;
    }

    /**
     * @return the gananciaMaxima
     */
    public double getGananciaMaxima() {
        return gananciaMaxima;
    }

    /**
     * @param gananciaMaxima the gananciaMaxima to set
     */
    public void setGananciaMaxima(double gananciaMaxima) {
        this.gananciaMaxima = gananciaMaxima;
    }

    /**
     * @return the anguloAzimut
     */
    public double getAnguloAzimut() {
        return anguloAzimut;
    }

    /**
     * @param anguloAzimut the anguloAzimut to set
     */
    public void setAnguloAzimut(double anguloAzimut) {
        this.anguloAzimut = anguloAzimut;
    }

    /**
     * @return the anguloElevacion
     */
    public double getAnguloElevacion() {
        return anguloElevacion;
    }

    /**
     * @param anguloElevacion the anguloElevacion to set
     */
    public void setAnguloElevacion(double anguloElevacion) {
        this.anguloElevacion = anguloElevacion;
    }

    /**
     * @return the polarizacion
     */
    public String getPolarizacion() {
        return polarizacion;
    }

    /**
     * @param polarizacion the polarizacion to set
     */
    public void setPolarizacion(String polarizacion) {
        this.polarizacion = polarizacion;
    }

    /**
     * @return the impedancia
     */
    public double getImpedancia() {
        return impedancia;
    }

    /**
     * @param impedancia the impedancia to set
     */
    public void setImpedancia(double impedancia) {
        this.impedancia = impedancia;
    }

    /**
     * @return the frecuenciaMaxima
     */
    public double getFrecuenciaMaxima() {
        return frecuenciaMaxima;
    }

    /**
     * @param frecuenciaMaxima the frecuenciaMaxima to set
     */
    public void setFrecuenciaMaxima(double frecuenciaMaxima) {
        this.frecuenciaMaxima = frecuenciaMaxima;
    }

    /**
     * @return the frecuenciaMinima
     */
    public double getFrecuenciaMinima() {
        return frecuenciaMinima;
    }

    /**
     * @param frecuenciaMinima the frecuenciaMinima to set
     */
    public void setFrecuenciaMinima(double frecuenciaMinima) {
        this.frecuenciaMinima = frecuenciaMinima;
    }
    
    /**
     * @return the TIPOS_ANTENAS
     */
    public static String[] getTIPOS_ANTENAS() {
        return TIPOS_ANTENAS;
    }

    /**
     * @param aTIPOS_ANTENAS the TIPOS_ANTENAS to set
     */
    public static void setTIPOS_ANTENAS(String[] aTIPOS_ANTENAS) {
        TIPOS_ANTENAS = aTIPOS_ANTENAS;
    }

    /**
     * @return the TIPOS_POLARIZACIONES
     */
    public static String[] getTIPOS_POLARIZACIONES() {
        return TIPOS_POLARIZACIONES;
    }

    /**
     * @return the HPBW_Horizantal
     */
    public double getHPBW_Horizantal() {
        return HPBW_Horizantal;
    }

    /**
     * @param HPBW_Horizantal the HPBW_Horizantal to set
     */
    public void setHPBW_Horizantal(double HPBW_Horizantal) {
        this.HPBW_Horizantal = HPBW_Horizantal;
    }

    /**
     * @return the HPBW_Vertical
     */
    public double getHPBW_Vertical() {
        return HPBW_Vertical;
    }

    /**
     * @param HPBW_Vertical the HPBW_Vertical to set
     */
    public void setHPBW_Vertical(double HPBW_Vertical) {
        this.HPBW_Vertical = HPBW_Vertical;
    }
    
    /**
     * @param aTIPOS_POLARIZACIONES the TIPOS_POLARIZACIONES to set
     */
    public static void setTIPOS_POLARIZACIONES(String[] aTIPOS_POLARIZACIONES) {
        TIPOS_POLARIZACIONES = aTIPOS_POLARIZACIONES;
    }

    /**
     * @return the alturaAntena
     */
    public double getAlturaAntena() {
        return alturaAntena;
    }

    /**
     * @param alturaAntena the alturaAntena to set
     */
    public void setAlturaAntena(double alturaAntena) {
        this.alturaAntena = alturaAntena;
    }
}
