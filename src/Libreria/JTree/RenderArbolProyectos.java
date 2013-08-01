/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.JTree;
/* 
 * Fichero: MiRender.java 
 * Autor: Chuidiang 
 * Fecha: 03/02/07 06:40 
 */ 

import Controlador.ControladorGeneral;
import Modelo.PRO.Proyecto;
import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;


/**
 * Un Render propio para árbol
 *
 * @author Chuidiang
 *
  */
public class RenderArbolProyectos extends JPanel implements TreeCellRenderer
{
    /**
     * Serial uid para evitar warnings.
     */
    private static final long serialVersionUID = 7187242510053519089L;

    /** Boton con el icono */
    private JLabel botonIcono = new JLabel();
    

    /**
     * Botón para el texto
     */
    private JLabel botonTexto = new JLabel();

    /**
     * Icono para las hojas del arbol
     */
    private ImageIcon projectBookClose = new ImageIcon("src/Images/iconos16x16/book.png");
    private ImageIcon projectBookOpen = new ImageIcon("src/Images/iconos16x16/book-open.png");
    private ImageIcon proyecto = new ImageIcon("src/Images/iconos16x16/package.png");
    private ImageIcon proyectoPredeterminado = new ImageIcon("src/Images/iconos16x16/package-share.png");
    
    private ImageIcon RANs = new ImageIcon("src/Images/iconos16x16/network-wireless.png");
    private ImageIcon EPCs = new ImageIcon("src/Images/iconos16x16/network-cloud.png");
    private ImageIcon sitios = new ImageIcon("src/Images/iconos16x16/building-medium.png");
    private ImageIcon equiposUsuario = new ImageIcon("src/Images/iconos16x16/blackberry_white.png");
    
    private ImageIcon sitio = new ImageIcon("src/Images/iconos16x16/building-medium.png");
    private ImageIcon RAN = new ImageIcon("src/Images/iconos16x16/RAN-16.png");
    private ImageIcon eNodeB = new ImageIcon("src/Images/iconos16x16/eNB6-16.png");
    
    private ImageIcon radio = new ImageIcon("src/Images/iconos16x16/stanchion.png");
    private ImageIcon antena = new ImageIcon("src/Images/iconos16x16/satellite_dish.png");
    private ImageIcon atenuador = new ImageIcon("src/Images/iconos16x16/port.png");
    private ImageIcon tarjetaRadio = new ImageIcon("src/Images/iconos16x16/network_adapter.png");
    
    private ImageIcon EU_Celular = new ImageIcon("src/Images/iconos16x16/blackberry_white.png");
    private ImageIcon EU_SmartP = new ImageIcon("src/Images/iconos16x16/iphone.png");
    private ImageIcon EU_Tablet = new ImageIcon("src/Images/iconos16x16/ipad.png");
    private ImageIcon EU_PC = new ImageIcon("src/Images/iconos16x16/laptop.png");
    private ImageIcon EU_Camara = new ImageIcon("src/Images/iconos16x16/cctv_camera.png");
    
    private ImageIcon indoorLink = new ImageIcon("src/Images/iconos16x16/connect-out.png");
    
    private ControladorGeneral controlador;
    private String proyectoPre;
    private String tipoSitio;
    
    
    /**
     * Crea un nuevo objeto MiRender.
     */
    public RenderArbolProyectos(ControladorGeneral controlador)
    {
        this.controlador = controlador;
        
        add(botonIcono);
        add(botonTexto);
        setOpaque(false);
    }
    
    public void establecerProyectoPerdeterminado(String proyectoPre){
        this.proyectoPre = proyectoPre;
    }
    
    public void estrablecerTipoDeSitio(String tipoSitio){
        this.tipoSitio = tipoSitio;
    }
    
    private String getTipoSitioDesdeNombre(String nombreSitio){
        String tipoSitioBuscado = "";
        LinkedList<Proyecto> proyectos = controlador.getLibroDeProyectos().getProyectos();
        String proyectoPredeterminadoActual = controlador.getLibroDeProyectos().getProyectoPredeterminado();
                for(int i=0; i < proyectos.size(); i++){
                    if(proyectos.get(i).getNombre().equals(proyectoPredeterminadoActual)){
                        Proyecto proyectoPre = proyectos.get(i);
                        for(int j = 0; j < proyectoPre.getGrupoSitios().size(); j++){
                                 if(proyectoPre.getGrupoSitios().get(j).getNombreSitio().equals(nombreSitio)){
                                     tipoSitioBuscado = proyectoPre.getGrupoSitios().get(j).getTipoSitio();
                                 }
                        }   
                    }
                }
         return tipoSitioBuscado;
    }
    

