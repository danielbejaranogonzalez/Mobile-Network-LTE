/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDiagramas.java
 *
 * Created on 21/07/2010, 01:16:59 PM
 */

package Vista.paneles;

import Controlador.ControladorGeneral;
import Libreria.WWHelper;
import Modelo.GEO.CoordenadaGeografica;
import Modelo.MOVIL.EquipoUsuario;
import Modelo.PRO.Proyecto;
import Modelo.PRO.Sitio;
import Vista.menus.MenuClickDerechoMundo;
import Vista.menus.MenuClickDerechoMapa;
import Vista.ventanas.Mapas.VentanaEstiloMapaCloudMade;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.PositionListener;
import gov.nasa.worldwind.event.RenderingEvent;
import gov.nasa.worldwind.event.RenderingListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.event.BackgroundEvent;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactory;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author Bejarano
 */
public class PanelDiagramas extends javax.swing.JPanel implements PositionListener, RenderingListener, MouseListener
{
    /*
     * factor incremental del 0.5 por cada nivel el zoom; para zoom = 2, incremento 224.768
     */
    private static double latitud;
    private static double longitud;
    private static double altitud;
    
    private static double latitudWorld;
    private static double longitudWorld;
    private static double elevacionWorld;

    private ControladorGeneral controlador;
    public final static String UNIT_METRIC = "gov.nasa.worldwind.StatusBar.Metric";
    private String elevationUnit = UNIT_METRIC;

    private PositionEvent positionEvent;
    private MenuClickDerechoMapa menuClickMapa;
    private MenuClickDerechoMundo menuClickMundo;
    
    private LinkedList listaSitios;
    private CoordenadaGeografica[][] coordenadasEnlacesOutdoor;
    
    private JLabel capaSitiosLabel, capaEquiposDeUsuarioLabel;
    
    /** Creates new form PanelDiagramas */
    
