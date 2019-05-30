/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geile
 */
public class PerceptronSimple extends RedNeuronal implements Runnable {

    private IGraficar graficar;
    public Thread hilo;

    public PerceptronSimple() {
    }

    public PerceptronSimple(int m, int n, int patrones, int[][] x, int[][] yd, double[] u, double[][] w, int numeroIteraciones, double errorMaximoPermitido, double tasaAprendisaje) {
        super(m, n, patrones, x, yd, u, w, numeroIteraciones, errorMaximoPermitido, tasaAprendisaje);
    }

    public PerceptronSimple comenzarHilo() {
        hilo = new Thread(this);
        hilo.start();
        return this;
    }

    public int entrenar() {
        ArrayList array = new ArrayList();
        int[][] yr = new int[getPatrones()][getN()];
        double[] el = new double[getN()];
        double[] ep = new double[getPatrones()];

        for (int interacion = 1; interacion <= getNumeroIteraciones(); interacion++) {

            double sumEp = 0;
            for (int p = 0; p < getPatrones(); p++) {
                double sumEl = 0;
                for (int i = 0; i < getN(); i++) {
                    double soma = 0;
                    for (int j = 0; j < getM(); j++) {
                        soma = soma + getX()[p][j] * getW()[j][i];
                    }
                    soma = soma + getU()[i];
                    yr[p][i] = activacion(soma);
                    el[i] = getYd()[p][i] - yr[p][i];

                    sumEl = sumEl + Math.abs(el[i]);
                }
                ep[p] = sumEl / getN();
                sumEp = sumEp + ep[p];

                for (int i = 0; i < getN(); i++) {
                    getU()[i] = getU()[i] + getTasaAprendisaje() * el[i];
                    for (int j = 0; j < getM(); j++) {
                        getW()[j][i] = getW()[j][i] + getTasaAprendisaje() * el[i] * getX()[p][j];
                    }
                }
            }

            double erms = sumEp / getPatrones();
            array.add(erms);
            graficar.Progreso(interacion);
            graficar.graficarErmsIteracion(array, getErrorMaximoPermitido());
            graficar.graficarPesosUmbrales(getW(), getU());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PerceptronSimple.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("iteracion: "+interacion);
            System.out.println("erms: " + erms);

            if (erms <= getErrorMaximoPermitido()) {
                setEstado(true);
                graficar.Progreso(getNumeroIteraciones());
                graficar.finalizar();
                
                return 1;
            }
        }
        graficar.finalizar();
        setEstado(false);
        return 1;
    }

    public boolean inicializarPesosUmbrales() {
        try {
            setU(new double[getN()]);
            setW(new double[getM()][getN()]);

            for (int i = 0; i < getN(); i++) {
                getU()[i] = ThreadLocalRandom.current().nextDouble(-1, 1);
                for (int j = 0; j < getM(); j++) {
                    getW()[j][i] = ThreadLocalRandom.current().nextDouble(-1, 1);
                }
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        entrenar();
    }

    public IGraficar getGraficar() {
        return graficar;
    }

    public void setGraficar(IGraficar graficar) {
        this.graficar = graficar;
    }

}
