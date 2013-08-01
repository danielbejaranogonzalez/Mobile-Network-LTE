/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.PRO.ProjectBook;
import Vista.Hilos.EjecutarSimulacion;
import Vista.barras.BarraEstado;
import Vista.barras.BarraHerramientasPrincipal;
import Vista.general.PanelPrincipal;
import java.io.Serializable;
import javax.swing.JMenuBar;

/**
 *
 * @author Bejarano
 */
public class ControladorGeneral{

    /*
     * Modelo
     */
    
    private ProjectBook libroDeProyectos;

    /*
     * Vista
     */
    private JMenuBar barraPropiedades;
    private BarraHerramientasPrincipal barraHerramientas;
    private BarraEstado barraEstado;
    private PanelPrincipal principal;
    private String estadoMovimientoMapa;
    
    /*
     * Controladores
     */
    private ControladorBotones controladorBotones;
    private ControladorArbolProyectos controladorArbolProyectos;
    private ControladorVentanas controladorVentanas;
    private ControladorHilos controladorHilos;
    
    private Thread hiloSimulacion;
    private EjecutarSimulacion ejecutarSimulacion;
    
    /*
     * Contructor de la clase Controlador
     */
    public ControladorGeneral()
    {
        estadoMovimientoMapa = null;
        libroDeProyectos = new ProjectBook();
        
        /*
         * Instanciamiento de los demas controladores de apollo
         */
        controladorBotones = new ControladorBotones(this);
        controladorArbolProyectos = new ControladorArbolProyectos(this);
        controladorVentanas = new ControladorVentanas(this);
        controladorHilos = new ControladorHilos(this);
        
        ejecutarSimulacion = new EjecutarSimulacion(this);
        hiloSimulacion = new Thread(ejecutarSimulacion);
        
    }

    public void conectar(PanelPrincipal principal, JMenuBar barraPropiedades, BarraHerramientasPrincipal barraHerramientas, BarraEstado barraEstado){
        this.barraPropiedades = barraPropiedades;
        this.barraHerramientas = barraHerramientas;
        this.principal = principal;
        this.barraEstado = barraEstado;
    }

    public void setCloudMadeStylo(int numeroEstilo){
        getPrincipal().getPanelDiagramas().setCloudMadeStilo(numeroEstilo);
    }

    /**
     * @return the controladorBotones
     */
    public ControladorBotones getControladorBotones() {
        return controladorBotones;
    }
    

    /**
     * @return the libroDeProyectos
     */
    public ProjectBook getLibroDeProyectos() {
        return libroDeProyectos;
    }

    /**
     * @return the barraPropiedades
     */
    public JMenuBar getBarraPropiedades() {
        return barraPropiedades;
    }

    /**
     * @return the barraHerramientas
     */
    public BarraHerramientasPrincipal getBarraHerramientas() {
        return barraHerramientas;
    }

    /**
     * @return the principal
     */
    public PanelPrincipal getPrincipal() {
        return principal;
    }

    /**
     * @return the estadoMovimientoMapa
     */
    public String getEstadoMovimientoMapa() {
        return estadoMovimientoMapa;
    }

    /**
     * @return the controladorArbolProyectos
     */
    public ControladorArbolProyectos getControladorArbolProyectos() {
        return controladorArbolProyectos;
    }
    
    public ControladorVentanas getControladorVentanas(){
        return controladorVentanas;
    }

    /**
     * @return the controladorHilos
     */
    public ControladorHilos getControladorHilos() {
        return controladorHilos;
    }

    /**
     * @return the barraEstado
     */
    public BarraEstado getBarraEstado() {
        return barraEstado;
    }

    /**
     * @return the hiloSimulacion
     */
    public Thread getHiloSimulacion() {
        return hiloSimulacion;
    }

    /**
     * @return the ejecutarSimulacion
     */
    public EjecutarSimulacion getEjecutarSimulacion() {
        return ejecutarSimulacion;
    }

    public void reInstanciarHiloSimulacion() {
        ejecutarSimulacion = new EjecutarSimulacion(this);
        hiloSimulacion = new Thread(ejecutarSimulacion);
    }

}