    public PanelDiagramas(ControladorGeneral controlador) {

        this.controlador = controlador;
        initComponents();
        listaSitios = new LinkedList();
        coordenadasEnlacesOutdoor = new CoordenadaGeografica[2][0];
        
        WWHelper.setup (world);

        mapa.setAlignmentX(0.6F);
        mapa.setDataProviderCreditShown(true);
        mapa.setName("Mundo"); 
        mapa.setIgnoreRepaint(true);
        mapa.setZoom(15);
        
        final int max = 17;
        TileFactoryInfo info = new TileFactoryInfo(0,15,max, 256, true, true, "http://tile.openstreetmap.org", "x","y","z"){
                public String getTileUrl(int x, int y, int zoom)
                {
                        zoom = max-zoom;
                        String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                        return temp;
                }
        };
        info.setDefaultZoomLevel(17);
        try{
            TileFactory tf = new DefaultTileFactory(info);
            mapa.setZoomSliderVisible(true);
            mapa.setTileFactory(tf);
            mapa.setCenterPosition(new GeoPosition(4.6276, -74.0921));
        }catch(Exception UHE){
            controlador.getBarraEstado().setStatus(0);
        }
        
        mapa.getMainMap().setLayout(null);
        mapa.getMiniMap().setBounds(840, 450, 150, 150);
        mapa.getZoomSlider().setBounds(10, 10, 50, 300);
        
        
        /* // AGREGA CAPA DE OPEN STREET MAPS
        OSMMapnikTransparentLayer ol2 = new OSMMapnikTransparentLayer();
        ol2.setEnabled(true);
        Model modelo = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
        modelo.getLayers().add(ol2);
        world.setModel(modelo);
         */
        Layer layer = world.getModel().getLayers().get(6);
        layer.setEnabled(true);
        Layer layer2 = world.getModel().getLayers().get(8);
        layer2.setEnabled(true);
        Layer layer1 = world.getModel().getLayers().get(11);
        layer1.setEnabled(true);
        System.out.print("Test Layer: " + layer2.getName());
        
        ImageIcon imagenBrujula = new ImageIcon("src/Images/otros/brujula4.png");
        JLabel labelBrujula = new JLabel(imagenBrujula);
        labelBrujula.setBounds((int)(mapa.getSize().getWidth()-75) , 5, 70, 70);
        mapa.getMainMap().add(labelBrujula);
        
        world.redraw();
        double test = world.getModel().getGlobe().getElevationModel().getElevation(Angle.fromDegreesLongitude(-74.1166),Angle.fromDegreesLatitude(4.4529));
        System.out.println("Elevacion: " + test);
        
        menuClickMapa = new MenuClickDerechoMapa(this.controlador);
        //menuClickMundo = new MenuClickDerechoMundo(this.controlador);
        
        world.addPositionListener(this);
        world.addRenderingListener(this);
        
        world.addMouseListener(new MouseListener(){
           public void mouseReleased(MouseEvent Me){
                if(Me.isPopupTrigger() == true){
                       
                    menuClickMapa.show(Me.getComponent(), Me.getX(), Me.getY());
                    menuClickMapa.setObtenerGeoPunto(latitudWorld, longitudWorld, elevacionWorld);
                }
            }
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });
      
      mapa.getMainMap().addMouseMotionListener(new MouseMotionListener() {
            
            public void mouseDragged(MouseEvent e) {
            }
             
            public void mouseMoved(MouseEvent e) {
                int zoomActual = mapa.getMainMap().getZoom();
                GeoPosition geoPoint = mapa.getMainMap().convertPointToGeoPosition(e.getPoint());
                
                latitud = geoPoint.getLatitude();
                longitud = geoPoint.getLongitude();
                
                
                Globe globe = world.getModel().getGlobe();
                ElevationModel currentElevationModel = globe.getElevationModel();
                mapa.getZoomSlider().setBounds(10, 10, 20, 180);
                
                double elev = currentElevationModel.getElevation( Angle.fromDegreesLatitude(latitud), Angle.fromDegreesLongitude(longitud));
                latitudMapaLabel.setText("Latitud: " + (float) latitud + "º");
                longitudMapaLabel.setText("Longitud: " + (float)longitud  + "º");
                altitudMapaLabel.setText("Altitud: " + (float) elev + "m");
                
            }
      });
      
      mapa.getMainMap().addMouseListener(new MouseListener(){

            public void mouseMoved(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent Me) {
                try{
                        if(Me.isPopupTrigger() == true){
                           Globe globe = world.getModel().getGlobe();
                           ElevationModel currentElevationModel = globe.getElevationModel();
                           double elev = currentElevationModel.getElevation( Angle.fromDegreesLatitude(latitud), Angle.fromDegreesLongitude(longitud));
                            
                           menuClickMapa.show(Me.getComponent(), Me.getX(), Me.getY());
                           menuClickMapa.setObtenerGeoPunto(latitud, longitud, elev);
                        }
                    }catch(NullPointerException NPE){
                        System.out.println("TEST ERROR CLICK MENU");
                    }
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }

      });
      // agrega una linea...

         WaypointPainter waypointPainter = new WaypointPainter();
         waypointPainter.setRenderer(new WaypointRenderer() {
            public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
                return true;
            }
         });
         //waypointPainter.getWaypoints().add(new Waypoint(0, 0));
         mapa.getMainMap().setOverlayPainter(new Painter<JXMapViewer>() {
            public void paint(Graphics2D g, JXMapViewer map, int width, int height) {
                
                    // Dibujo de enlaces
                    JXMapViewer mainMap = mapa.getMainMap();
                    Rectangle mainMapBounds = mainMap.getViewportBounds();
                    
                    g = (Graphics2D) g.create();
                    Rectangle rect = map.getViewportBounds();
                    g.translate(-rect.x, -rect.y);
                    float dash[] = {8.0f};
                    g.setStroke(new BasicStroke(3.0f, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_ROUND, 10.0f, dash, 5.0f));
                    g.setColor(new Color(0,255,0,255));
                    
                    // convert to Point2Ds
                    Point2D upperLeft2D = mainMapBounds.getLocation();
                    Point2D lowerRight2D = new Point2D.Double(upperLeft2D.getX() + mainMapBounds.getWidth(),
                            upperLeft2D.getY() + mainMapBounds.getHeight());
                    
                    for(int j=0; j < coordenadasEnlacesOutdoor[0].length; j++){
                        upperLeft2D = mainMapBounds.getLocation();
                        lowerRight2D = new Point2D.Double(upperLeft2D.getX() + mainMapBounds.getWidth(),
                            upperLeft2D.getY() + mainMapBounds.getHeight());
                        upperLeft2D = map.getTileFactory().geoToPixel(new GeoPosition(coordenadasEnlacesOutdoor[0][j].getLatitudDecimal(), coordenadasEnlacesOutdoor[0][j].getLongitudDecimal()), map.getZoom());
                        lowerRight2D = map.getTileFactory().geoToPixel(new GeoPosition(coordenadasEnlacesOutdoor[1][j].getLatitudDecimal(), coordenadasEnlacesOutdoor[1][j].getLongitudDecimal()), map.getZoom());
                        
                        g.drawLine((int)upperLeft2D.getX(), (int)upperLeft2D.getY(), (int)(lowerRight2D.getX()), (int)(lowerRight2D.getY()));
                    }
                    g.dispose();
            }
        }); 
      /*
       WMSService wms = new WMSService();
        wms.setLayer("BMNG");
        wms.setBaseUrl("http://wms.jpl.nasa.gov/wms.cgi?");
        TileFactory fact = new WMSTileFactory(wms);
        mapa.setTileFactory(fact);
       * ?
       */
      System.out.println("TEST Fin Panel Diagramas");
    }

    public void establecerCoordenadasEnlacesOutdoor( CoordenadaGeografica[][] coordenadasEnlacesOutdoor){
        this.coordenadasEnlacesOutdoor = coordenadasEnlacesOutdoor;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        Mundo = new javax.swing.JTabbedPane();
        PanelMapa = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        barraMapa = new javax.swing.JToolBar();
        zoomMas = new javax.swing.JButton();
        zoomMenos = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        mapaCalles = new javax.swing.JButton();
        tileArrobaHome = new javax.swing.JButton();
        CycleMap = new javax.swing.JButton();
        mapQuest = new javax.swing.JButton();
        HybridoGoogleMapButton = new javax.swing.JButton();
        MapGoogleMapButton = new javax.swing.JButton();
        TerrainGoogleMapButton = new javax.swing.JButton();
        cloudMadeBoton = new javax.swing.JButton();
        mapa = new org.jdesktop.swingx.JXMapKit();
        jPanel1 = new javax.swing.JPanel();
        longitudMapaLabel = new javax.swing.JLabel();
        latitudMapaLabel = new javax.swing.JLabel();
        altitudMapaLabel = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        panelWindWorld = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelLatitud = new javax.swing.JLabel();
        labelLongitud = new javax.swing.JLabel();
        labelElevacion = new javax.swing.JLabel();
        world = new gov.nasa.worldwind.awt.WorldWindowGLJPanel();
        jSeparator4 = new javax.swing.JSeparator();

        setLayout(new java.awt.GridBagLayout());

        Mundo.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        PanelMapa.setLayout(new java.awt.GridBagLayout());

        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(0.02F);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        PanelMapa.add(jSeparator2, gridBagConstraints);

        barraMapa.setRollover(true);

        zoomMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/zoom_in.png"))); // NOI18N
        zoomMas.setFocusable(false);
        zoomMas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomMas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        zoomMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomMasActionPerformed(evt);
            }
        });
        barraMapa.add(zoomMas);

        zoomMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/zoom_out.png"))); // NOI18N
        zoomMenos.setFocusable(false);
        zoomMenos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomMenos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        zoomMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomMenosActionPerformed(evt);
            }
        });
        barraMapa.add(zoomMenos);
        barraMapa.add(jSeparator3);

        mapaCalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/osmdata_small.png"))); // NOI18N
        mapaCalles.setToolTipText("Servidor: OpenStreetMap");
        mapaCalles.setFocusable(false);
        mapaCalles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mapaCalles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mapaCalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapaCallesActionPerformed(evt);
            }
        });
        barraMapa.add(mapaCalles);

        tileArrobaHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/osmdata_small.png"))); // NOI18N
        tileArrobaHome.setToolTipText("Servidor: Tile @ Home");
        tileArrobaHome.setFocusable(false);
        tileArrobaHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tileArrobaHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tileArrobaHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileArrobaHomeActionPerformed(evt);
            }
        });
        barraMapa.add(tileArrobaHome);

        CycleMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/opencyclemap 16x16.png"))); // NOI18N
        CycleMap.setToolTipText("Servidor: OpenCycleMap");
        CycleMap.setFocusable(false);
        CycleMap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CycleMap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CycleMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CycleMapActionPerformed(evt);
            }
        });
        barraMapa.add(CycleMap);

        mapQuest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/Mapquest_2010.png"))); // NOI18N
        mapQuest.setToolTipText("Servidor MapQuest");
        mapQuest.setFocusable(false);
        mapQuest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mapQuest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mapQuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapQuestActionPerformed(evt);
            }
        });
        barraMapa.add(mapQuest);

        HybridoGoogleMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/google_icon.png"))); // NOI18N
        HybridoGoogleMapButton.setToolTipText("Servidor: Google Map Hibrido");
        HybridoGoogleMapButton.setFocusable(false);
        HybridoGoogleMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        HybridoGoogleMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        HybridoGoogleMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HybridoGoogleMapButtonActionPerformed(evt);
            }
        });
        barraMapa.add(HybridoGoogleMapButton);

        MapGoogleMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/google_icon.png"))); // NOI18N
        MapGoogleMapButton.setToolTipText("Servidor: Google Map Mapas");
        MapGoogleMapButton.setFocusable(false);
        MapGoogleMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MapGoogleMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MapGoogleMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MapGoogleMapButtonActionPerformed(evt);
            }
        });
        barraMapa.add(MapGoogleMapButton);

        TerrainGoogleMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/google_icon.png"))); // NOI18N
        TerrainGoogleMapButton.setToolTipText("Servidor: Google Map Terreno");
        TerrainGoogleMapButton.setFocusable(false);
        TerrainGoogleMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TerrainGoogleMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        TerrainGoogleMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerrainGoogleMapButtonActionPerformed(evt);
            }
        });
        barraMapa.add(TerrainGoogleMapButton);

        cloudMadeBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/cloudmade.png"))); // NOI18N
        cloudMadeBoton.setToolTipText("Servidor: CloudMade");
        cloudMadeBoton.setFocusable(false);
        cloudMadeBoton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cloudMadeBoton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cloudMadeBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cloudMadeBotonActionPerformed(evt);
            }
        });
        barraMapa.add(cloudMadeBoton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        PanelMapa.add(barraMapa, gridBagConstraints);

        mapa.setBackground(new java.awt.Color(255, 255, 255));
        mapa.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);
        mapa.setForeground(new java.awt.Color(255, 255, 255));
        mapa.setMiniMapVisible(false);
        mapa.setZoomButtonsVisible(false);
        mapa.setAddressLocationShown(false);
        mapa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        PanelMapa.add(mapa, gridBagConstraints);

        longitudMapaLabel.setText("Longitud");

        latitudMapaLabel.setText("Latitud: ");

        altitudMapaLabel.setText("Altitud:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(latitudMapaLabel)
                .addGap(85, 85, 85)
                .addComponent(longitudMapaLabel)
                .addGap(94, 94, 94)
                .addComponent(altitudMapaLabel)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(latitudMapaLabel)
                    .addComponent(longitudMapaLabel)
                    .addComponent(altitudMapaLabel)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        PanelMapa.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        PanelMapa.add(jSeparator5, gridBagConstraints);

        Mundo.addTab("Cartografia", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/map.png")), PanelMapa, ""); // NOI18N

        panelWindWorld.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelWindWorld.add(jSeparator1, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/layers.png"))); // NOI18N
        jButton2.setToolTipText("Capas");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelWindWorld.add(jToolBar1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(600, 19));

        labelLatitud.setText(" Latitud: 0.00º");

        labelLongitud.setText("Longitud: 0.00º");

        labelElevacion.setText("Elevacion: 0.00 metros");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(labelLatitud)
                .addGap(55, 55, 55)
                .addComponent(labelLongitud)
                .addGap(55, 55, 55)
                .addComponent(labelElevacion))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLatitud)
                    .addComponent(labelLongitud)
                    .addComponent(labelElevacion)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelWindWorld.add(jPanel2, gridBagConstraints);

        world.setForeground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelWindWorld.add(world, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        panelWindWorld.add(jSeparator4, gridBagConstraints);

        Mundo.addTab("NASA World Wind", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/globe-green.png")), panelWindWorld); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(Mundo, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

private void cloudMadeBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cloudMadeBotonActionPerformed
        
        new VentanaEstiloMapaCloudMade(new JFrame(), true, controlador);
}//GEN-LAST:event_cloudMadeBotonActionPerformed

private void TerrainGoogleMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerrainGoogleMapButtonActionPerformed
        // TODO add your handling code here:
               // TODO add your handling code here:
        // TODO add your handling code here:
         latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://mt1.google.com/vt/lyrs=p", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"&x="+x+"&y="+y+"&z="+zoom+"    ";
                return temp;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
        
}//GEN-LAST:event_TerrainGoogleMapButtonActionPerformed

private void MapGoogleMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MapGoogleMapButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
         latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://mt0.google.com/vt/", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String url = this.baseURL +"&x="+x+"&y="+y+"&z="+zoom+"";
                return url;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
        
}//GEN-LAST:event_MapGoogleMapButtonActionPerformed

