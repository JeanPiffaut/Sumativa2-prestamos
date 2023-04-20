/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public class Libro {
    private int ISBN;
    
    // DEBE COMPLETAR ESTE CONSTRUCTOR
    public Libro(int ISBN) {
        setISBN(ISBN);
    }
    
    /**
     * @return the ISBN
     */
    public int getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        return "" + getISBN();
    }
}
