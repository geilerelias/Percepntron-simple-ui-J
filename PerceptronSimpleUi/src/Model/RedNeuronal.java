/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author geile
 */
public class RedNeuronal {

    private int m;
    private int n;
    private int patrones;
    private int[][] x;
    private int[][] yd;
    private double[] u;
    private double[][] w;
    private int numeroIteraciones;
    private double errorMaximoPermitido;
    private double tasaAprendisaje;
    private boolean estado;

    public RedNeuronal() {
    }

    public RedNeuronal(int m, int n, int patrones, int[][] x, int[][] yd, double[] u, double[][] w, int numeroIteraciones, double errorMaximoPermitido, double tasaAprendisaje) {
        this.m = m;
        this.n = n;
        this.patrones = patrones;
        this.x = x;
        this.yd = yd;
        this.u = u;
        this.w = w;
        this.numeroIteraciones = numeroIteraciones;
        this.errorMaximoPermitido = errorMaximoPermitido;
        this.tasaAprendisaje = tasaAprendisaje;
    }

    public void ObtenerEntradasSalidasPatrones() {
        this.setM(this.getX()[0].length);
        this.setN(this.getYd()[0].length);
        this.setPatrones(this.getX().length);
    }

    public int activacion(double soma) {
        if (soma >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @return the m
     */
    public int getM() {
        return m;
    }

    /**
     * @param m the m to set
     */
    public void setM(int m) {
        this.m = m;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the patrones
     */
    public int getPatrones() {
        return patrones;
    }

    /**
     * @param patrones the patrones to set
     */
    public void setPatrones(int patrones) {
        this.patrones = patrones;
    }

    /**
     * @return the x
     */
    public int[][] getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int[][] x) {
        this.x = x;
    }

    /**
     * @return the yd
     */
    public int[][] getYd() {
        return yd;
    }

    /**
     * @param yd the yd to set
     */
    public void setYd(int[][] yd) {
        this.yd = yd;
    }

    /**
     * @return the u
     */
    public double[] getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(double[] u) {
        this.u = u;
    }

    /**
     * @return the w
     */
    public double[][] getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(double[][] w) {
        this.w = w;
    }

    /**
     * @return the numeroIteraciones
     */
    public int getNumeroIteraciones() {
        return numeroIteraciones;
    }

    /**
     * @param numeroIteraciones the numeroIteraciones to set
     */
    public void setNumeroIteraciones(int numeroIteraciones) {
        this.numeroIteraciones = numeroIteraciones;
    }

    /**
     * @return the errorMaximoPermitido
     */
    public double getErrorMaximoPermitido() {
        return errorMaximoPermitido;
    }

    /**
     * @param errorMaximoPermitido the errorMaximoPermitido to set
     */
    public void setErrorMaximoPermitido(double errorMaximoPermitido) {
        this.errorMaximoPermitido = errorMaximoPermitido;
    }

    /**
     * @return the tasaAprendisaje
     */
    public double getTasaAprendisaje() {
        return tasaAprendisaje;
    }

    /**
     * @param tasaAprendisaje the tasaAprendisaje to set
     */
    public void setTasaAprendisaje(double tasaAprendisaje) {
        this.tasaAprendisaje = tasaAprendisaje;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