private void HybridoGoogleMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HybridoGoogleMapButtonActionPerformed
        // TODO add your handling code here:
        latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        try{
        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://mt0.google.com/vt/lyrs=y", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"&x="+x+"&y="+y+"&z="+zoom+"";
                return temp;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
        }catch(Exception UHE){
            JOptionPane.showMessageDialog(null, "'Se ha perdido la coneccion con el servidor de mapas de Google'", "Error 006: UnknownHostException", JOptionPane.ERROR_MESSAGE);
        }
        
}//GEN-LAST:event_HybridoGoogleMapButtonActionPerformed

private void mapQuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapQuestActionPerformed
// TODO add your handling code here:
    latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://otile1.mqcdn.com/tiles/1.0.0/osm", "z","x","y") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                return temp;
            }
        };
        
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
}//GEN-LAST:event_mapQuestActionPerformed

private void CycleMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CycleMapActionPerformed
        latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://andy.sandbox.cloudmade.com/tiles/cycle", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                return temp;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
}//GEN-LAST:event_CycleMapActionPerformed

private void tileArrobaHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileArrobaHomeActionPerformed
        // TODO add your handling code here:
        latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://tah.openstreetmap.org/Tiles/tile", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                return temp;
            }
        };

        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
}//GEN-LAST:event_tileArrobaHomeActionPerformed

