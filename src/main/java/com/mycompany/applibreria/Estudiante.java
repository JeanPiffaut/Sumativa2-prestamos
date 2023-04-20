/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public class Estudiante extends Usuario {
    // GENERAMOS DATOS DE USUARIOS BASE
    public Estudiante(String RUN, String nombreCompleto, int ISBN) {
        super(RUN, nombreCompleto, ISBN);
    }
    
    @Override
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // Y UTILIZO COMO BASE EL MÉTODO DE MI HERENCIA
        // "estudiante;" + "1-1;Tomás S;0" = > estudiante;1-1;Tomás S;0
        return "estudiante;" + super.toCSV();
    }
}
