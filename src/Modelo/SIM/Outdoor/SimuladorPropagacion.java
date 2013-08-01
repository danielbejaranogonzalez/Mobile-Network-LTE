/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class SimuladorPropagacion implements Serializable{
    
    private String modeloPropagacion;
    public static String MODELOS_PROPAGACION[] = {"MODELO REC. ITU-R M.2135"};
    private OutdoorLink outdoorLinkPredeterminado; 
    
    public SimuladorPropagacion(){
        modeloPropagacion = "";
        //outdoorLinkPredeterminado = null;
    }

    /**
     * @return the modeloPropagacion
     */
    public String getModeloPropagacion() {
        return modeloPropagacion;
    }

    /**
     * @param modeloPropagacion the modeloPropagacion to set
     */
    public void setModeloPropagacion(String modeloPropagacion) {
        this.modeloPropagacion = modeloPropagacion;
    }

    /**
     * @return the outdoorLinkPredeterminado
     */
    public OutdoorLink getOutdoorLinkPredeterminado() {
        return outdoorLinkPredeterminado;
    }

    /**
     * @param outdoorLinkPredeterminado the outdoorLinkPredeterminado to set
     */
    public void setOutdoorLinkPredeterminado(OutdoorLink outdoorLinkPredeterminado) {
        this.outdoorLinkPredeterminado = outdoorLinkPredeterminado;
    }
    
}
