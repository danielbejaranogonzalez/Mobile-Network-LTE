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
public class TarjetaRadio implements Serializable{


    private int numeroSlot;
    private String fabricante;
    private String tecnologia;
    private String tipoTargeta;
    
    private double potenciaMaximaTX;
    private double sensibilidad;
    private String modulacion;
    private String tecnologiaMultiplexacion;
    
    private String duplexMode;
    private String bandaFrecuencia;
    private double frecuenciaInferiorUpLink;
    private double frecuenciaSuperiorUpLink;
    private double anchoBandaUpLink;
    
    private double frecuenciaInferiorDownLink;
    private double frecuenciaSuperiorDownLink;
    private double anchoBandaDownLink;
            
    
    private static String TECNOLOGIAS_MULTIPLEXACION[] = {"OFDM","COFDM","MIMO-OFDM","SC-FDM"};
    private static String MODULACIONES[] = {"QPSK", "8-PSK", "QAM", "16-QAM", "64-QAM"};
    private static String TIPOS_TARJETAS_RADIO[] = {"LTE ADVENCED - TIPO-N", "LTE ADVENCED - SMA"};
    private static String DUPLEX_MODES[]= {"FDD", "TDD"};
    
    private static String BANDAS_DE_FRECUENCIA_FDD[] = {
                                        "Banda 1", "Banda 2","Banda 3", "Banda 4", "Banda 5","Banda 6", "Banda 7","Banda 8", "Banda 9", "Banda 10",
                                        "Banda 11", "Banda 12","Banda 13", "Banda 14", "Banda 15","Banda 16", "Banda 17","Banda 18", "Banda 19", "Banda 20",
                                        "Banda 21", "Banda 22","Banda 23", "Banda 24", "Banda 25"};
    
    private static String BANDAS_DE_FRECUENCIA_TDD[]={"Banda 33", "Banda 34", "Banda 35","Banda 36", "Banda 37","Banda 38", 
                                            "Banda 39", "Banda 40","Banda 41", "Banda 42", "Banda 43"};
    
    private static double FRECUENCIA_INFERIOR_UPLINK[] = {1920,1850,1710,1710,824,830,2500,880,1749.0,1710,1427.9,
                                            698,777,788,1900,2010,704,815,830,832,1447.9,3410,3410,1625.5,1850 
                                            };
            
    private static double FRECUENCIA_SUPERIOR_UPLINK[] = {1980, 1910,1785,1755,849,840,2570,915,1784.9,1770,1452.9,
                                            716,787,798,1920,2025,716,830,845,862,1462.9,3500,2020,1660.5,1915
                                            };
    private static double FRECUENCIA_INFERIOR_DOWNLINK[] = {2110,1930,1805,2110,869,875,2620,925,1844.9,2110,1475.9,
                                            728,746,746,2600,2585,734,860,875,791,1495.5,3510,2180,1525,1930
                                            };
            
    private static double FRECUENCIA_SUPERIOR_DOWNLINK[] = { 2170,1990,1880,2155,894,885,2690,960,1879.9,2170,1500.9,
                                            746,756,768,2620,2600,746,875,890,821,1510.9,3600,2200,1559,1995
                                            };
    
    private static double FRECUENCIA_INFERIOR_ALLOCATION_TDD[] = {1900,2010,1850,1930,1910,2570,1880,2300,2496,3400,3600};
            
    private static double FRECUENCIA_SUPERIOR_ALLOCATION_TDD[] = {1920,2025,1910,1990,1930,2620,1920,2400,2690,3600,3800};
    
    
    public TarjetaRadio (int numeroSlot, String fabricante, String tecnologia, String tipoTargeta, String modulacion, String tecnologiaMultiplexacion, 
            double potenciaMax, double sensibilidad, String duplexMode, String bandaFrecuencia, double frecuenciaInferiorUpLink, 
            double frecuenciaSuperiorUpLink, double anchoBandaUpLink, double frecuenciaInferiorDownLink, double frecuenciaSuperiorDownLink, 
            double anchoBandaDownLink)
    {
        this.numeroSlot = numeroSlot;
        this.fabricante = fabricante;
        this.tecnologia = tecnologia;
        this.tipoTargeta = tipoTargeta;
        this.modulacion = modulacion;
        this.tecnologiaMultiplexacion = tecnologiaMultiplexacion;
         
        this.potenciaMaximaTX = potenciaMax;
        this.sensibilidad = sensibilidad;
        this.duplexMode = duplexMode;
        this.bandaFrecuencia = bandaFrecuencia;
        this.frecuenciaInferiorUpLink = frecuenciaInferiorUpLink;
        this.frecuenciaSuperiorUpLink = frecuenciaSuperiorUpLink;
        this.anchoBandaUpLink = anchoBandaUpLink;
        
        this.frecuenciaInferiorDownLink = frecuenciaInferiorDownLink;
        this.frecuenciaSuperiorDownLink = frecuenciaSuperiorDownLink;
        this.anchoBandaDownLink = anchoBandaDownLink;
    }

