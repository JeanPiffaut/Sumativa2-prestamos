/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import static com.mycompany.applibreria.AppLibreria.obtenerColumnasArchivo;
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
public final class Libro {
    private int ISBN; // DATO UNICO
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
        if (cant_biblioteca > 0){
            this.cant_biblioteca = cant_biblioteca;
        } else {
          throw new IllegalArgumentException("la cantidad en biblioteca "
                  + "para configurar el libro debe ser mayor a '0'");
        }    
    }

    public int getCant_disponible() {
        return cant_disponible;
    }

    public void setCant_disponible(int cant_disponible) {
        if (cant_disponible > 0 && cant_disponible <= cant_biblioteca){
            this.cant_disponible = cant_disponible;
        }else{
            throw new IllegalArgumentException("la cantidad a disponibilizar "
                    + "debe ser mayor a '0' y no sobrepasar los ejemplares "
                    + "en biblioteca");
        }
            
    }

    public boolean getImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }
    
    public void CrearLibro() throws IOException {
        String nombreArchivo = "libros.csv";
        ArrayList<Libro> libros = AppLibreria.cargarLibros(nombreArchivo);
        
        
        // 2.2.1.El ISBN debe ser único.
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.getISBN() == getISBN()) {
                throw new IOException("El libro \"" + getTitulo() + "\", no "
                        + "fue creado ya que el ISBN ya existe");
            }
        }
        
        // 2.2.2.Cantidad en biblioteca debeser mayor a cero
        if (getCant_biblioteca() <= 0) {
            throw new IOException("El libro \"" + getTitulo() + "\", no fue "
                    + "creado ya que la cantidad en la biblioteca "
                    + "debe ser mayor a 0");
        }
        
        // 2.2.3.Cantidad disponible debe ser mayor a cero y menor o igual a 
        // cantidad en biblioteca.
        if (getCant_disponible() <= 0 
                || getCant_disponible() < getCant_biblioteca()) {
            throw new IOException("El libro \"" + getTitulo() + "\", no fue "
                    + "creado ya que la cantidad disponible debe ser mayor "
                    + "o igual a 0 y menor o igual a la cantidad en la "
                    + "biblioteca");
        }
        
        // Agregamos el libro seteado
        libros.add(this);
        System.out.println("El libro \"" + getTitulo() + "\", fue creado "
                + "exitosamente.");
        
        // Hacemos el cambio en el excel
        guardarLibros(libros);
    }
    
    public void EliminarLibro() throws IOException {
        ArrayList<Libro> libros = AppLibreria.cargarLibros("libros.csv");
        boolean existe = false;
        int linea = 0;
        
        // Valida si el libro existe
        for(int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            
            if(libro.getISBN() == getISBN()) {
                existe = true;
                linea = i;
                break;
            }
        }
        
        // Ejecuta una accion en caso de que el libro no exista
        if (existe == false) {
            throw new IOException("El libro \"" + getTitulo() +"\", no fue "
                    + "eliminado ya que no se encontro su ISBN en los "
                    + "registros.");
        }
        
        // Valida si el libro esta arrendado en base al usuario
        ArrayList<Usuario> usuarios = AppLibreria.cargarUsuarios("usuarios.csv");
        for(int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            
            if(usuario.getConPrestamo()== getISBN()) {
                throw new IOException("El libro \"" + getTitulo() +"\", no fue "
                        + "eliminado ya que se encuentra arrendado.");
            }
        }
        
        // Eliminamos el libro del array
        libros.remove(libros.get(linea));
        
        guardarLibros(libros);
        System.out.println("El libro \""+ getTitulo() +"\", fue eliminado exitosamente.");
    }
    
    private void guardarLibros(ArrayList<Libro> libros) throws FileNotFoundException, IOException {
        String nombreArchivo = "libros.csv";
        String columnas = obtenerColumnasArchivo(nombreArchivo);
        // GENERAMOS UN NUEVO ARCHIVO PARA IR GUARDANDO DATOS, ADEMAS DEBEMOS VALIDAR DUPLICIDAD 
        FileWriter archivoEscritura = new FileWriter(nombreArchivo);
        // ESCRIBIMOS EN EL ARCHIVO LAS COLUMNAS
        archivoEscritura.write(columnas + "\n");
        
        // RECORREMOS CADA LÍNEA GUARDADA
        for (int i = 0; i < libros.size(); i++) {
            // OBRTENEMOS EL FORMATO CSV
            String linea = libros.get(i).toCSV();
            // ESCRIBIMOS EN EL ARCHIVO
            archivoEscritura.write(linea + "\n");
        }
       
        // CERRAMOS EL ARCHIVO
        archivoEscritura.close();  
    }
    
    public String toCSV() {
        return "" + getISBN() + ";" + getTitulo() + ";" + getAutor() 
                + ";" + getCant_biblioteca() + ";" + getCant_disponible() 
                + ";" + (getImagen() ? 1 : 0);
    }
}
