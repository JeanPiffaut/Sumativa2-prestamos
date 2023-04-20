/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.manejoarchivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tom
 */
public class ManejoArchivo {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // CARGAMOS EL ARCHIVO EN MEMORIA
        File archivo = new File("archivoCSV.csv");
        // VARIABLE PARA GUARDAR LAS LÍNEAS DEL ARCHIVO COMO OBJETOS
        ArrayList<Objeto> lineas = new ArrayList<Objeto>();
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        // LEEMOS EL ARCHIVO CON UN SCANNER
        Scanner lector = new Scanner(archivo);
        String columnas = lector.nextLine();
        // PREGUNTO SI EL ARCHIVO TIENE UNA LÍNEA SIGUIENTE
        while(lector.hasNextLine()) {
            // OBTENGO EL VALOR DE LA LÍNEA
            String linea = lector.nextLine();
            // SPLIT Columna 1;Columna 2;Columna 3;Columna 4;Columna 5 (;)
            // ["Columna 1", "Columna 2", "Columna 3", "Columna 4", "Columna 5"]
            //System.out.println("linea = " + linea);
            String cortado[] = linea.split(";");
            // ["Columna 1", "Columna 2", "Columna 3", "Columna 4", "Columna 5"]
            Objeto obj = new Objeto(cortado[0], cortado[1], cortado[2], cortado[3], cortado[4]);
            // AGREGAMOS EL OBJETO DENTRO DE NUESTRA VARIABLE LÍNEAS
            lineas.add(obj);
            System.out.println(obj.toCSV());
        }
        // CERRAMOS EL ARCHIVO PARA LIBERAR MEMORIA
        lector.close();
        
        
        // MOSTRAMOS LA INFORMACIÓN APTA PARA EL USUARIO
        //System.out.println("Línea 1: \n" + lineas.get(0).toString());
        // MOSTRAMOS LA INFORMACIÓN APTA PARA EL SISTEMA
        //System.out.println("Línea 1: \n" + lineas.get(0).toCSV());
        lineas.get(0).setColumna1("A");
        lineas.get(0).setColumna4("D");
        lineas.get(4).setColumna2("JOSE");
        // GENERAMOS UN NUEVO ARCHIVO PARA IR GUARDANDO DATOS
        FileWriter archivoEscritura = new FileWriter("archivoCSV.csv");
        // ESCRIBIMOS EN EL ARCHIVO LAS COLUMNAS
        archivoEscritura.write(columnas + "\n");
        
        // RECORREMOS CADA LÍNEA GUARDADA
        for (int i = 0; i < lineas.size(); i++) {
            // OBRTENEMOS EL FORMATO CSV
            String linea = lineas.get(i).toCSV();
            // ESCRIBIMOS EN EL ARCHIVO
            archivoEscritura.write(linea + "\n");
        }
        // CERRAMOS EL ARCHIVO
        archivoEscritura.close();
    }
}
