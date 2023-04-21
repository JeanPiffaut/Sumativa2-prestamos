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
    private boolean magister;
    private boolean doctor;
    
    public Docente(String RUN, String nombreCompleto,  boolean conPrestamo, boolean magister, boolean doctor) {
        super(RUN, nombreCompleto, conPrestamo);
        setMagister(magister);
        setDoctor(doctor);
    }
    /**
     * @return the magister
     */
    public boolean isMagister() {
        return magister;
    }

    /**
     * @param magister the magister to set
     */
    public void setMagister(boolean magister) {
        this.magister = magister;
    }

    /**
     * @return the doctor
     */
    public boolean isDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(boolean doctor) {
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
