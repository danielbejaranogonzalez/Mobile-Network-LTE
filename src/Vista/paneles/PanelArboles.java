/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelArboles.java
 *
 * Created on 21/07/2010, 01:15:07 PM
 */

package Vista.paneles;

import Controlador.ControladorGeneral;
import Libreria.JTree.RenderArbolProyectos;
import Modelo.PRO.Proyecto;
import Modelo.RAN.Antena;
import Modelo.RAN.ENodeB;
import Modelo.RAN.ElementoAtenuacion;
import Modelo.RAN.IndoorLink;
import Modelo.RAN.Radio;
import Modelo.RAN.TarjetaRadio;
import java.lang.Object;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Bejarano
 */
public class PanelArboles extends javax.swing.JPanel {

    private DefaultTreeModel modelo;
    private DefaultMutableTreeNode book;
    private RenderArbolProyectos renderArbol;
    
    private ControladorGeneral controlador;
    
    /** Creates new form PanelArboles */
    public PanelArboles(ControladorGeneral controlador) {
        this.controlador = controlador;
        initComponents();
        
        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbolProyectos.getCellRenderer();
        
        book = new DefaultMutableTreeNode("Project Book");
        modelo = new DefaultTreeModel(book);
        
        modelo.getPathToRoot(book);
        renderArbol = new RenderArbolProyectos(this.controlador);
        
        arbolProyectos.setModel(modelo);
        arbolProyectos.setCellRenderer(renderArbol);
    }
    
    public void establecerProyectoPredeterminado(String proyectoPredeterminado){
        renderArbol.establecerProyectoPerdeterminado(proyectoPredeterminado);
    }
    
    public void establecerTipoSitio(String tipoSitio){
        renderArbol.estrablecerTipoDeSitio(tipoSitio);
    }
    
