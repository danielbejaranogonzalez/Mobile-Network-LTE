/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VentanaRunSimulacion.java
 *
 * Created on 22-dic-2011, 0:18:37
 */
package Vista.ventanas.Simulacion;

import Controlador.ControladorGeneral;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author User
 */
public class VentanaRunSimulacion extends javax.swing.JFrame {

    private TimeSeries pathsLossDownSeries[];
    private TimeSeries pathsLossUpSeries[];
    private TimeSeries pathsRSSI_DownSeries[];
    private TimeSeries pathsRSSI_UpSeries[];
    private TimeSeries pathsCNR_DownSeries[];
    private TimeSeries pathsCNR_UpSeries[];
    private TimeSeries sensibilidad_DownSeries[];
    private TimeSeries sensibilidad_UpSeries[];
    
    private XYSeries pathsPowerDelayProfileSeries[];
    private Date fecha;
    
    private ControladorGeneral controlador;
    
    /** Creates new form VentanaRunSimulacion */
    public VentanaRunSimulacion(ControladorGeneral controlador, int cantidadPaths) {
        initComponents();
        this.controlador = controlador;
        
        String [] paths = new String[cantidadPaths];
        for(int i = 0; i < cantidadPaths; i++){
            paths[i] = "" + (i+1);
        }
        listaPaths.setModel(new DefaultComboBoxModel(paths));
        
        fecha = new Date();
        pathsLossDownSeries = new TimeSeries[cantidadPaths];
        pathsLossUpSeries = new TimeSeries[cantidadPaths];
        pathsRSSI_DownSeries = new TimeSeries[cantidadPaths];
        pathsRSSI_UpSeries = new TimeSeries[cantidadPaths];
        pathsCNR_DownSeries = new TimeSeries[cantidadPaths];
        pathsCNR_UpSeries = new TimeSeries[cantidadPaths];
        pathsPowerDelayProfileSeries = new XYSeries[cantidadPaths];
        
        sensibilidad_DownSeries = new TimeSeries[1];
        sensibilidad_UpSeries = new TimeSeries[1];
                
        XYDataset datasetPathLossDown = createDataset(pathsLossDownSeries);
        JFreeChart chartPathLossDown = createChart1(datasetPathLossDown,"Path Loss - DownStream","Tiempo [hh:mm:ss]","Perdida [dB]", "hh:mm:ss");
        chartPathLossDown.setBackgroundPaint(new Color(255,255,255,0));
        XYDataset datasetPathLossUp = createDataset(pathsLossUpSeries);
        JFreeChart chartPathLossUp = createChart1(datasetPathLossUp, "Path Loss - UpStream","Tiempo [hh:mm:ss]","Perdida [dB]", "hh:mm:ss");
        chartPathLossUp.setBackgroundPaint(new Color(255,255,255,0));
        XYDataset datasetPathsRSSI_Down = createDataset(pathsRSSI_DownSeries, sensibilidad_DownSeries);
        JFreeChart chartPathsRSSI_Down = createChart1(datasetPathsRSSI_Down, "Receive Signal Strength Indication (RSSI) - DownStream","Tiempo [hh:mm:ss]","Potencia [dBm]", "hh:mm:ss");
        chartPathsRSSI_Down.setBackgroundPaint(new Color(255,255,255,0));
        XYDataset datasetPathsRSSI_Up = createDataset(pathsRSSI_UpSeries, sensibilidad_UpSeries);
        JFreeChart chartPathsRSSI_Up = createChart1(datasetPathsRSSI_Up, "Receive Signal Strength Indication (RSSI) - UpStream","Tiempo [hh:mm:ss]","Potencia [dBm]", "hh:mm:ss");
        chartPathsRSSI_Up.setBackgroundPaint(new Color(255,255,255,0));
        
        XYDataset datasetPathsCNR_Down = createDataset(pathsCNR_DownSeries);
        JFreeChart chartPathsCNR_Down = createChart1(datasetPathsCNR_Down, "Carrier to Noise Ratio (CNR) - DownStream","Tiempo [hh:mm:ss]","C/N [dB]", "hh:mm:ss");
        chartPathsCNR_Down.setBackgroundPaint(new Color(255,255,255,0));
        XYDataset datasetPathsCNR_Up = createDataset(pathsCNR_UpSeries);
        JFreeChart chartPathsCNR_Up = createChart1(datasetPathsCNR_Up, "Carrier to Noise Ratio (CNR) - UpStream","Tiempo [hh:mm:ss]","C/N [dB]", "hh:mm:ss");
        chartPathsCNR_Up.setBackgroundPaint(new Color(255,255,255,0));
        
        XYDataset datasetPathsPDP = createDataset(pathsPowerDelayProfileSeries);
        JFreeChart chartPathsPDP = createChart2(datasetPathsPDP, "Power Delay Profile (PDP)","Retardo de Tiempo [us]","Potencia de Grupo [dB]");
        chartPathsPDP.setBackgroundPaint(new Color(255,255,255,0));
        
        
        JPanel graficaPathLossDown = new ChartPanel(chartPathLossDown);
        panelGraficaPathLossDown.add(graficaPathLossDown);
        JPanel graficaPathLossUp = new ChartPanel(chartPathLossUp);
        panelGraficaPathLossUp.add(graficaPathLossUp);
        JPanel graficaPathRSSI_Down = new ChartPanel(chartPathsRSSI_Down);
        panelGraficaRSSI_Down.add(graficaPathRSSI_Down);
        JPanel graficaPathRSSI_Up = new ChartPanel(chartPathsRSSI_Up);
        panelGraficaRSSI_Up.add(graficaPathRSSI_Up);
        JPanel graficaPathCNR_Down = new ChartPanel(chartPathsCNR_Down);
        panelGraficaCNR_Down.add(graficaPathCNR_Down);
        JPanel graficaPathCNR_Up = new ChartPanel(chartPathsCNR_Up);
        panelGraficaCNR_Up.add(graficaPathCNR_Up);
        
        JPanel graficaPathPDP = new ChartPanel(chartPathsPDP);
        panelPowerDelayProfile.add(graficaPathPDP);
    }
    
