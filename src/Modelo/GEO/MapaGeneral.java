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
public class MapaGeneral implements Serializable{
    
    private CoordenadaGeografica obtenerPunto;
    
    public MapaGeneral(){
        obtenerPunto = new CoordenadaGeografica(0.0 , 0.0 , 0.0);
    }

    /**
     * @return the obtenerPunto
     */
    public CoordenadaGeografica getObtenerPunto() {
        return obtenerPunto;
    }

    /**
     * @param obtenerPunto the obtenerPunto to set
     */
    public void setObtenerPunto(CoordenadaGeografica obtenerPunto) {
        this.obtenerPunto = obtenerPunto;
    }
    
    public void setObtenerPunto(double latitud, double longitud, double elevacion) {
       obtenerPunto.setLatitudDecimal(latitud);
       obtenerPunto.setLongitudDecimal(longitud);
       obtenerPunto.setElevacion(elevacion);
    }
    
    public static double calcularDistanciaEntreDosPuntos(CoordenadaGeografica posicion1, CoordenadaGeografica posicion2){
        double distancia,a,c;
        double RADIO_TIERRA = 6372.795477598;
        double deltaLat, deltaLong;
        
        deltaLat = Math.abs(Math.toRadians( posicion1.getLatitudDecimal() - posicion2.getLatitudDecimal()));
        deltaLong = Math.abs(Math.toRadians( posicion1.getLongitudDecimal() - posicion2.getLongitudDecimal()));
        
        a = ((Math.sin(deltaLat/2))*(Math.sin(deltaLat/2)))+ 
            ((Math.cos(Math.toRadians(posicion1.getLatitudDecimal())))*(Math.cos(Math.toRadians(posicion2.getLatitudDecimal()))))*
            ((Math.sin(deltaLong/2))*(Math.sin(deltaLong/2)));
        c = 2 *(Math.atan2((Math.sqrt(a)), (Math.sqrt(1-a))));
        distancia = (RADIO_TIERRA * c) * 1000;  // En metros
                
        if(distancia >= 20000){
            return distancia;
        }else{
            deltaLat = Math.abs(Math.toRadians( posicion1.getLatitudDecimal() - posicion2.getLatitudDecimal()));
            deltaLong = Math.abs(Math.toRadians( posicion1.getLongitudDecimal() - posicion2.getLongitudDecimal()));
        
            a = ((Math.sin(deltaLat/2))*(Math.sin(deltaLat/2)))+ 
                ((Math.cos(Math.toRadians(posicion1.getLatitudDecimal())))*(Math.cos(Math.toRadians(posicion2.getLatitudDecimal()))))*
                ((Math.sin(deltaLong/2))*(Math.sin(deltaLong/2)));
            c = 2 *(Math.asin(Math.min(1, Math.sqrt(a))));
            
            distancia = (RADIO_TIERRA * c)*1000;
            return distancia;
        }
    }
    
    public static double calcularAnguloEntreDosPuntos_al_Norte(CoordenadaGeografica posicionPrivote, CoordenadaGeografica posicion2){
        double angulo = 0;
        double x, y, d;
        
        y = Math.toRadians(posicion2.getLatitudDecimal() - posicionPrivote.getLatitudDecimal());
        x = Math.toRadians(posicion2.getLongitudDecimal() - posicionPrivote.getLongitudDecimal());
        d = Math.sqrt((x*x)+(y*y));
                
        if(posicionPrivote.getLongitudDecimal() < posicion2.getLongitudDecimal()){
            angulo = Math.toDegrees(Math.acos(y/d));
        }else if(posicionPrivote.getLongitudDecimal() > posicion2.getLongitudDecimal()){
            angulo = -Math.toDegrees(Math.acos(y/d));
        }else {
            angulo = 0;
        }
        return angulo;     
    }
    
    public static double calcularAnguloElevacionEntreBSyEU(CoordenadaGeografica posicionBS, CoordenadaGeografica posicionEU, 
            double alturaAntenaBS, double alturaUE){
        
        double anguloEntreBSyEU = 0.0;
        double x, y, d,  D, ht, h1, h2, hp;
        
        ht = alturaAntenaBS;
        hp = alturaUE;
        h1 = posicionEU.getElevacion();
        h2 = posicionBS.getElevacion();
        d = calcularDistanciaEntreDosPuntos(posicionBS, posicionEU);
        
        x = Math.sqrt((d*d)-(Math.pow((h2-(h1+hp)), 2)));
        y = (h2+ht-(h1+hp));
        
        anguloEntreBSyEU = Math.abs(Math.toDegrees(Math.atan(x/y)));
        
        if(h2+alturaAntenaBS > h1+alturaUE){
            anguloEntreBSyEU = anguloEntreBSyEU - 90;
        }else{
            anguloEntreBSyEU = anguloEntreBSyEU + 90;
        }
        
        return anguloEntreBSyEU;
    }
    
    public static double calcularDistanciaEntreBSyEU(CoordenadaGeografica posicionBS, CoordenadaGeografica posicionEU, 
            double alturaAntenaBS, double alturaUE){
        
        double distanciaBSyEU = 0.0;
        double distanciaBSyEU2 = 0.0;
        double x, y, d,  D, ht, h1, h2, hp;
        
        ht = alturaAntenaBS;
        hp = alturaUE;
        h1 = posicionEU.getElevacion();
        h2 = posicionBS.getElevacion();
        d = calcularDistanciaEntreDosPuntos(posicionBS, posicionEU);
        
        x = Math.sqrt((d*d)-(Math.pow((h2-(h1+hp)), 2)));
        y = (h2+ht-(h1+hp));
        
        distanciaBSyEU = Math.sqrt((x*x)+(y*y));
        //System.out.println("TEST MEDIDAS Distancia: " + ht + " - " + hp + " - " + h1 + " - " + h2 + " - " + d + " - "+ x +" - " + distanciaBSyEU + " - " + distanciaBSyEU2);
        return distanciaBSyEU;
    }
    
    /*
    public static void main(String[] args) {
        double angulo = calcularAnguloEntreDosPuntos_al_Norte(new CoordenadaGeografica(4.626613069935928, -74.09200072288513,0), 
                new CoordenadaGeografica(4.623640166068041, -74.09196853637695,0));
    }
    */
}
