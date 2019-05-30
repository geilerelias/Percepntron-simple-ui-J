/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JOptionPane;

/**
 *
 * @author geile
 */
public class MensajeRespuesta {
    private String Mensaje;
    private int tipoMensaje;
    private boolean estado;
    PerceptronSimple perceptronSimple;

   
    
    public MensajeRespuesta() {
    }

    public MensajeRespuesta(String Mensaje, int tipoMensaje) {
        this.Mensaje = Mensaje;
        this.tipoMensaje = tipoMensaje;
    }

    /**
     * @return the Mensaje
     */
    public String getMensaje() {
        return Mensaje;
    }

    /**
     * @param Mensaje the Mensaje to set
     */
    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    /**
     * @return the tipoMensaje
     */
    public int getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * @param tipoMensaje the tipoMensaje to set
     */
    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public void setPerceptronSimple(PerceptronSimple perceptronSimple) {
        this.perceptronSimple = perceptronSimple;
    }

    public PerceptronSimple getPerceptronSimple() {
        return perceptronSimple;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   
   
    
}
