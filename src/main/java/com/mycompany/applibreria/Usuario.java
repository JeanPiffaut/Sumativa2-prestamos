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
    private char genero;
    private int conPrestamo;
    
    public Usuario(String RUN, String nombreCompleto, char genero, int conPrestamo) {
        setRUN(RUN); //DATO UNICO Y SER RUT VALIDO (M11)
        setNombreCompleto(nombreCompleto);
        setGenero(genero); //M -o- F
        setConPrestamo(conPrestamo); //O -o- Cod ISBN
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
    
    public char getGenero() {
        return genero;
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
    public int isConPrestamo() {
        return conPrestamo;
    }

    /**
     * @param conPrestamo the conPrestamo to set
     */
    public void setConPrestamo(int conPrestamo) {
        this.conPrestamo = conPrestamo;
    }
    
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // 1-1;Tomás S;1
        
        return getRUN() + ";" + getNombreCompleto();
    }
    
    public void eliminarUsuario(){
        //SI EL USUARIO EXISTE Y NO TIENE PRESTAMOS ACTIVOS, ELIMINAR (METODO SE UTILIZARÁ LUEGO MEDIANTE INTERFAZ GRAFICA)
    }
}
