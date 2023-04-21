/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tom
 */
public final class Libro {
    private int ISBN;
    private String titulo;
    private String autor;
    private int cant_biblioteca;
    private int cant_disponible;
    private boolean imagen;
    
    // DEBE COMPLETAR ESTE CONSTRUCTOR
    public Libro(int ISBN, String titulo, String autor, int cant_biblioteca, 
            int cant_disponible, boolean imagen) {
        setISBN(ISBN);
        setTitulo(titulo);
        setAutor(autor);
        setCant_biblioteca(cant_biblioteca);
        setCant_disponible(cant_disponible);
        setImagen(imagen);
    }
    
    public int getISBN() {
        return ISBN;
    }
    
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCant_biblioteca() {
        return cant_biblioteca;
    }

    public void setCant_biblioteca(int cant_biblioteca) {
        this.cant_biblioteca = cant_biblioteca;
    }

    public int getCant_disponible() {
        return cant_disponible;
    }

    public void setCant_disponible(int cant_disponible) {
        this.cant_disponible = cant_disponible;
    }

    public boolean isImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }
    
    public void CrearLibro(ArrayList<Libro> libros) throws IOException {
        // 2.2.1.El ISBN debe ser único.
        for (int i = 0; i < libros.size(); i++) {
            // VOY OBTENIENDO CADA LIBRO EN EL ARREGLO DE LIBROS
            Libro libro = libros.get(i);
            
            // PREGUNTO SI EL ISBN DEL LIBRO ES IGUAL AL LIBRO QUE BUSCO
            if (libro.getISBN() == getISBN()) {
                throw new IOException("El ISBN ya existe");
            }
        }
        
        // 2.2.2.Cantidad en biblioteca debeser mayor a cero
        if (getCant_biblioteca() >= 0) {
            throw new IOException("La cantidad en la biblioteca "
                    + "debe ser mayor a 0");
        }
        
        // 2.2.3.Cantidad disponible debe ser mayor a cero y menor o igual a 
        // cantidad en biblioteca.
        if (getCant_disponible() >= 0 
                || getCant_disponible() > getCant_biblioteca()) {
            throw new IOException("La cantidad disponible debe ser mayor "
                    + "o igual a 0 y menor o igual a la cantidad en la "
                    + "biblioteca");
        }
        String nombreArchivo = "libros.csv";
        File archivo = new File(nombreArchivo);
        
        // VALIDACIÓN DEL ARCHIVO
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo no existe.");
        }
        
        FileWriter archivoEscritura = new FileWriter(nombreArchivo);
        
        archivoEscritura.write(this.toCSV() + "\n");
        
        // CERRAMOS EL ARCHIVO
        archivoEscritura.close();
    }
    
    public void EliminarLibro() {
        
    }
    
    public String toCSV() {
        return getISBN() + ";" + getTitulo() + ";" + getAutor() 
                + ";" + getCant_biblioteca() + ";" + getCant_disponible() 
                + ";" + isImagen();
    }
}
