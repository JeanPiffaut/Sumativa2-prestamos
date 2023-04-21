/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.applibreria;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Tom
 */
public class AppLibreria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // NOMBRE DE LOS ARCHIVOS
        final String archivoUsuarios    = "usuarios.csv";
        final String archivoLibros      = "libros.csv";
        // GENERAMOS DATOS DE USUARIOS BASE
        ArrayList<Usuario> usuarios = cargarUsuarios(archivoUsuarios);
        // GENERAMOS DATOS DE LIBROS BASE
        ArrayList<Libro> libros = cargarLibros(archivoLibros);
        // UN ARREGLO DE DEVOLUCIONES
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        // GENERAMOS UN PRÉSTAMO
        Prestamo prestamo = Prestamo.ingresarPrestamo(1, "1-2", libros, usuarios);
        // AGREGAMOS EL PRÉTAMO AL ARREGLO DE PRÉSTAMOS
        prestamos.add(prestamo);
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        System.out.println(prestamo.toString());
        // GENERAMOS UNA DEVOLUCION
        //Prestamo.ingresarDevolucion(1, "1-2", prestamos);
        System.out.println("-----------------------------------------------------------");
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        //System.out.println(prestamo.toString());
        guardarUsuarios(archivoUsuarios, usuarios);
    }
    
    public static ArrayList<Usuario> cargarUsuarios(String nombreArchivo) throws FileNotFoundException {
         // CARGAMOS EL ARCHIVO EN MEMORIA
        File archivo = new File(nombreArchivo);
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        // LEEMOS EL ARCHIVO CON UN SCANNER
        Scanner lector = new Scanner(archivo);
        // ARREGLO PARA GUARDAR LOS USUARIOS CARGADOS
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        lector.nextLine();
        // PREGUNTO SI EL ARCHIVO TIENE UNA LÍNEA SIGUIENTE
        while (lector.hasNextLine()) {
            // OBTENGO EL VALOR DE LA LÍNEA
            String linea = lector.nextLine();
            String cortado[] = linea.split(";");
            // OBTENGO EL TIPO DE USUARIO
            String tipo = cortado[0];
            // OBTENGO EL RUN DEL USUARIO
            String run = cortado[1];
            // OBTENGO EL NOMBRE DEL USUARIO
            String nombreCompleto = cortado[2];
            // OBTENGO EL ISBN DEL USUARIO
            int conPrestamo = Integer.parseInt(cortado[3]);
            // DDECLARO LA VARIABLE PARA GUARDAR EL USUARIO
            String carrera = cortado[4];
            
            boolean magister = cortado[5];
            
            boolean doctor = cortado[6];
            
            Usuario obj;
            if (tipo == "docente") {
                obj = new Docente(run, nombreCompleto, conPrestamo, carrera, magister, doctor );
            } else {
                obj = new Estudiante(run, nombreCompleto, conPrestamo);
            }
            usuarios.add(obj);
        }
        
        // CERRAMOS EL ARCHIVO PARA LIBERAR MEMORIA
        lector.close();
        
        return usuarios;
    }
    
    public static ArrayList<Libro> cargarLibros(String nombreArchivo) throws FileNotFoundException {
         // CARGAMOS EL ARCHIVO EN MEMORIA
        File archivo = new File(nombreArchivo);
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        // LEEMOS EL ARCHIVO CON UN SCANNER
        Scanner lector = new Scanner(archivo);
        // ARREGLO PARA GUARDAR LOS LIBROS CARGADOS
        ArrayList<Libro> libros = new ArrayList<Libro>();
        
        String columnas = lector.nextLine();
        // PREGUNTO SI EL ARCHIVO TIENE UNA LÍNEA SIGUIENTE
        while (lector.hasNextLine()) {
            // OBTENGO EL VALOR DE LA LÍNEA
            String linea = lector.nextLine();
            String cortado[] = linea.split(";");
            // OBTENGO EL ISBN DEL LIBRO
            int ISBN = Integer.parseInt(cortado[0]);
            // DDECLARO LA VARIABLE PARA GUARDAR EL USUARIO
            Libro obj = new Libro(ISBN);
            
            libros.add(obj);
        }
        // CERRAMOS EL ARCHIVO PARA LIBERAR MEMORIA
        lector.close();
        
        return libros;
    }
    
    public static void guardarUsuarios(String nombreArchivo, ArrayList<Usuario> usuarios) throws FileNotFoundException, IOException {
        String columnas = obtenerColumnasArchivo(nombreArchivo);
        // GENERAMOS UN NUEVO ARCHIVO PARA IR GUARDANDO DATOS
        FileWriter archivoEscritura = new FileWriter(nombreArchivo);
        // ESCRIBIMOS EN EL ARCHIVO LAS COLUMNAS
        archivoEscritura.write(columnas + "\n");
        
        // RECORREMOS CADA LÍNEA GUARDADA
        for (int i = 0; i < usuarios.size(); i++) {
            // OBRTENEMOS EL FORMATO CSV
            String linea = usuarios.get(i).toCSV();
            // ESCRIBIMOS EN EL ARCHIVO
            archivoEscritura.write(linea + "\n");
        }
        // CERRAMOS EL ARCHIVO
        archivoEscritura.close();
    }
    
    public static String obtenerColumnasArchivo(String nombreArchivo) throws FileNotFoundException {
        // CARGAMOS EL ARCHIVO EN MEMORIA
        File archivo = new File(nombreArchivo);
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        // LEEMOS EL ARCHIVO CON UN SCANNER
        Scanner lector = new Scanner(archivo);
        // OBTENGO LAS COLUMNAS
        String columnas = lector.nextLine();
        
        return columnas;
    }
}