    public void actualizarArbol(LinkedList<Proyecto> listaProyectos){
        
        book = new DefaultMutableTreeNode("Project Book");
        modelo = new DefaultTreeModel(book); 
        
        
        for(int i = 0; i < listaProyectos.size(); i++){
            
            Proyecto proyectoActual = listaProyectos.get(i);
            
            DefaultMutableTreeNode sitios = new DefaultMutableTreeNode("Sitios");
            DefaultMutableTreeNode equipoUsuario = new DefaultMutableTreeNode("Equipos de Usuario");
            DefaultMutableTreeNode radioAccessNetworks = new DefaultMutableTreeNode("Radio Access Networks");
            DefaultMutableTreeNode envolvedPacketCore = new DefaultMutableTreeNode("Envolved Packet Core");
            
            DefaultMutableTreeNode nodoProyectoActual = new DefaultMutableTreeNode("Proyecto: " + proyectoActual.getNombre());
            modelo.insertNodeInto((DefaultMutableTreeNode) nodoProyectoActual, book, i);
            
            DefaultMutableTreeNode tempSitios = (DefaultMutableTreeNode) nodoProyectoActual;
            DefaultMutableTreeNode tempRANs = (DefaultMutableTreeNode) nodoProyectoActual;
            DefaultMutableTreeNode tempEPC = (DefaultMutableTreeNode) nodoProyectoActual;
            
            tempSitios.insert(sitios, 0);
            tempSitios.insert(equipoUsuario, 1);
            tempRANs.insert(radioAccessNetworks, 2);
            tempEPC.insert(envolvedPacketCore, 3);
            
            for(int j = 0; j < proyectoActual.getGrupoSitios().size(); j++){
                //DefaultMutableTreeNode sitios = (DefaultMutableTreeNode) nodoProyectoActual.getNextNode();
                DefaultMutableTreeNode sitioNuevo = new DefaultMutableTreeNode("ST: " +  proyectoActual.getGrupoSitios().get(j).getNombreSitio());
                sitios.insert(sitioNuevo, 0);
            }
            
            for(int j = 0; j < proyectoActual.getGrupoEUs().size(); j++){
                //DefaultMutableTreeNode sitios = (DefaultMutableTreeNode) nodoProyectoActual.getNextNode();
                if(proyectoActual.getGrupoEUs().get(j).getTipoEU().equals("Telefono Movil")){
                    DefaultMutableTreeNode equipoUsuarioNuevo = new DefaultMutableTreeNode("TM: " +  proyectoActual.getGrupoEUs().get(j).getNombreEstacionMovil());
                    equipoUsuario.insert(equipoUsuarioNuevo, 0);
                }else if(proyectoActual.getGrupoEUs().get(j).getTipoEU().equals("Smart Phone")){
                    DefaultMutableTreeNode equipoUsuarioNuevo = new DefaultMutableTreeNode("SP: " +  proyectoActual.getGrupoEUs().get(j).getNombreEstacionMovil());
                    equipoUsuario.insert(equipoUsuarioNuevo, 0);
                 }else if(proyectoActual.getGrupoEUs().get(j).getTipoEU().equals("PC")){
                    DefaultMutableTreeNode equipoUsuarioNuevo = new DefaultMutableTreeNode("PC: " +  proyectoActual.getGrupoEUs().get(j).getNombreEstacionMovil());
                    equipoUsuario.insert(equipoUsuarioNuevo, 0);
                  }else if(proyectoActual.getGrupoEUs().get(j).getTipoEU().equals("Tablet")){
                    DefaultMutableTreeNode equipoUsuarioNuevo = new DefaultMutableTreeNode("TB: " +  proyectoActual.getGrupoEUs().get(j).getNombreEstacionMovil());
                    equipoUsuario.insert(equipoUsuarioNuevo, 0);
                    }else if(proyectoActual.getGrupoEUs().get(j).getTipoEU().equals("Camara CCTV 4G")){
                        DefaultMutableTreeNode equipoUsuarioNuevo = new DefaultMutableTreeNode("CS: " +  proyectoActual.getGrupoEUs().get(j).getNombreEstacionMovil());
                        equipoUsuario.insert(equipoUsuarioNuevo, 0);
                    }
            }
            for(int j = 0; j < proyectoActual.getGrupoRANs().size(); j++){
                //DefaultMutableTreeNode sitios = (DefaultMutableTreeNode) nodoProyectoActual.getNextNode();
                DefaultMutableTreeNode nuevaRAN = new DefaultMutableTreeNode("RAN: " +  proyectoActual.getGrupoRANs().get(j).getNombre());
                radioAccessNetworks.insert(nuevaRAN, 0);
                
                LinkedList <ENodeB> grupoNodosB = proyectoActual.getGrupoRANs().get(j).getGrupoNodosB();
                for(int k = 0; k < grupoNodosB.size(); k++){
                    
                    DefaultMutableTreeNode nuevoENodeB = new DefaultMutableTreeNode("eNB: " +  grupoNodosB.get(k).getNombre());
                    nuevaRAN.insert(nuevoENodeB, 0);
                    
                    LinkedList <IndoorLink> grupoLinks = grupoNodosB.get(k).getIndoorLinks();
                    for(int l = 0; l < grupoLinks.size(); l++){
                        DefaultMutableTreeNode nuevoIndoorLink = new DefaultMutableTreeNode("IL: " + grupoLinks.get(l).getNombreLink());
                        nuevoENodeB.insert(nuevoIndoorLink, 0);
                    }
                    LinkedList <ElementoAtenuacion> grupoAtenuadores = grupoNodosB.get(k).getAtenuadores();
                    for(int l = 0; l < grupoAtenuadores.size(); l++){
                        DefaultMutableTreeNode nuevoAtenuador = new DefaultMutableTreeNode("AT: " + grupoAtenuadores.get(l).getNombreDispositivo());
                        nuevoENodeB.insert(nuevoAtenuador, 0);
                    }
                    LinkedList <Antena> grupoAntenas = grupoNodosB.get(k).getAntenas();
                    for(int l = 0; l < grupoAntenas.size(); l++){
                        DefaultMutableTreeNode nuevaAntena = new DefaultMutableTreeNode("ANT: " + grupoAntenas.get(l).getNombreAntena());
                        nuevoENodeB.insert(nuevaAntena, 0);
                    }
                    LinkedList <Radio> grupoRadios = grupoNodosB.get(k).getRadios();
                    for(int l = 0; l < grupoRadios.size(); l++){
                        DefaultMutableTreeNode nuevoRadio = new DefaultMutableTreeNode("RD: " + grupoRadios.get(l).getNombre());
                        nuevoENodeB.insert(nuevoRadio, 0);
                        
                        TarjetaRadio grupoTarjetas[] = grupoRadios.get(l).getInterfacesRadio();
                        for(int m = 0; m < grupoTarjetas.length; m++){
                            System.out.println();
                            try{
                                DefaultMutableTreeNode nuevaTarjetaRadio = new DefaultMutableTreeNode("TR: Slot-"+ grupoTarjetas[m].getNumeroSlot());
                                nuevoRadio.insert(nuevaTarjetaRadio, 0);
                            }catch(NullPointerException NPE){
                                
                            }
                        }
                    }
                }
            }
        }
        arbolProyectos.setModel(modelo);
        arbolProyectos.setCellRenderer(renderArbol);
        expandAllTree(arbolProyectos, true);
    }
    
    public static void expandAllTree(JTree tree, boolean expand) { 
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        expandAllTree(tree, new TreePath(root), expand); 
    } 

    private static void expandAllTree(JTree tree, TreePath parent, boolean expand) { 
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        
        if (node.getChildCount() >= 0) { 
            for (Enumeration e=node.children(); e.hasMoreElements(); ) { 
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

        tabProyectos = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolProyectos = new javax.swing.JTree();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.GridBagLayout());

        tabProyectos.setDoubleBuffered(true);
        tabProyectos.setMinimumSize(new java.awt.Dimension(200, 250));

        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setInheritsPopupMenu(true);

        arbolProyectos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        arbolProyectos.setDoubleBuffered(true);
        arbolProyectos.setDragEnabled(true);
        arbolProyectos.setDropMode(javax.swing.DropMode.ON);
        arbolProyectos.setInheritsPopupMenu(true);
        arbolProyectos.setRowHeight(21);
        arbolProyectos.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(arbolProyectos);

        tabProyectos.addTab("Proyectos", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/package_green.png")), jScrollPane1, "Tab Proyectos"); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(tabProyectos, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolProyectos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabProyectos;
    // End of variables declaration//GEN-END:variables

}
