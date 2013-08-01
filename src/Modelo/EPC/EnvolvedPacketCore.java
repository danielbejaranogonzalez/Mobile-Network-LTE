/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.EPC;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class EnvolvedPacketCore extends SystemArchitectureEvolution_LTE implements Serializable
{
    
    private LinkedList<MobilityManagementEntit_MME> grupoMME;
    private LinkedList<ServingGateway_SWG> grupoSWG;
    private LinkedList<PDNGateway_PGW> grupoPGW;
    private LinkedList<HomeSubscriberServer_HSS> grupoHSS;
    
    public EnvolvedPacketCore ()
    {
         
         grupoMME = new LinkedList<MobilityManagementEntit_MME> ();
         grupoSWG = new LinkedList<ServingGateway_SWG> ();
         grupoPGW = new LinkedList<PDNGateway_PGW> ();
         grupoHSS = new LinkedList<HomeSubscriberServer_HSS> ();
         
    }
    
    public void crearMME(){
        grupoMME.add(new MobilityManagementEntit_MME());
    }
    
    public void crearSWG(){
        grupoSWG.add(new ServingGateway_SWG());
    }
    
    public void crearPWG(){
        grupoPGW.add(new PDNGateway_PGW());
    }
    
    public void crearHSS(){
        grupoHSS.add(new HomeSubscriberServer_HSS());
    }
    
}