    /**
     * Implementación del método de la interface TreeCellRenderer
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
        boolean leaf, int row, boolean hasFocus)
    {
        
        System.out.println(value.toString());
        botonTexto.setFont(new Font("Arial", Font.PLAIN, 11));
        if(value.toString().equals("Project Book")){
            //System.out.println("Test Value: " + value);
            if(expanded){
                botonIcono.setIcon(projectBookOpen);
            }else
                botonIcono.setIcon(projectBookClose);
        }
        else if(value.toString().charAt(0)=='P' && value.toString().charAt(1)=='r'&& value.toString().charAt(2)=='o'
                && value.toString().charAt(3)=='y'&&value.toString().charAt(4)=='e'&&value.toString().charAt(5)=='c'
                && value.toString().charAt(6)=='t' && value.toString().charAt(7)=='o'&& value.toString().charAt(8)==':'){
            
                if(value.toString().equals("Proyecto: " + proyectoPre)){
                    botonIcono.setIcon(proyectoPredeterminado);
                    botonTexto.setFont(new Font("Arial", Font.BOLD, 12));
                }else
                    botonIcono.setIcon(proyecto);
        }
        
        else if(value.toString().equals("Sitios")){
            botonIcono.setIcon(sitios);
           
        }
        else if(value.toString().equals("Equipos de Usuario")){
            botonIcono.setIcon(equiposUsuario);
        }
        else if(value.toString().equals("Radio Access Networks")){
            botonIcono.setIcon(RANs);
        }
        else if(value.toString().equals("Envolved Packet Core")){
            botonIcono.setIcon(EPCs);
        }
        
        else if(value.toString().charAt(0)=='S' && value.toString().charAt(1)=='T'&& value.toString().charAt(2)==':'){
            
            String subCadena = value.toString().substring(4);
            if(getTipoSitioDesdeNombre(subCadena).equals("Estacion Base")){
                sitio = new ImageIcon("src/Images/iconos16x16/eNB5-16.png");
                botonIcono.setIcon(sitio);
            }else if(getTipoSitioDesdeNombre(subCadena).equals("Armario de Conexiones Outdoor")){
                sitio = new ImageIcon("src/Images/iconos16x16/drawer.png");
                botonIcono.setIcon(sitio);
            }else if(getTipoSitioDesdeNombre(subCadena).equals("Central de Conmutacion")){
                sitio = new ImageIcon("src/Images/iconos16x16/house_two.png");
                botonIcono.setIcon(sitio);
            }else if(getTipoSitioDesdeNombre(subCadena).equals("Network Operations Center (NOC)")){
                sitio = new ImageIcon("src/Images/iconos16x16/building (1).png");
                botonIcono.setIcon(sitio);
            }
            /*
            if(value.toString().equals("Sitio: " + getNombreSitioDesdeTipo(tipoSitio))){
                if(tipoSitio.equals("Estacion Base")){
                    //sitio = new ImageIcon("src/Images/iconos16x16/eNB5-16.png");
                    System.out.println("TEST 0 " + value);
                    botonIcono.setIcon(sitio0);
                }if(tipoSitio.equals("Armario de Conexiones Outdoor")){
                    //sitio = new ImageIcon("src/Images/iconos16x16/drawer.png");
                    System.out.println("TEST 1 " + value);
                    botonIcono.setIcon(sitio1); 
                }if(tipoSitio.equals("Central de Conmutacion")){
                    //sitio = new ImageIcon("src/Images/iconos16x16/building-medium.png");
                    System.out.println("TEST 2 " + value);
                    botonIcono.setIcon(sitio2);
                }if(tipoSitio.equals("Network Operations Center (NOC)")){
                    //sitio = new ImageIcon("src/Images/iconos16x16/building (1).png");
                    System.out.println("TEST 4 " + value);
                    botonIcono.setIcon(sitio0);
                }
            }
             */
        }else if(value.toString().charAt(0)=='R' && value.toString().charAt(1)=='A'&& value.toString().charAt(2)=='N'
                && value.toString().charAt(3)==':'){
            botonIcono.setIcon(RAN);
        }
        else if(value.toString().charAt(0)=='e' && value.toString().charAt(1)=='N'&& value.toString().charAt(2)=='B'
                && value.toString().charAt(3)==':'){
            botonIcono.setIcon(eNodeB);
        }
        else if(value.toString().charAt(0)=='T' && value.toString().charAt(1)=='M'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(EU_Celular);
        }
        else if(value.toString().charAt(0)=='S' && value.toString().charAt(1)=='P'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(EU_SmartP);
        }
        else if(value.toString().charAt(0)=='P' && value.toString().charAt(1)=='C'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(EU_PC);
        }
        else if(value.toString().charAt(0)=='T' && value.toString().charAt(1)=='B'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(EU_Tablet);
        }
        else if(value.toString().charAt(0)=='C' && value.toString().charAt(1)=='S'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(EU_Camara);
        }
        else if(value.toString().charAt(0)=='A' && value.toString().charAt(1)=='N'&& value.toString().charAt(2)=='T'&& value.toString().charAt(3)==':'){
            botonIcono.setIcon(antena);
        }
        else if(value.toString().charAt(0)=='R' && value.toString().charAt(1)=='D'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(radio);
        }
        else if(value.toString().charAt(0)=='A' && value.toString().charAt(1)=='T'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(atenuador);
        }
        else if(value.toString().charAt(0)=='T' && value.toString().charAt(1)=='R'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(tarjetaRadio);
        }
        else if(value.toString().charAt(0)=='I' && value.toString().charAt(1)=='L'&& value.toString().charAt(2)==':'){
            botonIcono.setIcon(indoorLink);
        }
        //---------------------------------------------------------------------------------------------
        botonTexto.setText(((DefaultMutableTreeNode) value).getUserObject().toString());
        return this;
    }
}