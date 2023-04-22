/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Tom
 */
public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private GregorianCalendar fecha;
    private Devolucion devolucion;
    
    // DEBE COMPLETAR ESTE CONSTRUCTOR
    public Prestamo(Usuario usuario, Libro libro) {
        setUsuario(usuario);
        //setLibro(libro);
    }
    
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @return the fecha
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the devolucion
     */
    public Devolucion getDevolucion() {
        return devolucion;
    }

    /**
     * @param devolucion the devolucion to set
     */
    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }
    
    public String obtenerTipoDeUsuario() {
        if (getUsuario() instanceof Docente) {
            return "Docente";
        }
       
        return "Estudiante";
    }
        
    public static Prestamo ingresarPrestamo(int ISBN, String RUN, ArrayList<Libro> libros, ArrayList<Usuario> usuarios) {
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MÉTODO BUSCARLIBRO
        Libro libro = buscarLibro(ISBN, libros);
        
        // SI EL LIBRO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (libro == null) {
            throw new IllegalArgumentException("El libro a buscar no existe.");
        }
        
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MÉTODO BUSCARUSUARIO
        Usuario usuario = buscarUsuario(RUN, usuarios);
        
        // SI EL USUARIO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario a buscar no existe.");
        }
        
        // EN ESTE PUNTO, YA SABEMOS QUE EL USUARIO Y EL LIBRO YA EXISTEN
        
        // AQUÍ VALIDAMOS QUE EL LIBRO TENGA COMO MÍNIMO UN EJEMPLAR //
        // AQUÍ VALIDAMOS QUE EL USUARIO DEBA ESTAR HABILTIADO PARA EL PRÉSTAMO //
        
        // SI SE INTENTA DE ARRENDAR EL MISMO USUARIO CON EL MISMO LIBRO, IMPRIMIR ALERTA //
            
        // UNAS VEZ GENERADA TODAS LAS VALIDACIONES
        
        // GENERAMOS UNA INSTANCIA DE PRÉSTAMO
        Prestamo prestamo = new Prestamo(usuario, libro);
        // ---------------- LO QUE SE DEBE HACER A CONTINUACIÓN SE PUEDE REALIZAR DENTRO DE ÉSTE MÉTODO Ó ----------------
        // ----------------------------- DENTRO DE LA INSTANCIACIÓN DEL OBJETO -------------------------------------------
        // REDUCIMOS LA CANTIDAD DISPONIBLE DEL LIBRO
        // DEJAMOS AL USUARIO NO DISPONIBLE PARA EL NUEVO PRÉSTAMO, ASIGNANDO EL COD ISBN AL ATRIBUTO CONPRESTAMO
        // INSERTAMOS EN EL ARCHIVO HISTORICO DE PRESTAMOS CON INFO ADICIONAL (FECHA_ACTUAL, PERIODO DE PRESTAMO AUTORIZADO, FECHA_DEVOLUCION "CALCULADA AUTO%")
        // IMPRIMIMOS POR PANTALLA UN VOUCHER DE ARRIENDO (FORMATO A DEFINIR)
        // RETORNAMOS EL PRÉSTAMO VALIDADO
        return prestamo;
    }
    
    public static void ingresarDevolucion(int ISBN, String RUN, ArrayList<Prestamo> prestamos) {
        
        // A PARTIR DEL ENUNCIADO:
        // DEBEMOS VALIDAR QUE EL USUARIO A BUSCAR Y EL ISBN EXISTAN
        
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MÉTODO BUSCAR PRESTAMO
        
        Prestamo prestamo = buscarPrestamo(ISBN, RUN, prestamos);
        
        // SI EL PRÉTAMO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        
        if (prestamo == null) {
            throw new IllegalArgumentException("El prestamo a buscar no existe.");
        }
        // ASIGNO LA DEVOLUCIÓN RESPETANDO LA RELACIÓN DE COMPOSICIÓN
        Devolucion devolucion = new Devolucion();
        // ASINGO LA DEVOLUCIÓN RESPETANDO LA RELACIÓN DE COMPOSICIÓN
        // DEBIDO A QUE DEVOLUCIÓN SE INSTANCIÓ DENTRO DEL OBJETO Y NO POR FUERA
        // setDevolucion(devolucion);
        // HABILITAMOS AL USUARIO (CONPRESTADO = 0)
        // DISPONIBILIZAMOS LA UNIDDAD DEL LIBRO
        // SE DEBE DETERMINAR PLAZO DE ARRIENDO ASIGNADO POR TIPO USUARIO (10: ESTUDIANTE, 20: DOCENTE)
        // SI LA FECHA PACTADA DE DEVOLUCION NO SE CUMPLE, SE CALCULA LA DIFERENCIA EN DIAS ENTRE FECHA DE PRESTAMO VS FECHA DE DEVOLUCION, MENOS LOS DIAS APLICADOS POR TIPO USUARIO.
        // SE APLICARÁ MULTA DE $1.000 POR DIA DE ATRASO
        // EJECUTAMOS EL MÉTODO ASIGNAR DEVOLUCIÓN
    }
    
    public static Libro buscarLibro(int ISBN, ArrayList<Libro> libros) {
        // BUSCO EL LIBRO EN EL ARREGLO DE LIBROS
        for (int i = 0; i < libros.size(); i++) {
            // VOY OBTENIENDO CADA LIBRO EN EL ARREGLO DE LIBROS
            Libro libro = libros.get(i);
            
            // PREGUNTO SI EL ISBN DEL LIBRO ES IGUAL AL LIBRO QUE BUSCO
            if (libro.getISBN() == ISBN) {
                // SI LO ENCUENTRO, LO RETORNO
                return libro;
            }
        }
        
        // SI NO LO ENCUENTRO, RETORNO UN NULL
        return null;
    }
    
    public static Usuario buscarUsuario(String RUN, ArrayList<Usuario> usuarios) {
        // BUSCO EL LIBRO EN EL ARREGLO DE USUARIOS
        for (int i = 0; i < usuarios.size(); i++) {
            // VOY OBTENIENDO CADA USUARIO EN EL ARREGLO DE USUARIOS
            Usuario usuario = usuarios.get(i);
            
            // PREGUNTO SI EL RUT DEL USUARIO ES IGUAL AL RUN QUE BUSCO
            if (usuario.getRUN().equals(RUN)) {
                // SI LO ENCUENTRO, LO RETORNO
                return usuario;
            }
        }
        
        // SI NO LO ENCUENTRO, RETORNO UN NULL
        return null;
    }
    
    public static Prestamo buscarPrestamo(int ISBN, String RUN, ArrayList<Prestamo> prestamos) {
        // BUSCO EL PRESTAMO EN EL ARREGLO DE PRESTAMOS
        for (int i = 0; i < prestamos.size(); i++) {
            // VOY OBTENIENDO CADA PRESTAMO EN EL ARREGLO DE PRESTAMO
            Prestamo prestamo = prestamos.get(i);
            
            // PREGUNTO SI EL RUT DEL USUARIO ES IGUAL AL RUN QUE BUSCO Y EL ISBN DEL LIBRO ES IGUAL AL ISBN A BUSCAR
            // FALTA VALIDAR QUE EL PRÉSTAMO ESTÉ ACTUALMENTE ACTIVO Y NO ENCUENTRE UN PRÉSTAMO YA DEVUELVO
            if (prestamo.getUsuario().getRUN().equals(RUN) && prestamo.getLibro().getISBN() == ISBN) {
                // SI LO ENCUENTRO, LO RETORNO
                return prestamo;
            }
        }
        
        // SI NO LO ENCUENTRO, RETORNO UN NULL
        return null;
    }
    
    @Override
    public String toString() {
        // GENERAMOS UN ESTADO BASE
        String estadoBase = "Prestamo: \n" + 
                "ISBN: " + getLibro().getISBN() + "\n" +
                "RUN: " + getUsuario().getRUN() + "\n" +
                "Arrendado por: " + obtenerTipoDeUsuario() + "\n" + 
                "Estado: ";
        
        // LO MODIFICAMOS EN BASE A LA DEVOLUCIÓN
        if (getDevolucion() == null) {
            estadoBase += "En préstamo.";
        } else {
            estadoBase += "Devuelto.";
        }
        
        return estadoBase;
    }
}
