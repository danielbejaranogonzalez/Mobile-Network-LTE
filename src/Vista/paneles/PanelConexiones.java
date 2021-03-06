/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDispositivos.java
 *
 * Created on 21/07/2010, 01:15:34 PM
 */

package Vista.paneles;

import Controlador.ControladorGeneral;
import Libreria.JTree.RenderArbolConexiones;
import Libreria.JTree.RenderArbolProyectos;
import Modelo.PRO.Proyecto;
import Modelo.SIM.Outdoor.OutdoorLink;
import Modelo.SIM.Outdoor.OutdoorLinkRAN;
import Modelo.SIM.Outdoor.Paths.PathModelo_ITU_R_M2135;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Bejarano
 */
public class PanelConexiones extends javax.swing.JPanel {

    private DefaultTreeModel modelo;
    private DefaultMutableTreeNode proyectoPredeterminado;
    private RenderArbolConexiones renderArbol;
    
    private ControladorGeneral controlador;
    
    /** Creates new form PanelDispositivos */
    public PanelConexiones(ControladorGeneral controlador) {
        this.controlador = controlador;
        initComponents();
        
        proyectoPredeterminado = new DefaultMutableTreeNode("");
        modelo = new DefaultTreeModel(proyectoPredeterminado);
        
        modelo.getPathToRoot(proyectoPredeterminado);
        renderArbol = new RenderArbolConexiones(this.controlador);
        
        proyectoPredeterminado = new DefaultMutableTreeNode("<Sin proyecto>");
        modelo = new DefaultTreeModel(proyectoPredeterminado);
        
        arbolConexiones.setModel(modelo);
        arbolConexiones.setCellRenderer(renderArbol);
    }
    
    public void actualizarArbol(Proyecto proyectoPred){
        
        try{
            proyectoPredeterminado = new DefaultMutableTreeNode("Proyecto: " + proyectoPred.getNombre());
            renderArbol.establecerProyectoPerdeterminado(proyectoPred.getNombre());
            modelo = new DefaultTreeModel(proyectoPredeterminado); 
        
            for(int i = 0; i < proyectoPred.getGrupoOutdoorLinks().size();i++){
                DefaultMutableTreeNode sitioNuevo = new DefaultMutableTreeNode("Conexion: " + proyectoPred.getGrupoOutdoorLinks().get(i).getNombreEnlaceOutdoor() );
                proyectoPredeterminado.insert(sitioNuevo, 0);

                OutdoorLinkRAN conexion = (OutdoorLinkRAN) proyectoPred.getGrupoOutdoorLinks().get(i);
                for(int j = 0; j < conexion.getListaPaths().size();j++){
                    PathModelo_ITU_R_M2135 path = (PathModelo_ITU_R_M2135) conexion.getListaPaths().get(j);
                    DefaultMutableTreeNode pathActual = new DefaultMutableTreeNode("Path: " + path.getNumeroPath());
                    sitioNuevo.insert(pathActual, 0);
                }
            }

            arbolConexiones.setModel(modelo);
            arbolConexiones.setCellRenderer(renderArbol);
            expandAllTree(arbolConexiones, true);
        }catch(NullPointerException NPE){
            modelo.getPathToRoot(proyectoPredeterminado);
            renderArbol = new RenderArbolConexiones(this.controlador);

            proyectoPredeterminado = new DefaultMutableTreeNode("<Sin proyecto>");
            modelo = new DefaultTreeModel(proyectoPredeterminado);

            arbolConexiones.setModel(modelo);
            arbolConexiones.setCellRenderer(renderArbol);
        }
        
        
    }
    
    public static void expandAllTree(JTree tree, boolean expand) { 
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        expandAllTree(tree, new TreePath(root), expand); 
    } 
    private static void expandAllTree(JTree tree, TreePath parent, boolean expand) { 
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        
        if (node.getChildCount() >= 0) { 
            for (Enumeration e= node.children(); e.hasMoreElements(); ) { 
                TreeNode n = (TreeNode)e.nextElement(); 
                TreePath path = parent.pathByAddingChild(n); 
                expandAllTree(tree, path, expand); 
            } 
        } 
        if (expand) { 
            tree.expandPath(parent); 
        } else { 
            tree.collapsePath(parent); 
        } 
    } 

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolConexiones = new javax.swing.JTree();

        setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(300, 300));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        arbolConexiones.setLargeModel(true);
        arbolConexiones.setRowHeight(21);
        arbolConexiones.setVisibleRowCount(30);
        jScrollPane1.setViewportView(arbolConexiones);

        jPanel2.add(jScrollPane1);

        jTabbedPane1.addTab("Conexiones Outdoor", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/connect.png")), jPanel2); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Conexiones Outdoor");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolConexiones;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