private void mapaCallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapaCallesActionPerformed
        // TODO add your handling code here:

        latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        try{
        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true, "http://tile.openstreetmap.org", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                return temp;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
        }catch(Exception UHE){
             JOptionPane.showMessageDialog(null, "'Se ha perdido la coneccion con el servidor de mapas de Open Street Maps'", "Error 006: UnknownHostException", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_mapaCallesActionPerformed

private void zoomMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomMenosActionPerformed

        if(mapa.getZoomSlider().getValue()<=18 && mapa.getZoomSlider().getValue()>=0){
            mapa.setZoom(mapa.getZoomSlider().getValue()+1);
        }
}//GEN-LAST:event_zoomMenosActionPerformed

private void zoomMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomMasActionPerformed

        if(mapa.getZoomSlider().getValue()<=18 && mapa.getZoomSlider().getValue()>=0){
            mapa.setZoom(mapa.getZoomSlider().getValue()-1);
        }
}//GEN-LAST:event_zoomMasActionPerformed

    public void setCloudMadeStilo(int numeroEstilo){
        latitud = mapa.getCenterPosition().getLatitude();
        longitud = mapa.getCenterPosition().getLongitude();

        final int max = 18;
        TileFactoryInfo info = new TileFactoryInfo(0,16,max, 256, true, true,
                "http://a.tile.cloudmade.com/8bafab36916b5ce6b4395ede3cb9ddea/" + numeroEstilo + "/256/", "x","y","z") {
            public String getTileUrl(int x, int y, int zoom) {
                zoom = max-zoom;
                String temp = this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
                return temp;
            }
        };
        info.setDefaultZoomLevel(mapa.getZoomSlider().getValue());
        TileFactory tf = new DefaultTileFactory(info);
        mapa.setTileFactory(tf);
        mapa.setCenterPosition(new GeoPosition(latitud, longitud));
        actualizarSitios();
    }
        
    public void actualizarEUs(){
        
        JLabel capaEquiposDeUsuario = new JLabel();
        
        int x, y; 
        int zoomActual = mapa.getMainMap().getZoom();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> listaProyectos = controlador.getLibroDeProyectos().getProyectos();
        
        for(int i=0; i < listaProyectos.size(); i++){
            if(listaProyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                
                LinkedList <EquipoUsuario> equiposActuales = listaProyectos.get(i).getGrupoEUs();
                capaEquiposDeUsuario.setBounds(0, 0, (int)mapa.getSize().getWidth(), (int)mapa.getSize().getHeight());
                
                for(int j = 0; j < equiposActuales.size(); j++){
                    
                    Point2D punto = mapa.getMainMap().convertGeoPositionToPoint(new GeoPosition((equiposActuales.get(j).getCoordenadaEU().getLatitudDecimal()),(equiposActuales.get(j).getCoordenadaEU().getLongitudDecimal())));
                        x = (int)punto.getX();
                        y = (int)punto.getY(); 
                    
                    ImageIcon imageEU;    
                    if(zoomActual < 2){
                        imageEU = new ImageIcon("src/Images/iconos32x32/blackberry_white.png");
                        if(equiposActuales.get(j).getTipoEU().equals("Telefono Movil")){
                            imageEU = new ImageIcon("src/Images/iconos32x32/blackberry_white.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("PC")){
                            imageEU = new ImageIcon("src/Images/iconos32x32/laptop.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("Tablet")){
                            imageEU = new ImageIcon("src/Images/iconos32x32/ipad.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("Camara CCTV 4G")){
                            imageEU = new ImageIcon("src/Images/iconos32x32/cctv_camera.png");
                        }
                        
                            JLabel eUActualToPaint = new JLabel(imageEU);
                            eUActualToPaint.setBounds((x-32), (y-24), 64, 48);
                            eUActualToPaint.setText("EU" + j+":" + equiposActuales.get(j).getNombreEstacionMovil());
                            eUActualToPaint.setHorizontalTextPosition(JLabel.CENTER);
                            eUActualToPaint.setVerticalTextPosition(JLabel.BOTTOM);
                            eUActualToPaint.setFont(new Font("Arial",Font.BOLD,10));
                            capaEquiposDeUsuario.add(eUActualToPaint);
                            
                    }else if(zoomActual >= 2){
                        imageEU = new ImageIcon("src/Images/iconos16x16/blackberry_white.png");
                        if(equiposActuales.get(j).getTipoEU().equals("Telefono Movil")){
                            imageEU = new ImageIcon("src/Images/iconos16x16/blackberry_white.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("PC")){
                            imageEU = new ImageIcon("src/Images/iconos16x16/laptop.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("Tablet")){
                            imageEU = new ImageIcon("src/Images/iconos16x16/ipad.png");
                        }else if(equiposActuales.get(j).getTipoEU().equals("Camara CCTV 4G")){
                            imageEU = new ImageIcon("src/Images/iconos16x16/cctv_camera.png");
                        }
                        capaEquiposDeUsuario.setBounds(0, 0, (int)mapa.getSize().getWidth(), (int)mapa.getSize().getHeight());
                        JLabel eUActualToPaint = new JLabel(imageEU);
                        eUActualToPaint.setBounds((x-8), (y-8), 16, 16);
                        capaEquiposDeUsuario.add(eUActualToPaint);
                    } 
                }
            }
        }
        capaEquiposDeUsuarioLabel = capaEquiposDeUsuario;
    }
    
    public void actualizarSitios(){
        JLabel capaSitios = new JLabel();
        int x, y;
        int zoomActual = mapa.getMainMap().getZoom();
        
        String proyectoPredeterminado = controlador.getLibroDeProyectos().getProyectoPredeterminado();
        LinkedList <Proyecto> listaProyectos = controlador.getLibroDeProyectos().getProyectos();
        for(int i=0; i < listaProyectos.size(); i++){
            if(listaProyectos.get(i).getNombre().equals(proyectoPredeterminado)){
                //se encontro e proyecto predeterminado
                LinkedList <Sitio> sitios = listaProyectos.get(i).getGrupoSitios();
                capaSitios.setBounds(0, 0, (int)mapa.getSize().getWidth(), (int)mapa.getSize().getHeight());
                for(int j = 0; j < sitios.size(); j++){
                    
                    Point2D punto = mapa.getMainMap().convertGeoPositionToPoint(new GeoPosition((sitios.get(j).getCoordenadaSitio().getLatitudDecimal()),(sitios.get(j).getCoordenadaSitio().getLongitudDecimal())));
                    x = (int)punto.getX();
                    y = (int)punto.getY(); 
                        
                    ImageIcon imageSitio;    
                    if(zoomActual < 2){
                        imageSitio = new ImageIcon("src/Images/iconos32x32/building.png");
                        if(sitios.get(j).getTipoSitio().equals("Estacion Base")){
                            imageSitio = new ImageIcon("src/Images/iconos32x32/eNB5-32.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Armario de Conexiones Outdoor")){
                            imageSitio = new ImageIcon("src/Images/iconos32x32/drawer.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Central de Conmutacion")){
                            imageSitio = new ImageIcon("src/Images/iconos32x32/house_two.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Network Operations Center (NOC)")){
                            imageSitio = new ImageIcon("src/Images/iconos32x32/building.png");
                        }
                        
                        JLabel sitioActualToPaint = new JLabel(imageSitio);
                        sitioActualToPaint.setBounds((x-32), (y-24), 64, 48);
                        sitioActualToPaint.setText("ST" + j+":" + sitios.get(j).getNombreSitio());
                        sitioActualToPaint.setHorizontalTextPosition(JLabel.CENTER);
                        sitioActualToPaint.setVerticalTextPosition(JLabel.BOTTOM);
                        sitioActualToPaint.setFont(new Font("Arial",Font.BOLD,10));
                        capaSitios.add(sitioActualToPaint);
                        
                    }else if(zoomActual >= 2){
                        imageSitio = new ImageIcon("src/Images/iconos16x16/building (1).png");
                        if(sitios.get(j).getTipoSitio().equals("Estacion Base")){
                            imageSitio = new ImageIcon("src/Images/iconos16x16/eNB5-16.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Armario de Conexiones Outdoor")){
                            imageSitio = new ImageIcon("src/Images/iconos16x16/drawer.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Central de Conmutacion")){
                            imageSitio = new ImageIcon("src/Images/iconos16x16/house_two.png");
                        }else if(sitios.get(j).getTipoSitio().equals("Network Operations Center (NOC)")){
                            imageSitio = new ImageIcon("src/Images/iconos16x16/building (1).png");
                        }
                        capaSitios.setBounds(0, 0, (int)mapa.getSize().getWidth(), (int)mapa.getSize().getHeight());
                        JLabel sitioActualToPaint = new JLabel(imageSitio);
                        sitioActualToPaint.setBounds((x-8), (y-8), 16, 16);
                        capaSitios.add(sitioActualToPaint);
                    }
                }
            }
        }
        capaSitiosLabel = capaSitios;
    }

    public void actualizarCapasEnMapa() {
        try{
             mapa.getMainMap().removeAll();
        }catch(java.lang.ArrayIndexOutOfBoundsException AIOBE){
             System.out.println("TEST 1: Error al remover capa 1");
        }catch(IllegalArgumentException IAE){
             System.out.println("TEST 2: Error al agregar capa 2");
        }
        ImageIcon imagenBrujula = new ImageIcon("src/Images/otros/brujula5.png");
        JLabel labelBrujula = new JLabel(imagenBrujula);
        labelBrujula.setBounds((int)(mapa.getSize().getWidth()-95) , 5, 90, 90);
        mapa.getMiniMap().setBounds((int)mapa.getSize().getWidth()-160, (int)mapa.getSize().getHeight()-160, 150, 150);
        
        try{
            mapa.getMainMap().add(capaSitiosLabel, 0);
            mapa.getMainMap().add(capaEquiposDeUsuarioLabel, 1);
            mapa.getMainMap().add(labelBrujula);
            mapa.getMainMap().repaint();
        }catch(NullPointerException NULL){
             System.out.println("Error a l agregar capas de acceso");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CycleMap;
    private javax.swing.JButton HybridoGoogleMapButton;
    private javax.swing.JButton MapGoogleMapButton;
    private javax.swing.JTabbedPane Mundo;
    private javax.swing.JPanel PanelMapa;
    private javax.swing.JButton TerrainGoogleMapButton;
    private javax.swing.JLabel altitudMapaLabel;
    private javax.swing.JToolBar barraMapa;
    private javax.swing.JButton cloudMadeBoton;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelElevacion;
    private javax.swing.JLabel labelLatitud;
    private javax.swing.JLabel labelLongitud;
    private javax.swing.JLabel latitudMapaLabel;
    private javax.swing.JLabel longitudMapaLabel;
    private javax.swing.JButton mapQuest;
    private org.jdesktop.swingx.JXMapKit mapa;
    private javax.swing.JButton mapaCalles;
    private javax.swing.JPanel panelWindWorld;
    private javax.swing.JButton tileArrobaHome;
    private gov.nasa.worldwind.awt.WorldWindowGLJPanel world;
    private javax.swing.JButton zoomMas;
    private javax.swing.JButton zoomMenos;
    // End of variables declaration//GEN-END:variables

    public void moved(PositionEvent event) {
        //panelWindWorld.handleCursorPositionChange(event);
        Position newPos = event.getPosition();
        positionEvent = event;
        
        try{

            newPos.getLatitude().fromDegreesLatitude(4.6276);
            newPos.getLongitude().fromDegreesLongitude(-74.0921);
            latitudWorld = newPos.getLatitude().getDegrees();
            longitudWorld = newPos.getLongitude().getDegrees();
            elevacionWorld = newPos.getElevation();

            //String las = makeAngleDescription("Lat", newPos.getLatitude());

            labelLatitud.setText(" Latitud: " + (float)latitudWorld + " º");
            labelLongitud.setText("Longitud: " + (float)longitudWorld + " º");
            labelElevacion.setText("Elevacion: " + (float)elevacionWorld + " Metros");

        }catch(NullPointerException NPE){
        }
    }

    public void stageChanged(RenderingEvent re) {
        //System.out.println("Test Listeners 1");
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Test Listeners 2");
    }

    public void mousePressed(MouseEvent e) {
        //System.out.println("Test Listeners 3");
    }

    public void mouseReleased(MouseEvent e) {
       if(e.getModifiers() == 4){
           System.out.println("Test MouseListener" + e.getModifiers());
        }
    }

    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void started(BackgroundEvent be) {
    }
    public void doInBackground(BackgroundEvent be) {
    }
    public void done(BackgroundEvent be) {
    }
    public void process(BackgroundEvent be) {
    }
}
