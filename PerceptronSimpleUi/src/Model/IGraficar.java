/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author geile
 */
public interface IGraficar {

    public void graficarErmsIteracion(ArrayList array, double erms);
    public void graficarPesosUmbrales(double [][]w,double []u);
    public void Progreso(int i);
    public void finalizar();
}
