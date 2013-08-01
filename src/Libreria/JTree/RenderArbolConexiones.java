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
public class RenderArbolConexiones extends JPanel implements TreeCellRenderer
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
    private ImageIcon proyectoClose = new ImageIcon("src/Images/iconos16x16/package_go.png");
    private ImageIcon proyectoOpen = new ImageIcon("src/Images/iconos16x16/package.png");
    private ImageIcon outdoorLink = new ImageIcon("src/Images/iconos16x16/connect.png");
    private ImageIcon path  = new ImageIcon("src/Images/iconos16x16/chart_line.png");

    private ControladorGeneral controlador;
    private String proyectoPre;

    
    
    /**
     * Crea un nuevo objeto MiRender.
     */
    public RenderArbolConexiones(ControladorGeneral controlador)
    {
        this.controlador = controlador;
        
        add(botonIcono);
        add(botonTexto);
        setOpaque(false);
    }
    
    public void establecerProyectoPerdeterminado(String proyectoPre){
        this.proyectoPre = proyectoPre;
    }
    
    
    /**
     * Implementación del método de la interface TreeCellRenderer
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
        boolean leaf, int row, boolean hasFocus)
    {
        
        botonTexto.setFont(new Font("Arial", Font.PLAIN, 11));
        
       
        if(value.toString().equals("Proyecto: " + proyectoPre)){
              botonIcono.setIcon(proyectoClose);
              botonTexto.setFont(new Font("Arial", Font.BOLD, 12));
        }else if(value.toString().charAt(0)=='C' && value.toString().charAt(1)=='o'&& value.toString().charAt(2)=='n' && 
                value.toString().charAt(3)=='e' && value.toString().charAt(4)=='x'&& value.toString().charAt(5)=='i'&&
                value.toString().charAt(6)=='o' && value.toString().charAt(7)=='n'&& value.toString().charAt(8)==':'){
            botonIcono.setIcon(outdoorLink);
        }else if(value.toString().charAt(0)=='P' && value.toString().charAt(1)=='a'&& value.toString().charAt(2)=='t' && 
                value.toString().charAt(3)=='h' && value.toString().charAt(4)==':'){
            botonIcono.setIcon(path);
        }
        //---------------------------------------------------------------------------------------------
        botonTexto.setText(((DefaultMutableTreeNode) value).getUserObject().toString());
        return this;
    }
}