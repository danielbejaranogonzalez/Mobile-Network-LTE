/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RAN;

import java.io.Serializable;
import java.lang.Integer;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Radio implements Serializable{

    /**
     * @return the fabricantes
     */
    public static String[] getFabricantes() {
        return fabricantes;
    }
    
    private String nombre;
    private String fabricante;
    private boolean estado;
    private TarjetaRadio interfacesRadio[];
    private TarjetaAcceso interfacesAcceso[];
    
    private static String fabricantes[] = {"Alcatel - Lucent", "Ericcson", "Huawei", "NEC", "Nokia", "Nortel", "Motorola", "Generico"};
    
    public Radio(String nombre, String fabricante, boolean status){
        
        interfacesRadio = new TarjetaRadio [6];
        interfacesAcceso = new TarjetaAcceso [6];
        
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.estado = status;
    }
    
    public void crearInterfaceRadio(int numeroSlot, String fabricante, String tecnologia, String tipoTargeta, String modulacion, String tecnologiaMultiplexacion, 
            double potenciaMax, double sensivilidad, String duplexMode, String bandaFrecuencia, double frecuenciaInferiorUpLink, 
            double frecuenciaSuperiorUpLink, double anchoBandaUpLink, double frecuenciaInferiorDownLink, double frecuenciaSuperiorDownLink, 
            double anchoBandaDownLink){
         interfacesRadio[numeroSlot] = new TarjetaRadio( numeroSlot, fabricante, tecnologia, tipoTargeta, modulacion, tecnologiaMultiplexacion, potenciaMax, sensivilidad, 
            duplexMode, bandaFrecuencia, frecuenciaInferiorUpLink, frecuenciaSuperiorUpLink, anchoBandaUpLink, 
            frecuenciaInferiorDownLink, frecuenciaSuperiorDownLink, anchoBandaDownLink);
    }
    
    public void crearInterfaceAcceso(int numeroSlot, String nombreSlot, String tipoInterface){
         interfacesAcceso[numeroSlot] = new TarjetaAcceso(nombreSlot, tipoInterface);
    }
    
    public String[] obtenerTargetasRadioSlotVacios(){
        String[] targetasSlotsVacios;
        LinkedList <String> listaTargetas = new LinkedList<String> ();
        
        for(int i = 0; i < interfacesRadio.length; i++){
             if(interfacesRadio[i] == null){
                 listaTargetas.add(i + "");
             }
        }
        targetasSlotsVacios = new String[listaTargetas.size()];
        for(int j = 0; j < listaTargetas.size(); j++){
            targetasSlotsVacios[j] = listaTargetas.get(j);
        }
        return targetasSlotsVacios;
    }
    
     public String[] obtenerTargetasRadioSlotVaciosConActual(int slotActual){
        String[] targetasSlotsVacios;
        LinkedList <String> listaTargetas = new LinkedList<String> ();
        
        for(int i = 0; i < interfacesRadio.length; i++){
             if(interfacesRadio[i] == null || i == slotActual){
                 listaTargetas.add(i + "");
             }
        }
        targetasSlotsVacios = new String[listaTargetas.size()];
        for(int j = 0; j < listaTargetas.size(); j++){
            targetasSlotsVacios[j] = listaTargetas.get(j);
        }
        return targetasSlotsVacios;
    }
    
    public TarjetaRadio[] obtenerTargetasRadioSlotOcupados(){
        TarjetaRadio[] targetasSlotsOcupados;
        LinkedList <TarjetaRadio> listaTargetas = new LinkedList<TarjetaRadio> ();
        
        for(int i = 0; i < interfacesRadio.length; i++){
             if(interfacesRadio[i] != null){
                 listaTargetas.add(interfacesRadio[i]);
             }
        }
        targetasSlotsOcupados = new TarjetaRadio[listaTargetas.size()];
        for(int j = 0; j < listaTargetas.size(); j++){
            targetasSlotsOcupados[j] = listaTargetas.get(j);
        }
        return targetasSlotsOcupados;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the interfacesRadio
     */
    public TarjetaRadio[] getInterfacesRadio() {
        return interfacesRadio;
    }

    /**
     * @param interfacesRadio the interfacesRadio to set
     */
    public void setInterfacesRadio(TarjetaRadio[] interfacesRadio) {
        this.interfacesRadio = interfacesRadio;
    }

    /**
     * @return the interfacesAcceso
     */
    public TarjetaAcceso[] getInterfacesAcceso() {
        return interfacesAcceso;
    }

    public void elimiarInterfaceRadio(int numeroSlot) {
        interfacesRadio[numeroSlot] = null;
    }

    public void editarInterfaceRadio(int numeroSlot, String fabricante, String tecnologia, String tipoTargeta, String modulacion, String tecnologiaMultiplexacion, 
            double potenciaMax, double sensivilidad, String duplexMode, String bandaFrecuencia, double frecuenciaInferiorUpLink, 
            double frecuenciaSuperiorUpLink, double anchoBandaUpLink, double frecuenciaInferiorDownLink, double frecuenciaSuperiorDownLink, 
            double anchoBandaDownLink) {
         interfacesRadio[numeroSlot] = new TarjetaRadio( numeroSlot, fabricante, tecnologia, tipoTargeta, modulacion, tecnologiaMultiplexacion, potenciaMax, sensivilidad, 
            duplexMode, bandaFrecuencia, frecuenciaInferiorUpLink, frecuenciaSuperiorUpLink, anchoBandaUpLink, 
            frecuenciaInferiorDownLink, frecuenciaSuperiorDownLink, anchoBandaDownLink);
    }

    

}
