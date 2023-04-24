/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public final class Docente extends Usuario {
    private String magister;
    private String doctor;
    
    public Docente(String RUN, String nombreCompleto, char genero, int conPrestamo, String magister, String doctor) {
        super(RUN, nombreCompleto, genero, conPrestamo);
        setMagister(magister);
        setDoctor(doctor);
    }

    public String getMagister() {
        return magister;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setMagister(String magister) {
        this.magister = magister;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    @Override
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // Y UTILIZO COMO BASE EL MÉTODO DE MI HERENCIA
        // "docente;" + "1-1;Tomás S;1" => "docente;1-1;Tomás S;1"
        return "Docente;" + super.toCSV();
    }
}
