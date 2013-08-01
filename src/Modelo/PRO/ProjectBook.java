/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.PRO;

import Controlador.ControladorGeneral;
import Modelo.GEO.MapaGeneral;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ProjectBook {
    
    private String nombre;
    private LinkedList <Proyecto> proyectos;
    private String proyectoPredeterminado;
    private MapaGeneral mapa;
    private Storage storage;
    
    private ControladorGeneral controlador;

    public ProjectBook(){
        
        nombre = "Project Book";
        proyectos = new LinkedList <Proyecto>();
        mapa = new MapaGeneral();
        storage = new Storage();
        proyectoPredeterminado = null;
    }
    
    public void crearProyecto(String nombreProyecto, String tipoProyecto, boolean isPredeterminado){
        
        boolean isRepetido = false;
        
        for(int j = 0; j < proyectos.size(); j++){
            if(proyectos.get(j).getNombre().equals(nombreProyecto)){
                isRepetido = true;
                break;
            }
        }
        if(isRepetido == false){
            if(isPredeterminado){
                    proyectos.add(new Proyecto(nombreProyecto,tipoProyecto, isPredeterminado));
                    for(int i=0; i < proyectos.size(); i++){
                        if(proyectos.get(i).getNombre().equals(nombreProyecto)){
                            proyectos.get(i).setProyectoPredeterminado(true);
                        }else
                            proyectos.get(i).setProyectoPredeterminado(false);
                    }
                    proyectoPredeterminado = nombreProyecto;
                }else
                    proyectos.add(new Proyecto(nombreProyecto,tipoProyecto, isPredeterminado));
        }else{
            JOptionPane.showMessageDialog(null, "'No es posible crear el Proyecto. Es probable que ya exista un proyecto con el nombre "+ nombreProyecto + "'","Error 026: Error en la creacion de proyectos",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void crearProyecto(Proyecto proyecto){
        
        boolean isRepetido = false;
        
        for(int j = 0; j < proyectos.size(); j++){
            if(proyectos.get(j).getNombre().equals(proyecto.getNombre())){
                isRepetido = true;
                break;
            }
        }
        if(isRepetido == false){
            proyectos.add(proyecto);
                    for(int i=0; i < proyectos.size(); i++){
                        if(proyectos.get(i).getNombre().equals(proyecto.getNombre())){
                            proyectos.get(i).setProyectoPredeterminado(true);
                        }else
                            proyectos.get(i).setProyectoPredeterminado(false);
                    }
                    proyectoPredeterminado = proyecto.getNombre();
        }else{
            JOptionPane.showMessageDialog(null, "'No es posible crear el Proyecto. Es probable que ya exista un proyecto con el nombre "+ proyecto.getNombre() + "'","Error 026: Error en la creacion de proyectos",JOptionPane.ERROR_MESSAGE);
        }
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
     * @return the proyectos
     */
    public LinkedList <Proyecto> getProyectos() {
        return proyectos;
    }
    
    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(LinkedList <Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    /**
     * @return the proyectoPredeterminado
     */
    public String getProyectoPredeterminado() {
        return proyectoPredeterminado;
    }
    
    /**
     * @param proyectoPredeterminado the proyectoPredeterminado to set
     */
    public void setProyectoPredeterminado(String proyectoPredeterminado) {
        for(int i=0; i < proyectos.size(); i++){
            if(proyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                proyectos.get(i).setProyectoPredeterminado(true);
                this.proyectoPredeterminado = proyectoPredeterminado;
            }else
                proyectos.get(i).setProyectoPredeterminado(false);
        }
    }
    
    public void guardarProyectoPredeterminado(Proyecto proyecto, String ruta){
        proyecto.setURLProyecto(ruta);
        storage.guardarProyectoPredeterminado(proyecto);
    }
    
    public String abrirProyecto(String ruta) {
         Proyecto importado = storage.abrirProyecto(ruta);
         this.crearProyecto(importado);
         return importado.getNombre();
    }

    /**
     * @return the mapa
     */
    public MapaGeneral getMapa() {
        return mapa;
    }
}
