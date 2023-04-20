/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public class Docente extends Usuario {
    public Docente(String RUN, String nombreCompleto, int ISBN) {
        super(RUN, nombreCompleto, ISBN);
    }
    
    @Override
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // Y UTILIZO COMO BASE EL MÉTODO DE MI HERENCIA
        // "docente;" + "1-1;Tomás S;1" => "docente;1-1;Tomás S;1"
        return "docente;" + super.toCSV();
    }
}
