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
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Tom
 */
public class AppLibreria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //NOMBRE DE ARCHIVOS
        final String archivoUsuarios    = "usuarios.csv";
        final String archivoLibros      = "libros.csv";
        
        //GENERAMOS DATOS DE USUARIOS BASE --> OK
        ArrayList<Usuario> usuarios = cargarUsuarios(archivoUsuarios);
        
        //GENERAMOS DATOS DE LIBROS BASE --> OK
        ArrayList<Libro> libros = cargarLibros(archivoLibros);
        
        agregarLinea(archivoLibros, archivoLibros);
        
        // UN ARREGLO DE DEVOLUCIONES
        //ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        // GENERAMOS UN PRÉSTAMO
        //Prestamo prestamo = Prestamo.ingresarPrestamo(1, "1-2", libros, usuarios);
        // AGREGAMOS EL PRÉTAMO AL ARREGLO DE PRÉSTAMO
        //prestamos.add(prestamo);
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        //System.out.println(prestamo.toString());
        // GENERAMOS UNA DEVOLUCION
        //Prestamo.ingresarDevolucion(1, "1-2", prestamos);
        //System.out.println("-----------------------------------------------------------");
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        System.out.println("USUARIOS: "+ usuarios.size());
        System.out.println("LIBROS: " + libros.size());
        //guardarUsuarios(archivoUsuarios, usuarios);
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
            
            char genero = cortado[3].charAt(0);
            //System.out.println(genero);
            // OBTENGO EL ISBN DEL USUARIO
            int conPrestamo = Integer.parseInt(cortado[4]);
            
            String carrera = cortado[5];
            
            String magister = cortado[6];
            
            String doctor = cortado[7];
            
            Usuario obj;
            if (tipo.equals("docente")) {
                obj = new Docente(run, nombreCompleto, genero, conPrestamo, magister, doctor );
            } else {
                obj = new Estudiante(run, nombreCompleto, genero, conPrestamo, carrera);
            }
            usuarios.add(obj);
        }
        
        // CERRAMOS EL ARCHIVO PARA LIBERAR MEMORIA
        lector.close();
        
        //DEBEMOS VALIDAE USUARIOS DUPLICADOS ANTES DE RETORNAR
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
        ArrayList vaLibros = new ArrayList();
        String columnas = lector.nextLine();
        // PREGUNTO SI EL ARCHIVO TIENE UNA LÍNEA SIGUIENTE
        while (lector.hasNextLine()) {
            // OBTENGO EL VALOR DE LA LÍNEA
            String linea = lector.nextLine();
            String cortado[] = linea.split(";");
            
            // OBTENGO EL ISBN DEL LIBRO
            int ISBN = Integer.parseInt(cortado[0]);

            String titulo = cortado[1];
            String autor = cortado[2];
            int cant_biblioteca = Integer.parseInt(cortado[3]);
            int cant_disponible = Integer.parseInt(cortado[4]);
            boolean imagen = Integer.parseInt(cortado[5]) != 0;
            
            // DECLARO LA VARIABLE PARA GUARDAR EL USUARIO
            Libro obj = new Libro(ISBN, titulo, autor, cant_biblioteca, cant_disponible, imagen);
            
            libros.add(obj);
        }
        
        // CERRAMOS EL ARCHIVO PARA LIBERAR MEMORIA
        lector.close();
        
        //DEBEMOS VALIDAR LIBROS DUPLICADOS ANTES DE RETORNAR
        return libros;
    }
    
    public static void guardarUsuarios(String nombreArchivo, ArrayList<Usuario> usuarios) throws FileNotFoundException, IOException {
        String columnas = obtenerColumnasArchivo(nombreArchivo);
        // GENERAMOS UN NUEVO ARCHIVO PARA IR GUARDANDO DATOS, ADEMAS DEBEMOS VALIDAR DUPLICIDAD 
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
    
    public static void agregarLinea(String nombreArchivo, String csv_linea) throws FileNotFoundException, IOException {
        File archivo = new File(nombreArchivo);
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        
        FileWriter archivoEscritura = new FileWriter(nombreArchivo);
        
        archivoEscritura.write(System.lineSeparator() + csv_linea);
    }
}
