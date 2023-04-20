/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public abstract class Usuario {
    private String RUN;
    private String nombreCompleto;
    private int ISBN;
    
    public Usuario(String RUN, String nombreCompleto, int ISBN) {
        setRUN(RUN);
        setNombreCompleto(nombreCompleto);
        setISBN(ISBN);
    }
    
    /**
     * @return the RUN
     */
    public String getRUN() {
        return RUN;
    }

    /**
     * @param RUN the RUN to set
     */
    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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
        // 1-1;Tomás S;1
        
        return getRUN() + ";" + getNombreCompleto() + ";" + getISBN();
    }
}