    /**
     * @return the numeroSlot
     */
    public int getNumeroSlot() {
        return numeroSlot;
    }

    /**
     * @param numeroSlot the numeroSlot to set
     */
    public void setNumeroSlot(int numeroSlot) {
        this.numeroSlot = numeroSlot;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the tecnologia
     */
    public String getTecnologia() {
        return tecnologia;
    }

    /**
     * @param tecnologia the tecnologia to set
     */
    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    /**
     * @return the tipoTargeta
     */
    public String getTipoTargeta() {
        return tipoTargeta;
    }

    /**
     * @param tipoTargeta the tipoTargeta to set
     */
    public void setTipoTargeta(String tipoTargeta) {
        this.tipoTargeta = tipoTargeta;
    }

    /**
     * @return the potenciaMaximaTX
     */
    public double getPotenciaMaximaTX() {
        return potenciaMaximaTX;
    }

    /**
     * @param potenciaMaximaTX the potenciaMaximaTX to set
     */
    public void setPotenciaMaximaTX(double potenciaMaximaTX) {
        this.potenciaMaximaTX = potenciaMaximaTX;
    }

    /**
     * @return the sensibilidad
     */
    public double getSensibilidad() {
        return sensibilidad;
    }

    /**
     * @param sensibilidad the sensibilidad to set
     */
    public void setSensibilidad(double sensibilidad) {
        this.sensibilidad = sensibilidad;
    }

    /**
     * @return the modulacion
     */
    public String getModulacion() {
        return modulacion;
    }

    /**
     * @param modulacion the modulacion to set
     */
    public void setModulacion(String modulacion) {
        this.modulacion = modulacion;
    }

    /**
     * @return the tecnologiaMultiplexacion
     */
    public String getTecnologiaMultiplexacion() {
        return tecnologiaMultiplexacion;
    }

    /**
     * @param tecnologiaMultiplexacion the tecnologiaMultiplexacion to set
     */
    public void setTecnologiaMultiplexacion(String tecnologiaMultiplexacion) {
        this.tecnologiaMultiplexacion = tecnologiaMultiplexacion;
    }

    /**
     * @return the duplexMode
     */
    public String getDuplexMode() {
        return duplexMode;
    }

    /**
     * @param duplexMode the duplexMode to set
     */
    public void setDuplexMode(String duplexMode) {
        this.duplexMode = duplexMode;
    }

    /**
     * @return the bandaFrecuencia
     */
    public String getBandaFrecuencia() {
        return bandaFrecuencia;
    }

    /**
     * @param bandaFrecuencia the bandaFrecuencia to set
     */
    public void setBandaFrecuencia(String bandaFrecuencia) {
        this.bandaFrecuencia = bandaFrecuencia;
    }

    /**
     * @return the frecuenciaInferiorUpLink
     */
    public double getFrecuenciaInferiorUpLink() {
        return frecuenciaInferiorUpLink;
    }

    /**
     * @param frecuenciaInferiorUpLink the frecuenciaInferiorUpLink to set
     */
    public void setFrecuenciaInferiorUpLink(double frecuenciaInferiorUpLink) {
        this.frecuenciaInferiorUpLink = frecuenciaInferiorUpLink;
    }

    /**
     * @return the frecuenciaSuperiorUpLink
     */
    public double getFrecuenciaSuperiorUpLink() {
        return frecuenciaSuperiorUpLink;
    }

    /**
     * @param frecuenciaSuperiorUpLink the frecuenciaSuperiorUpLink to set
     */
    public void setFrecuenciaSuperiorUpLink(double frecuenciaSuperiorUpLink) {
        this.frecuenciaSuperiorUpLink = frecuenciaSuperiorUpLink;
    }

    /**
     * @return the anchoBandaUpLink
     */
    public double getAnchoBandaUpLink() {
        return anchoBandaUpLink;
    }

    /**
     * @param anchoBandaUpLink the anchoBandaUpLink to set
     */
    public void setAnchoBandaUpLink(double anchoBandaUpLink) {
        this.anchoBandaUpLink = anchoBandaUpLink;
    }

    /**
     * @return the frecuenciaInferiorDownLink
     */
    public double getFrecuenciaInferiorDownLink() {
        return frecuenciaInferiorDownLink;
    }

    /**
     * @param frecuenciaInferiorDownLink the frecuenciaInferiorDownLink to set
     */
    public void setFrecuenciaInferiorDownLink(double frecuenciaInferiorDownLink) {
        this.frecuenciaInferiorDownLink = frecuenciaInferiorDownLink;
    }

    /**
     * @return the frecuenciaSuperiorDownLink
     */
    public double getFrecuenciaSuperiorDownLink() {
        return frecuenciaSuperiorDownLink;
    }

    /**
     * @param frecuenciaSuperiorDownLink the frecuenciaSuperiorDownLink to set
     */
    public void setFrecuenciaSuperiorDownLink(double frecuenciaSuperiorDownLink) {
        this.frecuenciaSuperiorDownLink = frecuenciaSuperiorDownLink;
    }

    /**
     * @return the anchoBandaDownLink
     */
    public double getAnchoBandaDownLink() {
        return anchoBandaDownLink;
    }

    /**
     * @param anchoBandaDownLink the anchoBandaDownLink to set
     */
    public void setAnchoBandaDownLink(double anchoBandaDownLink) {
        this.anchoBandaDownLink = anchoBandaDownLink;
    }
    
    
    
    
    
    
    /**
     * @return the TECNOLOGIAS_MULTIPLEXACION
     */
    public static String[] getTECNOLOGIAS_MULTIPLEXACION() {
        return TECNOLOGIAS_MULTIPLEXACION;
    }

    /**
     * @return the MODULACIONES
     */
    public static String[] getMODULACIONES() {
        return MODULACIONES;
    }

    /**
     * @return the TIPOS_TARJETAS_RADIO
     */
    public static String[] getTIPOS_TARJETAS_RADIO() {
        return TIPOS_TARJETAS_RADIO;
    }

    /**
     * @return the DUPLEX_MODES
     */
    public static String[] getDUPLEX_MODES() {
        return DUPLEX_MODES;
    }

    /**
     * @return the BANDAS_DE_FRECUENCIA_FDD
     */
    public static String[] getBANDAS_DE_FRECUENCIA_FDD() {
        return BANDAS_DE_FRECUENCIA_FDD;
    }

    /**
     * @return the BANDAS_DE_FRECUENCIA_TDD
     */
    public static String[] getBANDAS_DE_FRECUENCIA_TDD() {
        return BANDAS_DE_FRECUENCIA_TDD;
    }

    /**
     * @return the FRECUANCIA_INFERIOR_UPLINK
     */
    public static double[] getFRECUANCIA_INFERIOR_UPLINK() {
        return FRECUENCIA_INFERIOR_UPLINK;
    }

    /**
     * @return the FRECUANCIA_SUPERIOR_UPLINK
     */
    public static double[] getFRECUANCIA_SUPERIOR_UPLINK() {
        return FRECUENCIA_SUPERIOR_UPLINK;
    }

    /**
     * @return the FRECUANCIA_INFERIOR_DOWNLINK
     */
    public static double[] getFRECUANCIA_INFERIOR_DOWNLINK() {
        return FRECUENCIA_INFERIOR_DOWNLINK;
    }

    /**
     * @return the FRECUANCIA_SUPERIOR_DOWNLINK
     */
    public static double[] getFRECUANCIA_SUPERIOR_DOWNLINK() {
        return FRECUENCIA_SUPERIOR_DOWNLINK;
    }

    /**
     * @return the FRECUANCIA_INFERIOR_ALLOCATION_TDD
     */
    public static double[] getFRECUANCIA_INFERIOR_ALLOCATION_TDD() {
        return FRECUENCIA_INFERIOR_ALLOCATION_TDD;
    }

    /**
     * @return the FRECUANCIA_SUPERIOR_ALLOCATION_TDD
     */
    public static double[] getFRECUANCIA_SUPERIOR_ALLOCATION_TDD() {
        return FRECUENCIA_SUPERIOR_ALLOCATION_TDD;
    }
}