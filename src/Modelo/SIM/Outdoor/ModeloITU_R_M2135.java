/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor;

import Modelo.RAN.IndoorLink;
import Modelo.GEO.CoordenadaGeografica;
import Modelo.GEO.MapaGeneral;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.RAN.Antena;
import java.io.Serializable;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 * Basado en la recomendacion UTU-R M.2135 para las tecnologias IMT-Advanced
 */
public class ModeloITU_R_M2135 implements Serializable
{
    public static double DELAY_SCALING_RICEAN[]={3.6,3,3.2,3,2.2,2.4,1.5,2.5,2.3,3.8,1.7};
    public static double DELAY_SPREAD_DESVICION_ESTANDAR[] = {0.18,0.14,0.40,0.54,0.32,0.38,0.33,0.66,0.39,0.55,0.48};
    public static int SHADOWING_ESTANDAR_POR_GRUPO[] = {6,3,3,3,4,3,3,3,3,3,3};
    /*
     * Calculo de Path-Loss en Interiores
     */
    public static double calcularPathLossLTE_InH(double distanciaLinealLink, String tipoPath, double alturaBS, 
                    double alturaEU, double frecuenciaCentral){
        double pathLossInH = 0.0;
        double d = distanciaLinealLink;
        double hBS = alturaBS; 
        double hUE = alturaEU;
        
            if(tipoPath.equals("LOS")){
                if(d >= 3 && d < 100){
                    if((hBS>=3 && hBS<=6)&&(hUE>=1 && hUE<=2.5)){
                        pathLossInH = (16.9*Math.log10(d))+32.8+(20*Math.log10(frecuenciaCentral/1000));
                    }else{System.out.println("Test LTE-SIM-InH-LOS: Altura BS o EU fuera de Rango"); JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else{System.out.println("Test LTE-SIM-InH-LOS: Distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else if(tipoPath.equals("NLOS")){
                if(d >= 10 && d <= 150){
                    if((hBS>=3 && hBS<=6)&&(hUE>=1 && hUE<=2.5)){
                        pathLossInH = (43.3*Math.log10(d))+11.5+(20*Math.log10(frecuenciaCentral/1000));
                    }else{System.out.println("Test LTE-SIM-InH-NLOS: Altura BS o EU fuera de Rango"); JOptionPane.showMessageDialog(null, "'LTE-SIM-InH-LOS: Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else{System.out.println("Test LTE-SIM-InH-NLOS: Distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-InH-NLOS y LOS: caso fuera de rango");JOptionPane.showMessageDialog(null, "'Tipo de Path Herrado'","Error 025: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);};
        return pathLossInH;
    }
    
    public static double calcularPathLossLTE_UMi_LOS(double d, double alturaBS, double alturaEU, double frecuenciaCentral){
        double pathLossUMiLOS = 0.0;
        double dprima_BP;
        double hprima_BS, hprima_UE;
        double c = 300000000; // velosidad de la luz [m/s]
        double hBS = alturaBS; 
        double hUE = alturaEU;
        
        hprima_BS = hBS - 1;
        hprima_UE = hUE - 1;
        dprima_BP = 4*(hprima_BS)*(hprima_UE)*((frecuenciaCentral*1000000)/c);
        
        if(hBS <= 10 && hUE >= 1.5){
            if((d>=10)&&(d<=dprima_BP)){
                pathLossUMiLOS =  (22*Math.log10(d))+28+(20*Math.log10(frecuenciaCentral/1000));
            }else if((d>dprima_BP)&&(d<=5000)){
                pathLossUMiLOS = (40*Math.log10(d))+7.8-(18*Math.log10(hprima_BS))-(18*Math.log10(hprima_UE))+(2*Math.log10(frecuenciaCentral/1000));
            }else{System.out.println("Test LTE-SIM-UMi-LOS: Distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{ System.out.println("Test LTE-SIM-UMi-LOS: Altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        return pathLossUMiLOS;
    }
    
    public static double calcularPathLossLTE_UMi_NLOS(double alturaBS, double alturaEU, String distribucionCiudad, 
            CoordenadaGeografica coordenadaSitio , CoordenadaGeografica coordenadaUE, CoordenadaGeografica puntoRompimientoD1D2,
            double frecuenciaCentral, double distanciaLinealLink){
        double pathLossUMiNLOS = 0.0;
        double pathLoss_d1_d2 = 0.0;
        double pathLoss_d2_d1 = 0.0;
        double w = 20; // se asume ancho de la calle como 20 metros
        double hBS = alturaBS; 
        double hUE = alturaEU;
        
        if(distribucionCiudad.equals("Manhattan Grid Layout")){
                double d1, d2, nj1, nj2;
                d1 = MapaGeneral.calcularDistanciaEntreDosPuntos(coordenadaSitio, puntoRompimientoD1D2);
                d2 = MapaGeneral.calcularDistanciaEntreDosPuntos(puntoRompimientoD1D2, coordenadaUE);
            if( ((d1+d2)>=10) && ((d1+d2)<=5000) ){
                if(Math.min(d1, d2) >= (w/2)){
                    if(hBS <= 10 && hUE >= 1.5){
                        // donde k=1 y l=2
                        nj1 = Math.max(2.8-(d1*0.0024), 1.84);
                        pathLoss_d1_d2 = calcularPathLossLTE_UMi_LOS(d1, hBS, hUE, frecuenciaCentral)+
                                17.9-(12.5*nj1)+(10*nj1)*(Math.log10(d2))+(3*Math.log10(frecuenciaCentral/1000));
                        // donde k=2 y l=1
                        nj2 = Math.max(2.8-(d2*0.0024), 1.84);
                        pathLoss_d2_d1 = calcularPathLossLTE_UMi_LOS(d2, hBS, hUE, frecuenciaCentral)+
                                17.9-(12.5*nj2)+(10*nj2)*(Math.log10(d1))+(3*Math.log10(frecuenciaCentral/1000));
                        pathLossUMiNLOS = Math.min( pathLoss_d1_d2, pathLoss_d2_d1 );
                    }else{System.out.println("Test LTE-SIM-UMi-NLOS-Manhattan: hBS > 10 o hUE >1.5");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else if((Math.min(d1, d2)) >= 0 && (Math.min(d1, d2) < (w/2))){
                    pathLossUMiNLOS = calcularPathLossLTE_UMi_LOS(distanciaLinealLink, hBS, hUE, frecuenciaCentral);
                }else{System.out.println("Test LTE-SIM-UMi-NLOS-Manhattan: d1 o d2 es un valor negativo");JOptionPane.showMessageDialog(null, "'Distancias d1 o d2 fuera de rango para el modelo'","Error 026: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMi-NLOS-Manhattan: distancia d1+d2 fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancias d1 o d2 fuera de rango para el modelo'","Error 026: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else if(distribucionCiudad.equals("Hexagonal Layout")){
            if( (distanciaLinealLink >= 10) && (distanciaLinealLink<= 2000) ){
                if(hBS <= 10 && (hUE >=1 && hUE<=2.5)){
                    pathLossUMiNLOS= (36.7*Math.log10(distanciaLinealLink))+22.7+(26*Math.log10(frecuenciaCentral/1000));
                }else{System.out.println("Test LTE-SIM-UMi-NLOS-Hexagonal: hBS o hUE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMi-NLOS-Hexagonal: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-UMi-NLOS: distribucion de cuidad fuera de Rango");JOptionPane.showMessageDialog(null, "'Distribucion de ciudad con valor invalido'","Error 027: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        return pathLossUMiNLOS;
    }
    
    public static double calcularPathLossLTE_UMi_OtoI(CoordenadaGeografica coordenadaSitio , CoordenadaGeografica coordenadaUE, 
            CoordenadaGeografica coordenadaPuntoRompimineto, String distribucionCiudad, double alturaBS, double alturaEU, double frecuenciaCentral, double anguloVectorUnit){
        double hBS = alturaBS; 
        double hUE = alturaEU;
        double pathLossUMiOtoI = 0.0;
        double d_out, d_in;
        double PL_b, PL_tw, PL_in;
        double theta = 0.0;
        double anguloVectorUnitAlNorte = anguloVectorUnit;
        
        // distancias de la BS y la UE al muro
        d_out = MapaGeneral.calcularDistanciaEntreDosPuntos(coordenadaSitio, coordenadaPuntoRompimineto);
        d_in = MapaGeneral.calcularDistanciaEntreDosPuntos(coordenadaPuntoRompimineto, coordenadaUE);
        
        PL_b = calcularPathLossLTE_UMi_LOS(d_in+d_out, hBS, hUE, frecuenciaCentral);
        PL_in = 0.5*d_in;
        
        if(distribucionCiudad.equals("Manhattan Grid Layout")){
            if( ((d_in+d_out)>10) && ((d_in+d_out)<1000) ){
                if((d_in>0) && ((d_out)<25)){
                    theta = Math.abs(MapaGeneral.calcularAnguloEntreDosPuntos_al_Norte(
                            coordenadaPuntoRompimineto, coordenadaSitio)-anguloVectorUnitAlNorte);
                    PL_tw = 14 + 15*((1-Math.cos(Math.toRadians(theta)))*(1-Math.cos(Math.toRadians(theta))));
                    pathLossUMiOtoI = PL_b + PL_tw + PL_in;
                }else{System.out.println("Test LTE-SIM-UMi-OtoI-Manhattan: din fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia din o dout fuera de rango para el modelo'","Error 028: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMi-OtoI-Manhattan: din + dout fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia din o dout fuera de rango para el modelo'","Error 028: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else if(distribucionCiudad.equals("Hexagonal Layout")){
            if( ((d_in+d_out)>10) && ((d_in+d_out)<1000) ){
                if((d_in>0) && ((d_out)<25)){
                    PL_tw = 20;
                    pathLossUMiOtoI = PL_b + PL_tw + PL_in;
                }else{System.out.println("Test LTE-SIM-UMi-OtoI-Hexagonal: din fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia din o dout fuera de rango para el modelo'","Error 028: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMi-OtoI-Hexagonal: din + dout fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia din o dout fuera de rango para el modelo'","Error 028: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-UMi-OtoI: distribucion de cuidad fuera de Rango");JOptionPane.showMessageDialog(null, "'Distribucion de ciudad con valor invalido'","Error 027: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        
        return pathLossUMiOtoI;
    }
    
    public static double calcularPathLossLTE_UMa(double distanciaLinealLink, double hBS, double hUE, double frecuenciaCentral,
            String tipoPath, double anchuraPromedioCalles, double alturaPromedioEdificios){
        double pathLossUMa = 0.0;
        double d = distanciaLinealLink;
        int c = 300000000;
        double dprima_BP, hprima_BS, hprima_UE;
              
        hprima_BS = hBS - 1;
        hprima_UE = hUE - 1;
        dprima_BP = 4*(hprima_BS)*(hprima_UE)*((frecuenciaCentral*1000000)/c);
        
        if(tipoPath.equals("LOS")){
            if((d>=10)&&(d<=dprima_BP)){
                pathLossUMa = (22*Math.log10(d)) + 28 + (20*Math.log10(frecuenciaCentral/1000));
            }else if((d>dprima_BP)&&(d<=5000)){
                if((hBS>10 && hBS <=25) && (hUE >= 1.5 && hUE<= 10)){
                pathLossUMa = (40*Math.log10(d))+7.8-(18*Math.log10(hprima_BS))-(18*Math.log10(hprima_UE))+
                        (2*Math.log10(frecuenciaCentral/1000));
                }else{System.out.println("Test LTE-SIM-UMa-LOS: alturas BS y UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMa-LOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else if(tipoPath.equals("NLOS")){
            if((d>=10)&&(d<=5000)){
                if((hBS>=10 && hBS<=150)&&(hUE>=1 && hUE<=10)){
                    if((anchuraPromedioCalles>=5) && (anchuraPromedioCalles<=50)){
                        if(alturaPromedioEdificios>5 && alturaPromedioEdificios<= 50){
                            pathLossUMa = 161.04-(7.1*Math.log10(anchuraPromedioCalles))+(7.5*Math.log10(alturaPromedioEdificios))-
                                    ((24.37-3.7*((alturaPromedioEdificios/hBS)*(alturaPromedioEdificios/hBS)))*Math.log10(hBS))+
                                    ((43.42-(3.1*Math.log10(hBS)))*((Math.log10(d))-3))+(20*Math.log10(frecuenciaCentral/1000))-
                                    ((3.2*((Math.log10(11.75*hUE))*(Math.log10(11.75*hUE))))-4.97);
                        }else{System.out.println("Test LTE-SIM-UMa-NLOS: altura promedio de edificios fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura promedio de edificios fuera de rango'","Error 029: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                    }else{System.out.println("Test LTE-SIM-UMa-NLOS: anchura de calles fuera de Rango");JOptionPane.showMessageDialog(null, "'Anchura promedio de calles fuera de rango'","Error 030: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else{System.out.println("Test LTE-SIM-UMa-NLOS: altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-UMa-NLOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-UMa: caso fuera de Rango");JOptionPane.showMessageDialog(null, "'Tipo de Path Herrado'","Error 025: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        return pathLossUMa;
    }
    
    public static double calcularPathLossLTE_SMa(double distanciaLinealLink, double hBS, double hUE, double frecuenciaCentral,
            String tipoPath, double anchuraPromedioCalles, double alturaPromedioEdificios){
        double pathLossSMa = 0.0;
        double d = distanciaLinealLink;
        int c = 300000000;
        double dBP = 2*Math.PI * hBS * hUE *((frecuenciaCentral*1000000)/c);
        double PL1 = 0;
        
        if(tipoPath.equals("LOS")){
            if((hBS>=10 && hBS<=150)&&(hUE>=1 & hUE<=10)){
                if((anchuraPromedioCalles>=5) && (anchuraPromedioCalles<=50)){
                        if(alturaPromedioEdificios>5 && alturaPromedioEdificios<= 50){
                            if((d>=10)&&(d<=dBP)){
                                pathLossSMa = (20*Math.log10(40*Math.PI*d*(((frecuenciaCentral*1000000)/3))))+
                                        (Math.min(0.03*(Math.pow(alturaPromedioEdificios, 1.72)), 10)*Math.log10(d))-
                                        (Math.min(0.044*(Math.pow(alturaPromedioEdificios, 1.72)), 14.77))+ 
                                        (0.002*Math.log10(alturaPromedioEdificios)*d);
                            }else if((d>dBP)&&(d<=5000)){
                                PL1 = (20*Math.log10(40*Math.PI*dBP*(((frecuenciaCentral*1000000)/3))))+
                                        (Math.min(0.03*(Math.pow(alturaPromedioEdificios, 1.72)), 10)*Math.log10(dBP))-
                                        (Math.min(0.044*(Math.pow(alturaPromedioEdificios, 1.72)), 14.77))+ 
                                        (0.002*(Math.log10(alturaPromedioEdificios))*dBP);
                                pathLossSMa = PL1 + (40* Math.log10(d/dBP));
                            }else{System.out.println("Test LTE-SIM-SMa-LOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}            
                }else{System.out.println("Test LTE-SIM-SMa-LOS: altura promedio de edificios fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura promedio de edificios fuera de rango'","Error 029: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-SMa-LOS: anchura de calles fuera de Rango");JOptionPane.showMessageDialog(null, "'Anchura promedio de calles fuera de rango'","Error 030: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-SMa-LOS: altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else if(tipoPath.equals("NLOS")){
            if((d>=10)&&(d<=5000)){
                if((hBS>=10 && hBS<=150)&&(hUE>=1 & hUE<=10)){
                    if((anchuraPromedioCalles>=5) && (anchuraPromedioCalles<=50)){
                        if(alturaPromedioEdificios>5 && alturaPromedioEdificios<= 50){
                            pathLossSMa = 161.04-(7.1*Math.log10(anchuraPromedioCalles))+(7.5*Math.log10(alturaPromedioEdificios))-
                                    ((24.37-3.7*((alturaPromedioEdificios/hBS)*(alturaPromedioEdificios/hBS)))*Math.log10(hBS))+
                                    ((43.42-(3.1*Math.log10(hBS)))*((Math.log10(d))-3))+(20*Math.log10(frecuenciaCentral/1000))-
                                    ((3.2*((Math.log10(11.75*hUE))*(Math.log10(11.75*hUE))))-4.97);
                        }else{System.out.println("Test LTE-SIM-SMa-NLOS: altura promedio de edificios fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura promedio de edificios fuera de rango'","Error 029: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                    }else{System.out.println("Test LTE-SIM-SMa-NLOS: anchura de calles fuera de Rango");JOptionPane.showMessageDialog(null, "'Anchura promedio de calles fuera de rango'","Error 030: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else{System.out.println("Test LTE-SIM-SMa-NLOS: altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-SMa-NLOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-SMa: caso fuera de Rango"); JOptionPane.showMessageDialog(null, "'Tipo de Path Herrado'","Error 025: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        return pathLossSMa;
    } 
    
    public static double calcularPathLossLTE_RMa(double distanciaLinealLink, double hBS, double hUE, double frecuenciaCentral,
            String tipoPath, double anchuraPromedioCalles, double alturaPromedioEdificios){
        
        double pathLossRMa = 0.0;
        double d = distanciaLinealLink;
        int c = 300000000;
        double dBP = 2*Math.PI * hBS * hUE *((frecuenciaCentral*1000000)/c);
        double PL1 = 0;
        
        if(tipoPath.equals("LOS")){
            if((hBS>=10 && hBS<=150)&&(hUE>=1 & hUE<=10)){
                if((anchuraPromedioCalles>=5) && (anchuraPromedioCalles<=50)){
                        if(alturaPromedioEdificios>5 && alturaPromedioEdificios<= 50){
                            if((d>=10)&&(d<=dBP)){
                                pathLossRMa = (20*Math.log10(40*Math.PI*d*(((frecuenciaCentral*1000000)/3))))+
                                        (Math.min(0.03*(Math.pow(alturaPromedioEdificios, 1.72)), 10)*Math.log10(d))-
                                        (Math.min(0.044*(Math.pow(alturaPromedioEdificios, 1.72)), 14.77))+ 
                                        (0.002*Math.log10(alturaPromedioEdificios)*d);
                            }else if((d>dBP)&&(d<=10000)){
                                PL1 = (20*Math.log10(40*Math.PI*dBP*(((frecuenciaCentral*1000000)/3))))+
                                        (Math.min(0.03*(Math.pow(alturaPromedioEdificios, 1.72)), 10)*Math.log10(dBP))-
                                        (Math.min(0.044*(Math.pow(alturaPromedioEdificios, 1.72)), 14.77))+ 
                                        (0.002*(Math.log10(alturaPromedioEdificios))*dBP);
                                pathLossRMa = PL1 + (40* Math.log10(d/dBP));
                            }else{System.out.println("Test LTE-SIM-RMa-LOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}            
                }else{System.out.println("Test LTE-SIM-RMa-LOS: altura promedio de edificios fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura promedio de edificios fuera de rango'","Error 029: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-RMa-LOS: anchura de calles fuera de Rango");JOptionPane.showMessageDialog(null, "'Anchura promedio de calles fuera de rango'","Error 030: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-RMa-LOS: altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else if(tipoPath.equals("NLOS")){
            if((d>=10)&&(d<=5000)){
                if((hBS>=10 && hBS<=150)&&(hUE>=1 & hUE<=10)){
                    if((anchuraPromedioCalles>=5) && (anchuraPromedioCalles<=50)){
                        if(alturaPromedioEdificios>5 && alturaPromedioEdificios<= 50){
                            pathLossRMa = 161.04-(7.1*Math.log10(anchuraPromedioCalles))+(7.5*Math.log10(alturaPromedioEdificios))-
                                    ((24.37-3.7*((alturaPromedioEdificios/hBS)*(alturaPromedioEdificios/hBS)))*Math.log10(hBS))+
                                    ((43.42-(3.1*Math.log10(hBS)))*((Math.log10(d))-3))+(20*Math.log10(frecuenciaCentral/1000))-
                                    ((3.2*((Math.log10(11.75*hUE))*(Math.log10(11.75*hUE))))-4.97);
                        }else{System.out.println("Test LTE-SIM-RMa-NLOS: altura promedio de edificios fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura promedio de edificios fuera de rango'","Error 029: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                    }else{System.out.println("Test LTE-SIM-RMa-NLOS: anchura de calles fuera de Rango");JOptionPane.showMessageDialog(null, "'Anchura promedio de calles fuera de rango'","Error 030: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
                }else{System.out.println("Test LTE-SIM-RMa-NLOS: altura BS o UE fuera de Rango");JOptionPane.showMessageDialog(null, "'Altura BS o EU fuera de Rango'","Error 023: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
            }else{System.out.println("Test LTE-SIM-RMa-NLOS: distancia fuera de Rango");JOptionPane.showMessageDialog(null, "'Distancia fuera de rango para el modelo de simulacion'","Error 024: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        }else{System.out.println("Test LTE-SIM-RMa: caso fuera de Rango");JOptionPane.showMessageDialog(null, "'Tipo de Path Herrado'","Error 025: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);}
        return pathLossRMa;
    } 
    
    public static double hallarProbabilidadDeLOS(String tipoEscenario, double distanciaLinealLink){
        double probabilidadLOS = 0;
        
        if(tipoEscenario.equals("InH")){
            if(distanciaLinealLink <= 18){
                probabilidadLOS = 1;
            }else if(distanciaLinealLink>18 && distanciaLinealLink<37){
                probabilidadLOS = Math.exp(-(distanciaLinealLink-18)/27);
            }else if(distanciaLinealLink>=37){
                probabilidadLOS = 37;
            }else{System.out.print("Probabilidad de LOS: Fuera de rango de distancia");}
        }else if(tipoEscenario.equals("UMi")){
            probabilidadLOS = ((Math.min(18/distanciaLinealLink, 1))*(1-Math.exp(-distanciaLinealLink/36)))+Math.exp(-distanciaLinealLink/36);
        }else if(tipoEscenario.equals("UMa")){
            probabilidadLOS = ((Math.min(18/distanciaLinealLink, 1))*(1-Math.exp(-distanciaLinealLink/63)))+Math.exp(-distanciaLinealLink/63);
        }else if(tipoEscenario.equals("SMa")){
            if(distanciaLinealLink <=10){
                probabilidadLOS = 1;
            }else if(distanciaLinealLink > 10){
                probabilidadLOS = Math.exp(-(distanciaLinealLink-10)/200);
            }else{System.out.print("Probabilidad de LOS: Fuera de rango de distancia");}
        }else if(tipoEscenario.equals("RMa")){
            if(distanciaLinealLink <=10){
                probabilidadLOS = 1;
            }else if(distanciaLinealLink > 10){
                probabilidadLOS = Math.exp((-(distanciaLinealLink-10))/1000);
            }else{System.out.print("Probabilidad de LOS: Fuera de rango de distancia");}
        }else{ System.out.print("Probabilidad de LOS: Sin tipo de Escenario");}
        
        return probabilidadLOS;
    }
    
    public static double calcularShadowFadingMargin(double minimaProbabilidadShadowing, String rangoShadowing, double variableAleatoria, 
            int desviacionEstandar){
       
       double SFm = 0;
       //variableAleatoria = Math.random();
       while(variableAleatoria < (1-minimaProbabilidadShadowing) || variableAleatoria == 0){
           variableAleatoria = Math.random();
       }
       
       if(rangoShadowing.equals("Por Encima")){
            SFm = Math.cos(Math.toRadians(2*Math.PI*variableAleatoria))*((Math.sqrt(-2*Math.log10(variableAleatoria)))*desviacionEstandar)+0;
            return SFm;
       }else if(rangoShadowing.equals("Por Debajo")){
            SFm = -(Math.cos(Math.toRadians(2*Math.PI*variableAleatoria))*((Math.sqrt(-2*Math.log10(variableAleatoria)))*desviacionEstandar)+0);
            return SFm;
       } else if(rangoShadowing.equals("Ambas")){
           int signo = 0;
           signo = (int) Math.round(Math.random());
           if(signo==0){
                SFm = (Math.cos(Math.toRadians(2*Math.PI*variableAleatoria))*((Math.sqrt(-2*Math.log10(variableAleatoria)))*desviacionEstandar)+0);
                return SFm;
           }else if(signo==1){
                SFm = -(Math.cos(Math.toRadians(2*Math.PI*variableAleatoria))*((Math.sqrt(-2*Math.log10(variableAleatoria)))*desviacionEstandar)+0);
                return SFm;
           }
       }else{
           System.out.print("Shadow Fading: Fuera de rango de shadowing");
            JOptionPane.showMessageDialog(null, "'Shadow Fading: Fuera de rango de shadowing'","Error 022: ERROR DE INGRESO DE DATOS",JOptionPane.ERROR_MESSAGE);
       }
       return SFm;
    }
    
    /*
     * Calculo de Parametros de pequeña escal
     */
    public static double[] calcularDelaysT(int cantidadGrupos, String tipoEscenario, String tipoPath){

        double factor_k = 7;
        
        double delaysPrima[] = new double[cantidadGrupos];
        double delays[] = new double[cantidadGrupos];
        double variableAleatoriaGrupo[] = new double[cantidadGrupos];
        //Genera la variable aletoria
        for(int i=0; i<cantidadGrupos;i++){
            double tempVariableAleatoria=Math.random();
            while(tempVariableAleatoria == 0){
                tempVariableAleatoria=Math.random();
            }
            variableAleatoriaGrupo[i] = tempVariableAleatoria;
            // seleccion de parametros
            if(tipoEscenario.equals("InH")){
                if(tipoPath.equals("LOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[0])*(DELAY_SPREAD_DESVICION_ESTANDAR[0])*(Math.log(tempVariableAleatoria)); 
                    factor_k=7;
                }else if(tipoPath.equals("NLOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[1])*(DELAY_SPREAD_DESVICION_ESTANDAR[1])*(Math.log(tempVariableAleatoria));
                }
            }else if(tipoEscenario.equals("UMi")){
                if(tipoPath.equals("LOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[2])*(DELAY_SPREAD_DESVICION_ESTANDAR[2])*(Math.log(tempVariableAleatoria));
                    factor_k = 9;
                }else if(tipoPath.equals("NLOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[3])*(DELAY_SPREAD_DESVICION_ESTANDAR[3])*(Math.log(tempVariableAleatoria));
                }else if(tipoPath.equals("O-to-I")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[4])*(DELAY_SPREAD_DESVICION_ESTANDAR[4])*(Math.log(tempVariableAleatoria));
                }
            }else if(tipoEscenario.equals("SMa")){
                if(tipoPath.equals("LOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[5])*(DELAY_SPREAD_DESVICION_ESTANDAR[5])*(Math.log(tempVariableAleatoria));
                    factor_k = 9;
                }else if(tipoPath.equals("NLOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[6])*(DELAY_SPREAD_DESVICION_ESTANDAR[6])*(Math.log(tempVariableAleatoria));
                }
            }else if(tipoEscenario.equals("UMa")){
                if(tipoPath.equals("LOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[7])*(DELAY_SPREAD_DESVICION_ESTANDAR[7])*(Math.log(tempVariableAleatoria));
                    factor_k = 9;
                }else if(tipoPath.equals("NLOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[8])*(DELAY_SPREAD_DESVICION_ESTANDAR[8])*(Math.log(tempVariableAleatoria));
                }
            }else if(tipoEscenario.equals("RMa")){
                if(tipoPath.equals("LOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[9])*(DELAY_SPREAD_DESVICION_ESTANDAR[9])*(Math.log(tempVariableAleatoria));
                    factor_k = 7;
                }else if(tipoPath.equals("NLOS")){
                    delaysPrima [i] = (-DELAY_SCALING_RICEAN[10])*(DELAY_SPREAD_DESVICION_ESTANDAR[10])*(Math.log(tempVariableAleatoria));
                }
            }
        }
         //Organizacion de menor a mayor 
         Arrays.sort(delaysPrima);
         double menor = delaysPrima[0];
         
         //Busca el menor valor de delaysPrima
         for(int i=0; i<delaysPrima.length;i++){
             delays[i] = delaysPrima[i] - menor;
         }
         
         //Calculo de delat Tn
             if(tipoPath.equals("LOS")){
                 double D = 0;
                 double delaysLOS[] = new double[delays.length];
                 D = 0.7705 - (0.0433*factor_k) + (0.0002*Math.pow(factor_k, 2)) + (0.000017*Math.pow(factor_k, 3));
                 for(int i=0; i<delays.length;i++){
                     delaysLOS[i]= delays[i]/D;
                 }
                 delays = delaysLOS;
             }
             return delays;
    }
    
    public static double[] calcularPotenciaGrupo(String tipoEscenario, String tipoPath, double[] delays){
        
        double potencia_Prima[] = new double[delays.length];
        double potencia[] = new double[delays.length];
        double Z[] = new  double [delays.length];
        double sumatoriaPotencias_Prima = 0;
        
        for(int i=0; i<delays.length;i++){
            if(tipoEscenario.equals("InH")){
                if(tipoPath.equals("LOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[0], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[0]-1)/(DELAY_SCALING_RICEAN[0]*DELAY_SPREAD_DESVICION_ESTANDAR[0]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("NLOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[1], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[1]-1)/(DELAY_SCALING_RICEAN[1]*DELAY_SPREAD_DESVICION_ESTANDAR[1]))))*(Math.pow(10, (-Z[i]/10)));
                }
            }else if(tipoEscenario.equals("UMi")){
                if(tipoPath.equals("LOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[2], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[2]-1)/(DELAY_SCALING_RICEAN[2]*DELAY_SPREAD_DESVICION_ESTANDAR[2]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("NLOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[3], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[3]-1)/(DELAY_SCALING_RICEAN[3]*DELAY_SPREAD_DESVICION_ESTANDAR[3]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("O-to-I")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[4], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[4]-1)/(DELAY_SCALING_RICEAN[4]*DELAY_SPREAD_DESVICION_ESTANDAR[4]))))*(Math.pow(10, (-Z[i]/10)));
                }
            }else if(tipoEscenario.equals("SMa")){
                if(tipoPath.equals("LOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[5], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[5]-1)/(DELAY_SCALING_RICEAN[5]*DELAY_SPREAD_DESVICION_ESTANDAR[5]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("NLOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[6], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[6]-1)/(DELAY_SCALING_RICEAN[6]*DELAY_SPREAD_DESVICION_ESTANDAR[6]))))*(Math.pow(10, (-Z[i]/10)));
                }
            }else if(tipoEscenario.equals("UMa")){
                if(tipoPath.equals("LOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[7], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[7]-1)/(DELAY_SCALING_RICEAN[7]*DELAY_SPREAD_DESVICION_ESTANDAR[7]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("NLOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[8], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[8]-1)/(DELAY_SCALING_RICEAN[8]*DELAY_SPREAD_DESVICION_ESTANDAR[8]))))*(Math.pow(10, (-Z[i]/10)));
                }
            }else if(tipoEscenario.equals("RMa")){
                if(tipoPath.equals("LOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[9], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[9]-1)/(DELAY_SCALING_RICEAN[9]*DELAY_SPREAD_DESVICION_ESTANDAR[9]))))*(Math.pow(10, (-Z[i]/10)));
                }else if(tipoPath.equals("NLOS")){
                    Z[i]= Math.random() * Math.pow(SHADOWING_ESTANDAR_POR_GRUPO[10], 2);
                    potencia_Prima[i] = (Math.exp((-delays[i])*((DELAY_SCALING_RICEAN[10]-1)/(DELAY_SCALING_RICEAN[10]*DELAY_SPREAD_DESVICION_ESTANDAR[10]))))*(Math.pow(10, (-Z[i]/10)));
                }
            }
        }
        
        for(int n=0; n<potencia_Prima.length;n++){
            sumatoriaPotencias_Prima = sumatoriaPotencias_Prima  + potencia_Prima[n];
        }
        
        for(int n=0; n<potencia_Prima.length;n++){
            potencia[n] = potencia_Prima[n]/ sumatoriaPotencias_Prima;
        }
        
        return potencia;
    }
    /*
    public static void main(String[] args) {
         
    }
    */
}
