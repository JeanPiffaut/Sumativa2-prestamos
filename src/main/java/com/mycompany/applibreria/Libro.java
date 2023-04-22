/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

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
    public Libro(int ISBN, String titulo, String autor, int cant_biblioteca, int cant_disponible, boolean imagen) {
        setISBN(ISBN);
        setTitulo(titulo);
        setAutor(autor);
        setCant_biblioteca(cant_biblioteca); //DEBE SER MAYOR A CERO
        setCant_disponible(cant_disponible); //DEBE SER MATOR A CERO Y MEJOR O IGUAL A CANTIDAD BIBLIOTECA
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
    
    public void CrearLibro() {
        
    }
    
    public void EliminarLibro() {
        //SI EL LIBRO EXISTE Y NO ESTÁ A ASOCIADO A NINGUN PRESTAMO ACTIVO, ELIMINAR (METODO SE UTILIZARÁ LUEGO MEDIANTE INTERFAZ GRAFICA)

    }
    
    public String toCSV() {
        return getISBN() + ";" + getTitulo() + ";" + getAutor() 
                + ";" + getCant_biblioteca() + ";" + getCant_disponible() 
                + ";" + isImagen();
    }
}
