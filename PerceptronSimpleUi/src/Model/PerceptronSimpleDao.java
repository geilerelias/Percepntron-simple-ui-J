/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author geile
 */
public class PerceptronSimpleDao {

    public boolean guardar(PerceptronSimple perceptronSimple, String nombre) {
        Gson gson = new Gson();
        perceptronSimple.setGraficar(null);
        String json = gson.toJson(perceptronSimple);

        File root = null;
        try {
            root = new File(System.getProperty("user.dir") + "\\Entrenados");

            if (!root.exists()) {
                root.mkdirs();
            }

        } catch (Exception e) {
        }

        File directorio = null;
        try {
            directorio = new File(root + "\\" + nombre);

            if (!directorio.exists()) {
                directorio.mkdirs();
            }
        } catch (Exception e) {
        }
        //String nombre, int m, int n, int p, int[] numeroNeuronasCapas, String[] funcion, String algoritmo, double lr, ArrayList pesos, ArrayList umbral
        //Un texto cualquiera guardado en una variable

        try {
            //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
            File archivo = new File(directorio + "\\" + nombre + ".json");
            if (!archivo.exists()) {
                //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
                try (FileWriter fw = new FileWriter(archivo, false)) {
                    PrintWriter pw = new PrintWriter(fw);
                    //Escribimos en el archivo con el metodo write
                    pw.println(json);
                }
            } else {
                int num = directorio.listFiles().length;
                archivo = new File(directorio + "\\" + nombre + "0." + num + ".json");
                try (FileWriter fw = new FileWriter(archivo, false)) {
                    PrintWriter pw = new PrintWriter(fw);
                    //Escribimos en el archivo con el metodo write
                    pw.println(json);
                }

            }
            return true;
        } //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui //Si existe un problema al escribir cae aqui
        catch (IOException e) {
            System.out.println("Error al escribir" + e);
        }
        return false;
    }

    public MensajeRespuesta CargarArchivo(File file) {
        PerceptronSimple perceptronSimple;
        MensajeRespuesta mensajeRespuesta = new MensajeRespuesta();
        try {
            if (file == null) {
                return new MensajeRespuesta("\nNo se ha encontrado el archivo o \n esta inaccesible en estos momentos", JOptionPane.WARNING_MESSAGE);
            } else {
                FileReader archivos = new FileReader(file);
                try (BufferedReader lee = new BufferedReader(archivos)) {

                    String linea;
                    String json = "";
                    Gson gson = new Gson();
                    while ((linea = lee.readLine()) != null) {
                        json += linea;
                    }
                    try {
                        System.out.println(json);
                        perceptronSimple = gson.fromJson(json, PerceptronSimple.class);
                        mensajeRespuesta.setPerceptronSimple(perceptronSimple);
                        mensajeRespuesta.setMensaje("Datos Cargados Correctamente");
                        mensajeRespuesta.setTipoMensaje(JOptionPane.INFORMATION_MESSAGE);
                        mensajeRespuesta.setEstado(true);
                    } catch (JsonSyntaxException e) {
                        mensajeRespuesta.setMensaje(e.getMessage());
                        mensajeRespuesta.setTipoMensaje(JOptionPane.ERROR_MESSAGE);
                        mensajeRespuesta.setEstado(false);
                    }

                }
            }
        } catch (IOException ex) {
            mensajeRespuesta.setMensaje(ex.getMessage());
            mensajeRespuesta.setTipoMensaje(JOptionPane.ERROR_MESSAGE);
        }
        return mensajeRespuesta;
    }

}
