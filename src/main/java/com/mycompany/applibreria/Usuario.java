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
    private boolean conPrestamo;
    
    public Usuario(String RUN, String nombreCompleto, boolean conPrestamo) {
        setRUN(RUN);
        setNombreCompleto(nombreCompleto);
        setConPrestamo(conPrestamo);
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
     * @return the conPrestamo
     */
    public boolean isConPrestamo() {
        return conPrestamo;
    }

    /**
     * @param conPrestamo the conPrestamo to set
     */
    public void setConPrestamo(boolean conPrestamo) {
        this.conPrestamo = conPrestamo;
    }
    
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // 1-1;Tomás S;1
        
        return getRUN() + ";" + getNombreCompleto();
    }
}
