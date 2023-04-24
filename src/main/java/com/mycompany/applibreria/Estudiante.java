/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public final class Estudiante extends Usuario {
    private String carrera;
    
    // GENERAMOS DATOS DE USUARIOS BASE
    public Estudiante(String RUN, String nombreCompleto, char genero, int conPrestamo, String carrera) {
        super(RUN, nombreCompleto, genero,conPrestamo);
        setCarrera(carrera);
    }
    
    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
      @Override
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // Y UTILIZO COMO BASE EL MÉTODO DE MI HERENCIA
        // "estudiante;" + "1-1;Tomás S;0" = > estudiante;1-1;Tomás S;0
        return "estudiante;" + super.toCSV();
    }

    
}
