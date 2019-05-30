/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.IGraficar;
import Model.MensajeRespuesta;
import Model.PerceptronSimple;
import Model.PerceptronSimpleDao;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author geile
 */
public class ControllerPerceptronSimple {

    PerceptronSimpleDao perceptronSimpleDao = new PerceptronSimpleDao();
    private PerceptronSimple perceptronSimple;

    public ControllerPerceptronSimple() {
    }

    public void entrenar(int numeroIteraciones, double erms, double tasa, IGraficar graficar) {
        perceptronSimple.setErrorMaximoPermitido(erms);
        perceptronSimple.setNumeroIteraciones(numeroIteraciones);
        perceptronSimple.setTasaAprendisaje(tasa);
        perceptronSimple.setGraficar(graficar);

        Thread thread = new Thread(perceptronSimple);
        thread.start();
    }

    public MensajeRespuesta cargarDatos(File file) {
        MensajeRespuesta mensajeRespuesta = perceptronSimpleDao.CargarArchivo(file);

        perceptronSimple = mensajeRespuesta.getPerceptronSimple();
        perceptronSimple.ObtenerEntradasSalidasPatrones();
        return mensajeRespuesta;
    }

    public void guardar(PerceptronSimple perceptronSimple, String nombre) {
        perceptronSimple.setGraficar(null);
        perceptronSimpleDao.guardar(perceptronSimple, nombre);
    }

    public MensajeRespuesta inicializarPesosUmbrales() {

        if (getPerceptronSimple() != null) {
            if (getPerceptronSimple().inicializarPesosUmbrales()) {
                MensajeRespuesta mensajeRespuesta = new MensajeRespuesta("Inicializacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                mensajeRespuesta.setEstado(true);
                return mensajeRespuesta;

            } else {
                return new MensajeRespuesta("Tus Parametros de entradas no son correctos", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            return new MensajeRespuesta("No has Cargado Datos para esta red", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void graficarErmsIteracion(JPanel panelGraficaEntrenamiento, ArrayList listaErms, double errorMaximoPermitido) {
        // TODO add your handling code here:

        XYSeries sErms;//serie Erms
        XYSeries sIteraciones;//serie Iteraciones
        //titulos de las dos lineas que se graficaran
        sErms = new XYSeries("error maximo permitido");
        sIteraciones = new XYSeries("Erms vs Iteraciones");
        // ejemplo de valores que puedo cargar en la serie

        for (int i = 0; i < listaErms.size(); i++) {
            sIteraciones.add(i, (double) listaErms.get(i));
            sErms.add(i, errorMaximoPermitido);
        }

        //creo un contenedor que me almacenara el valor de las do series
        XYSeriesCollection xySeriesColletion = new XYSeriesCollection();

        //almaceno cada serie en el contendor
        xySeriesColletion.addSeries(sErms);
        xySeriesColletion.addSeries(sIteraciones);

        XYDataset contenedor = xySeriesColletion;
        //creo un marco de para que se pueda visualizar en la pantalla
        JFreeChart marco = ChartFactory.createXYLineChart("Gráfico de Erms vs Iteración", "Iteración", "Error RMS", contenedor, PlotOrientation.VERTICAL, true, true, true);

        //personalización del grafico color de fondo 
        XYPlot xyplot = (XYPlot) marco.getPlot();
        xyplot.setBackgroundPaint(Color.white);
        //personalización del grafico color de los grit 
        xyplot.setDomainGridlinePaint(Color.BLACK);
        xyplot.setRangeGridlinePaint(Color.BLACK);

        // -> Pinta Shapes en los puntos dados por el XYDataset
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(false);

        //--> muestra los valores de cada punto XY
        XYItemLabelGenerator xy = new StandardXYItemLabelGenerator();
        xylineandshaperenderer.setBaseItemLabelGenerator(xy);
        xylineandshaperenderer.setBaseItemLabelsVisible(true);
        xylineandshaperenderer.setBaseLinesVisible(true);
        xylineandshaperenderer.setBaseItemLabelsVisible(false);
        // Mostramos la grafica dentro del jPanel1
        ChartPanel panel = new ChartPanel(marco);
        panel.setBounds(panelGraficaEntrenamiento.getBounds());
        panelGraficaEntrenamiento.removeAll();
        panelGraficaEntrenamiento.setLayout(new java.awt.BorderLayout());
        panelGraficaEntrenamiento.add(panel);
        panelGraficaEntrenamiento.validate();
    }

    public void graficarPesosUmbrales(JPanel panelGraficoPesosUmbrales, double[][] w, double[] u) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Boys (Age,weight) series
        XYSeries seriesW = new XYSeries("Weight");
        for (int i = 0; i < w[0].length; i++) {
            for (int j = 0; j < w.length; j++) {
                seriesW.add(j, w[j][i]);
            }
        }
        dataset.addSeries(seriesW);

        XYSeries seriesu = new XYSeries("Umbral");
        for (int i = 0; i < u.length; i++) {
            seriesu.add(i, u[i]);
        }
        dataset.addSeries(seriesu);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Grafico Pesos Umbrales",
                "X-Axis", "Y-Axis", dataset);

        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        panel.setBounds(panelGraficoPesosUmbrales.getBounds());
        panelGraficoPesosUmbrales.removeAll();
        panelGraficoPesosUmbrales.setLayout(new java.awt.BorderLayout());
        panelGraficoPesosUmbrales.add(panel);
        panelGraficoPesosUmbrales.validate();
    }

    /**
     * @return the perceptronSimple
     */
    public PerceptronSimple getPerceptronSimple() {
        return perceptronSimple;
    }

    /**
     * @param perceptronSimple the perceptronSimple to set
     */
    public void setPerceptronSimple(PerceptronSimple perceptronSimple) {
        this.perceptronSimple = perceptronSimple;
    }

}