    private JFreeChart createChart1(XYDataset dataset, String nombre, String unidadesInferiores, String unidadesLaterales, String formato) {

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(nombre,unidadesInferiores,unidadesLaterales,
            dataset,true,true,true);
        chart.setBackgroundPaint(Color.white);
        
        //final StandardLegend sl = (StandardLegend) chart.getLegend();
        //sl.setDisplaySeriesShapes(true);
        final XYPlot plot = chart.getXYPlot();
        //plot.setOutlinePaint(null);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.GREEN);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(false);
        
        final XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            //rr.setPlotShapes(true);
            rr.setShapesFilled(true);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));
           }
        
        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat(formato));
        
        return chart;
    }
    
    private JFreeChart createChart2(XYDataset dataset, String nombre, String unidadesInferiores, String unidadesLaterales) {

        final JFreeChart chart = ChartFactory.createXYLineChart(nombre,unidadesInferiores,unidadesLaterales,
            dataset,PlotOrientation.VERTICAL,true,true,true);
        chart.setBackgroundPaint(Color.BLACK);
        
        //final StandardLegend sl = (StandardLegend) chart.getLegend();
        //sl.setDisplaySeriesShapes(true);
        final XYPlot plot = chart.getXYPlot();
        //plot.setOutlinePaint(null);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        return chart;
    }
    
    private XYDataset createDataset(TimeSeries timeseries[]){

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.setDomainIsPointsInTime(true);
        
            for(int i = 0; i < timeseries.length;i++){
                timeseries[i] = new TimeSeries("Path" + (i+1), Second.class);
                //path1.add(new Second(0, 0, 0, 7, 12, 2011), 0.0);
                dataset.addSeries(timeseries[i]);
            }
        
        return dataset;
    }
    
    private XYDataset createDataset(TimeSeries timeseries[], TimeSeries timeSeriesSensibilidad[]){
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.setDomainIsPointsInTime(true);
        
            for(int i = 0; i < timeseries.length;i++){
                timeseries[i] = new TimeSeries("Path" + (i+1), Second.class);
                //path1.add(new Second(0, 0, 0, 7, 12, 2011), 0.0);
                dataset.addSeries(timeseries[i]);
            }
            
            for(int i = 0; i < timeSeriesSensibilidad.length;i++){
                timeSeriesSensibilidad[i] = new TimeSeries("Sensibilidad Rx", Second.class);
                //path1.add(new Second(0, 0, 0, 7, 12, 2011), 0.0);
                dataset.addSeries(timeSeriesSensibilidad[i]);
            }
        
        /*
        fecha = new Date();
        timeSeriesSensibilidad.addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), -80);
         */
        return dataset;
    }
    
    private XYDataset createDataset(XYSeries series[]){
    
        final XYSeriesCollection dataset = new XYSeriesCollection();
        
        for(int i = 0; i < series.length;i++){
             series[i] = new XYSeries("Path" + (i+1));
             //series[i].add(1.1, 1.2);
             dataset.addSeries(series[i]);
        }
        
        return dataset;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        botonCanelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        distanciaEnTierra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        distanciaLineal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        listaPaths = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelGraficasGranEscala = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        panelGraficaPathLossDown = new javax.swing.JPanel();
        panelGraficaPathLossUp = new javax.swing.JPanel();
        panelGraficaRSSI_Down = new javax.swing.JPanel();
        panelGraficaCNR_Up = new javax.swing.JPanel();
        panelGraficaCNR_Down = new javax.swing.JPanel();
        panelGraficaRSSI_Up = new javax.swing.JPanel();
        panelGraficasPequeñaEscala = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        panelPowerDelayProfile = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        RSSI_DownText = new javax.swing.JTextField();
        CNR_DownText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PathLoss_DownText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PathLoss_UpText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        RSSI_UpText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        CNR_UpText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Simulacion de Path");
        setIconImage(new ImageIcon("src/Images/iconos16x16/control_play_blue.png").getImage());
        setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        botonCanelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/cross-script.png"))); // NOI18N
        botonCanelar.setText(" Cancelar");
        botonCanelar.setName("botonCanelar"); // NOI18N
        botonCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCanelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(705, Short.MAX_VALUE)
                .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Resultados Generales"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Distancia del enlace en tierra:");
        jLabel1.setName("jLabel1"); // NOI18N

        distanciaEnTierra.setEditable(false);
        distanciaEnTierra.setName("distanciaEnTierra"); // NOI18N

        jLabel3.setText("Distancia entre el ENodeB y el UE:");
        jLabel3.setName("jLabel3"); // NOI18N

        distanciaLineal.setEditable(false);
        distanciaLineal.setName("distanciaLineal"); // NOI18N

        jLabel4.setText("[m]");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel6.setText("[m]");
        jLabel6.setName("jLabel6"); // NOI18N

        listaPaths.setName("listaPaths"); // NOI18N
        listaPaths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaPathsActionPerformed(evt);
            }
        });

        jLabel7.setText("Numero del Path:");
        jLabel7.setName("jLabel7"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanciaEnTierra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanciaLineal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listaPaths, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(listaPaths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(distanciaEnTierra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(distanciaLineal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        panelGraficasGranEscala.setName("panelGraficasGranEscala"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        panelGraficaPathLossDown.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaPathLossDown.setName("panelGraficaPathLossDown"); // NOI18N
        panelGraficaPathLossDown.setLayout(new java.awt.GridLayout(1, 0));

        panelGraficaPathLossUp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaPathLossUp.setName("panelGraficaPathLossUp"); // NOI18N
        panelGraficaPathLossUp.setLayout(new java.awt.GridLayout(1, 0));

        panelGraficaRSSI_Down.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaRSSI_Down.setName("panelGraficaRSSI_Down"); // NOI18N
        panelGraficaRSSI_Down.setLayout(new java.awt.GridLayout(1, 0));

        panelGraficaCNR_Up.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaCNR_Up.setName("panelGraficaCNR_Up"); // NOI18N
        panelGraficaCNR_Up.setLayout(new java.awt.GridLayout(1, 0));

        panelGraficaCNR_Down.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaCNR_Down.setName("panelGraficaCNR_Down"); // NOI18N
        panelGraficaCNR_Down.setLayout(new java.awt.GridLayout(1, 0));

        panelGraficaRSSI_Up.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelGraficaRSSI_Up.setName("panelGraficaRSSI_Up"); // NOI18N
        panelGraficaRSSI_Up.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelGraficaCNR_Up, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGraficaCNR_Down, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGraficaRSSI_Up, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGraficaRSSI_Down, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGraficaPathLossUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGraficaPathLossDown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGraficaPathLossDown, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficaPathLossUp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficaRSSI_Down, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficaRSSI_Up, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficaCNR_Down, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraficaCNR_Up, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGraficasGranEscala.setViewportView(jPanel2);

        jTabbedPane1.addTab("Parametros de Gran Escala", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/action_log.png")), panelGraficasGranEscala, "Parametros de Gran Escala"); // NOI18N

        panelGraficasPequeñaEscala.setToolTipText("");
        panelGraficasPequeñaEscala.setName("panelGraficasPequeñaEscala"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        panelPowerDelayProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelPowerDelayProfile.setName("panelPowerDelayProfile"); // NOI18N
        panelPowerDelayProfile.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPowerDelayProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPowerDelayProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel5);

        javax.swing.GroupLayout panelGraficasPequeñaEscalaLayout = new javax.swing.GroupLayout(panelGraficasPequeñaEscala);
        panelGraficasPequeñaEscala.setLayout(panelGraficasPequeñaEscalaLayout);
        panelGraficasPequeñaEscalaLayout.setHorizontalGroup(
            panelGraficasPequeñaEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
        );
        panelGraficasPequeñaEscalaLayout.setVerticalGroup(
            panelGraficasPequeñaEscalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Parametros de Pequeña Escala", new javax.swing.ImageIcon(getClass().getResource("/Images/iconos16x16/chart_stock.png")), panelGraficasPequeñaEscala, ""); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Caracteristicas del Path"));
        jPanel3.setName("jPanel3"); // NOI18N

        RSSI_DownText.setEditable(false);
        RSSI_DownText.setFont(new java.awt.Font("Tahoma", 1, 12));
        RSSI_DownText.setName("RSSI_DownText"); // NOI18N

        CNR_DownText.setEditable(false);
        CNR_DownText.setFont(new java.awt.Font("Tahoma", 1, 12));
        CNR_DownText.setName("CNR_DownText"); // NOI18N

        jLabel2.setText("RSSI DownStream:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel5.setText("CNR DownStream:");
        jLabel5.setName("jLabel5"); // NOI18N

        PathLoss_DownText.setEditable(false);
        PathLoss_DownText.setFont(new java.awt.Font("Tahoma", 1, 12));
        PathLoss_DownText.setName("PathLoss_DownText"); // NOI18N

        jLabel8.setText("Path-Loss DownStream:");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText("[dB]");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText("[dBm]");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText("[dB]");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("Path-Loss UpStream:");
        jLabel12.setName("jLabel12"); // NOI18N

        PathLoss_UpText.setEditable(false);
        PathLoss_UpText.setFont(new java.awt.Font("Tahoma", 1, 12));
        PathLoss_UpText.setName("PathLoss_UpText"); // NOI18N

        jLabel13.setText("[dB]");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText("RSSI UpStream:");
        jLabel14.setName("jLabel14"); // NOI18N

        RSSI_UpText.setEditable(false);
        RSSI_UpText.setFont(new java.awt.Font("Tahoma", 1, 12));
        RSSI_UpText.setName("RSSI_UpText"); // NOI18N

        jLabel15.setText("[dBm]");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText("CNR UpStream:");
        jLabel16.setName("jLabel16"); // NOI18N

        CNR_UpText.setEditable(false);
        CNR_UpText.setFont(new java.awt.Font("Tahoma", 1, 12));
        CNR_UpText.setName("CNR_UpText"); // NOI18N

        jLabel17.setText("[dB]");
        jLabel17.setName("jLabel17"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PathLoss_DownText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PathLoss_UpText)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RSSI_UpText, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RSSI_DownText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel10))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CNR_DownText, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CNR_UpText, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(RSSI_DownText, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(RSSI_UpText, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PathLoss_DownText, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(PathLoss_UpText, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CNR_DownText)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CNR_UpText, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 public void setParametrosLink(double distanciaEnTierraValor, double distanciaLinealValor ){
     distanciaEnTierra.setText("" + distanciaEnTierraValor);
     distanciaLineal.setText("" + distanciaLinealValor);
 }
    
 public void setPathLossDown(double[] loss){
     for(int i = 0; i < pathsLossDownSeries.length;i++){
         fecha = new Date();
         pathsLossDownSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), loss[i]);
         if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
             PathLoss_DownText.setText("" + loss[i]);
         }
     }
 }
 
 public void setPathLossUp(double[] loss){
     for(int i = 0; i < pathsLossUpSeries.length;i++){
         fecha = new Date();
         pathsLossUpSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), loss[i]);
         if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
             PathLoss_UpText.setText("" + loss[i]);
         }
     }
 }
 
 public void setRSSI_Down(double[] loss){
     try{
         for(int i = 0; i < loss.length;i++){
             fecha = new Date();
             pathsRSSI_DownSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                     fecha.getDate(), fecha.getMonth()+1, 2012), loss[i]);

             if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
                 RSSI_DownText.setText("" + loss[i]);
             }
         }
     }catch(NullPointerException NPE){
     }catch(ArrayIndexOutOfBoundsException AIE){
     }
 }
 
 public void setRSSI_Up(double[] potencia){
     for(int i = 0; i < pathsRSSI_UpSeries.length;i++){
         fecha = new Date();
         pathsRSSI_UpSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), potencia[i]);
         if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
             RSSI_UpText.setText("" + potencia[i]);
         }
     }
 }
 
 public void setSensibilidades(double[] sensibilidadDown, double[] sensibilidadUp){
     fecha = new Date();
     for(int i = 0; i < sensibilidad_DownSeries.length;i++){
         sensibilidad_DownSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), sensibilidadDown[i]);
         sensibilidad_UpSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), sensibilidadUp[i]);
     }
 }
 
 public void setCNR_Down(double[] relacion){
     for(int i = 0; i < pathsCNR_DownSeries.length;i++){
         fecha = new Date();
         pathsCNR_DownSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), relacion[i]);
         if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
             CNR_DownText.setText("" + relacion[i]);
         }
     }
 }
  
 public void setCNR_Up(double[] relacion){
     for(int i = 0; i < pathsCNR_UpSeries.length;i++){
         fecha = new Date();
         pathsCNR_UpSeries[i].addOrUpdate(new Second(fecha.getSeconds(), fecha.getMinutes(), fecha.getHours(), 
                 fecha.getDate(), fecha.getMonth()+1, 2012), relacion[i]);
         if(listaPaths.getSelectedItem().toString().equals(""+(i+1))){
             CNR_UpText.setText("" + relacion[i]);
         }
     }
 }
  
 public void setPDP(LinkedList PDP_paths){
     
     
     try{
         for(int i=0; i < PDP_paths.size(); i++){
             double[][] arrayPDPActual = (double[][])PDP_paths.get(i);
             for(int j=0; j < arrayPDPActual[0].length; j++){
                 pathsPowerDelayProfileSeries[i].clear();
             }
         }
         for(int i=0; i < PDP_paths.size(); i++){
             double[][] arrayPDPActual = (double[][])PDP_paths.get(i);
             for(int j = 0; j <  arrayPDPActual[0].length; j++){
                 pathsPowerDelayProfileSeries[i].addOrUpdate(arrayPDPActual[0][j], arrayPDPActual[1][j]);
             }
         }
     }catch(NullPointerException NPE){
     }catch(ArrayIndexOutOfBoundsException aio){
     }
 }
 
 
