/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor.Paths;

import Controlador.ControladorGeneral;
import Modelo.GEO.CoordenadaGeografica;
import Modelo.GEO.MapaGeneral;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.PRO.Proyecto;
import Modelo.PRO.Sitio;
import Modelo.RAN.IndoorLink;
import Modelo.SIM.Outdoor.ModeloITU_R_M2135;
import Modelo.SIM.Outdoor.OutdoorLink;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class PathModelo_ITU_R_M2135 extends Path implements Serializable{
    
    public static String TIPOS_ESCENARIOS[] = {"InH", "UMi", "UMa", "SMa", "RMa"};
    public static String TIPOS_PATH1[] = {"LOS", "NLOS"};
    public static String TIPOS_PATH2[] = {"LOS", "NLOS", "O-to-I"};
    public static String DISTRIBUCIONES_CIUDADES[] = {"Manhattan Grid Layout", "Hexagonal Layout"};
    public static String CASOS_SHADOWING[]={"Por Encima", "Por Debajo", "Ambas"};
    public static double K_BOLTZMANN = 1.3806504 * (Math.pow(10, -23));
    
    double DELAY_SCALING_RICEAN[]={3.6,3,3.2,3,2.2,2.4,1.5,2.5,2.3,3.8,1.7};
    double DELAY_SPREAD_DESVICION_ESTANDAR[] = {0.18,0.14,0.40,0.54,0.32,0.38,0.33,0.66,0.39,0.55,0.48};
    
    private String tipoEscenario;
    private String tipoPath;
    
    private double alturaPromedioEdificios;
    private double anchuraPromedioCalles;
    private double temperaturaAmbiente;
    private String distrubucionUrbanistica;
    private double anguloVectorUnitario;
    private double frecuenciaCentralDownLink;
    private double frecuenciaCentralUpLink;
    
    private IndoorLink indoorLink;
    private OutdoorLinkRAN outdoorLink;
    private Sitio sitioAsociado;
    
    private double minimaProbabilidadShadowing;
    private String rangoShadowing;
    private int cantidadGrupos;
    
    private CoordenadaGeografica coordenadaPuntoRompimiento;
    
    public PathModelo_ITU_R_M2135(Sitio sitioAsociado, IndoorLink enlaceIndoor, OutdoorLinkRAN outdoorLink, CoordenadaGeografica coordenadaPuntoRompimiento, 
            int numeroPath, String tipoEscenario, String tipoPath, double alturaPromedioEdificios, double anchuraPromedioCalles, 
            String distrubucionUrbanistica, double temperaturaAmbiente, double anguloVectorUnitario, double minimaProbabilidadShadowing, String rangoShadowing, int cantidadGrupos){
        super(numeroPath);
        this.coordenadaPuntoRompimiento = coordenadaPuntoRompimiento;
        this.sitioAsociado = sitioAsociado;
        
        this.tipoEscenario = tipoEscenario;
        this.tipoPath = tipoPath;
        this.alturaPromedioEdificios = alturaPromedioEdificios;
        this.anchuraPromedioCalles = anchuraPromedioCalles;
        this.distrubucionUrbanistica = distrubucionUrbanistica;
        this.temperaturaAmbiente = temperaturaAmbiente;
        this.anguloVectorUnitario = anguloVectorUnitario;
        this.indoorLink = enlaceIndoor;
        this.outdoorLink = outdoorLink;
        
        this.minimaProbabilidadShadowing = minimaProbabilidadShadowing;
        this.rangoShadowing = rangoShadowing;
        this.cantidadGrupos = cantidadGrupos;
        
        this.frecuenciaCentralDownLink = enlaceIndoor.getTargetaEquipoRadio().getFrecuenciaInferiorDownLink()+((enlaceIndoor.getTargetaEquipoRadio().getAnchoBandaDownLink())/2);
        this.frecuenciaCentralUpLink = enlaceIndoor.getTargetaEquipoRadio().getFrecuenciaInferiorUpLink()+((enlaceIndoor.getTargetaEquipoRadio().getAnchoBandaUpLink())/2);
    }
      
    /*
     * Calcula la distancia lineal entre el eNodeB y la estacion Movil en metros
     */
    public double calcularPathLoss(boolean isDownLink){
        double pathLossLTE = 0.0;
        double alturaBS = indoorLink.getAntena().getAlturaAntena();
        double alturaUE = getOutdoorLink().getEquipoUsuario().getAltura();
        double distancia = MapaGeneral.calcularDistanciaEntreDosPuntos(sitioAsociado.getCoordenadaSitio(),
                getOutdoorLink().getEquipoUsuario().getCoordenadaEU());
        double frecuenciaCentral = 0;
        
        if(isDownLink){
            frecuenciaCentral = frecuenciaCentralDownLink;
        }else{frecuenciaCentral = frecuenciaCentralUpLink;}
        
        if(getTipoEscenario().equals("InH")){
            pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_InH(distancia, getTipoPath(), alturaBS, alturaUE, frecuenciaCentral);
        }else if(getTipoEscenario().equals("UMi")){
            if(getTipoPath().equals("LOS")){
                pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_UMi_LOS(distancia, alturaBS, alturaUE, frecuenciaCentral);
            }else if(getTipoPath().equals("NLOS")){
                pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_UMi_NLOS(alturaBS, alturaUE, getDistrubucionUrbanistica(), 
                    sitioAsociado.getCoordenadaSitio() , getOutdoorLink().getEquipoUsuario().getCoordenadaEU(), 
                    coordenadaPuntoRompimiento, frecuenciaCentral, distancia);
            }else if(getTipoPath().equals("O-to-I")){
                pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_UMi_OtoI(sitioAsociado.getCoordenadaSitio(), getOutdoorLink().getEquipoUsuario().getCoordenadaEU(), 
                        coordenadaPuntoRompimiento, getDistrubucionUrbanistica(), alturaBS, alturaUE, frecuenciaCentral, anguloVectorUnitario);
            }
        }else if(getTipoEscenario().equals("UMa")){
            pathLossLTE =  ModeloITU_R_M2135.calcularPathLossLTE_UMa(distancia, alturaBS, alturaUE, frecuenciaCentral, getTipoPath(), getAnchuraPromedioCalles(), getAlturaPromedioEdificios());
        }else if(getTipoEscenario().equals("SMa")){
            pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_SMa(distancia, alturaBS, alturaUE, frecuenciaCentral, getTipoPath(), getAnchuraPromedioCalles(), getAlturaPromedioEdificios());
        }else if(getTipoEscenario().equals("RMa")){
            pathLossLTE = ModeloITU_R_M2135.calcularPathLossLTE_RMa(distancia, alturaBS, alturaUE, frecuenciaCentral, getTipoPath(), getAnchuraPromedioCalles(), getAlturaPromedioEdificios());
        }
        return pathLossLTE;
    }
    
    public double calcularRSSI(boolean isDownLink, double pathLossInstantaneo) {
        
        double RSSI = 0;
        double anguloDelLinkAlNorte = MapaGeneral.calcularAnguloEntreDosPuntos_al_Norte(
                    sitioAsociado.getCoordenadaSitio(), 
                    getOutdoorLink().getEquipoUsuario().getCoordenadaEU()
                );
        
        double anguloElevacionEntreBSyEU = MapaGeneral.calcularAnguloElevacionEntreBSyEU(
                sitioAsociado.getCoordenadaSitio(), getOutdoorLink().getEquipoUsuario().getCoordenadaEU(),
                indoorLink.getAntena().getAlturaAntena(), getOutdoorLink().getEquipoUsuario().getAltura()
                );
        
        System.out.println("Angulo de azimut: " + indoorLink.getAntena().getAnguloAzimut());
        System.out.println("Angulo al norte: " + anguloDelLinkAlNorte);
        System.out.println("Diferencia de angulos: " + (indoorLink.getAntena().getAnguloAzimut() - anguloDelLinkAlNorte));
        
        if(isDownLink){
                RSSI = indoorLink.getTargetaEquipoRadio().getPotenciaMaximaTX() + indoorLink.obtenerPerdidasTotalesLink()+
                indoorLink.getAntena().obtenerGananciaInstantaneaAntenaSectorial(Math.abs(indoorLink.getAntena().getAnguloAzimut() - 
                anguloDelLinkAlNorte), (Math.abs(indoorLink.getAntena().getAnguloElevacion() - anguloElevacionEntreBSyEU))) - pathLossInstantaneo;
        }else{
                RSSI = getOutdoorLink().getEquipoUsuario().getPotenciaMaxima() + indoorLink.obtenerPerdidasTotalesLink()+
                indoorLink.getAntena().obtenerGananciaInstantaneaAntenaSectorial(Math.abs(indoorLink.getAntena().getAnguloAzimut() - 
                anguloDelLinkAlNorte), Math.abs(indoorLink.getAntena().getAnguloElevacion() - anguloElevacionEntreBSyEU)) - pathLossInstantaneo;
        }
        
        return RSSI;
    }
    
    public double calcularCNR(boolean isDownLink, double RSSI_Instantaneo) {
        
        double CNR = 0;
        double noise = 0;
        
        if(isDownLink){
            noise = 10*Math.log10(K_BOLTZMANN * (getTemperaturaAmbiente()+273)* (indoorLink.getTargetaEquipoRadio().getAnchoBandaDownLink()*1000000));
            CNR = RSSI_Instantaneo - noise;
        }else{
            noise = 10*Math.log10(K_BOLTZMANN * (getTemperaturaAmbiente()+273)* (indoorLink.getTargetaEquipoRadio().getAnchoBandaUpLink()*1000000));
            CNR = RSSI_Instantaneo - noise;
        }
        return CNR;
    }
    

    public double calcularMargenShadowing(){
        double margenShadowing = 0;
        double variableAleatoria = Math.random();
        
        if(getTipoEscenario().equals("InH")){
                if(getTipoPath().equals("LOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 3);
                }else if(getTipoPath().equals("NLOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 4);
                }
            }else if(getTipoEscenario().equals("UMi")){
                if(getTipoPath().equals("LOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 3);
                }else if(getTipoPath().equals("NLOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 4);
                }else if(getTipoPath().equals("O-to-I")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 7);
                }
            }else if(getTipoEscenario().equals("SMa")){
                if(getTipoPath().equals("LOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 4);
                }else if(getTipoPath().equals("NLOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 8);
                }
            }else if(getTipoEscenario().equals("UMa")){
                if(getTipoPath().equals("LOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 4);
                }else if(getTipoPath().equals("NLOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 6);
                }
            }else if(getTipoEscenario().equals("RMa")){
                if(getTipoPath().equals("LOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 4);
                }else if(getTipoPath().equals("NLOS")){
                    margenShadowing = ModeloITU_R_M2135.calcularShadowFadingMargin(getMinimaProbabilidadShadowing(), rangoShadowing, variableAleatoria, 8);
                }
            }
        return margenShadowing;
    }
    
    /*
     * Parametros de pequeña escala
     */
    public double[][] calcularPowerDelayProfile(double potenciaMedia){
        double PDP[][] = null;
        double[] retardosTiempo = ModeloITU_R_M2135.calcularDelaysT(cantidadGrupos, getTipoEscenario(), getTipoPath());
        double potencias[] = ModeloITU_R_M2135.calcularPotenciaGrupo(getTipoEscenario(), getTipoPath(), retardosTiempo);
        
        PDP = new double[2][retardosTiempo.length];
        for(int i = 0; i < retardosTiempo.length; i++){
            PDP[0][i] = retardosTiempo[i];
            PDP[1][i] = potencias[i] * potenciaMedia;
            //PDP[1][i] = Math.log10(potencias[i]);
        }
        return PDP;
    }

    /**
     * @return the outdoorLink
     */
    public OutdoorLinkRAN getOutdoorLink() {
        return outdoorLink;
    }

    /**
     * @return the tipoEscenario
     */
    public String getTipoEscenario() {
        return tipoEscenario;
    }

    /**
     * @return the tipoPath
     */
    public String getTipoPath() {
        return tipoPath;
    }

    /**
     * @return the alturaPromedioEdificios
     */
    public double getAlturaPromedioEdificios() {
        return alturaPromedioEdificios;
    }

    /**
     * @return the anchuraPromedioCalles
     */
    public double getAnchuraPromedioCalles() {
        return anchuraPromedioCalles;
    }

    /**
     * @return the temperaturaAmbiente
     */
    public double getTemperaturaAmbiente() {
        return temperaturaAmbiente;
    }

    /**
     * @return the distrubucionUrbanistica
     */
    public String getDistrubucionUrbanistica() {
        return distrubucionUrbanistica;
    }

    /**
     * @return the minimaProbabilidadShadowing
     */
    public double getMinimaProbabilidadShadowing() {
        return minimaProbabilidadShadowing;
    }
    
}