private void botonCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCanelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_botonCanelarActionPerformed

private void listaPathsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaPathsActionPerformed
// TODO add your handling code here:
    
}//GEN-LAST:event_listaPathsActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CNR_DownText;
    private javax.swing.JTextField CNR_UpText;
    private javax.swing.JTextField PathLoss_DownText;
    private javax.swing.JTextField PathLoss_UpText;
    private javax.swing.JTextField RSSI_DownText;
    private javax.swing.JTextField RSSI_UpText;
    private javax.swing.JButton botonCanelar;
    private javax.swing.JTextField distanciaEnTierra;
    private javax.swing.JTextField distanciaLineal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox listaPaths;
    private javax.swing.JPanel panelGraficaCNR_Down;
    private javax.swing.JPanel panelGraficaCNR_Up;
    private javax.swing.JPanel panelGraficaPathLossDown;
    private javax.swing.JPanel panelGraficaPathLossUp;
    private javax.swing.JPanel panelGraficaRSSI_Down;
    private javax.swing.JPanel panelGraficaRSSI_Up;
    private javax.swing.JScrollPane panelGraficasGranEscala;
    private javax.swing.JPanel panelGraficasPequeñaEscala;
    private javax.swing.JPanel panelPowerDelayProfile;
    // End of variables declaration//GEN-END:variables

}
