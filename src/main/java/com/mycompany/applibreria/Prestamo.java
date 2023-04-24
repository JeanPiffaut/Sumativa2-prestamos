/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Tom
 */
public final class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private GregorianCalendar fecha;
    private Devolucion devolucion;
    
    public Prestamo(Usuario usuario, Libro libro) {
        setUsuario(usuario);
        setLibro(libro);
        setFecha(fecha);
        setDevolucion(devolucion);
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
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
    
    public GregorianCalendar getFechaDevolucion() {
        GregorianCalendar fecha_prestamo = getFecha();
        String tipo = obtenerTipoDeUsuario();
        int dias = 0;
        if (tipo == "Docente") {
            dias = 20;
        } else {
            dias = 10;
        }
                
        fecha_prestamo.add(Calendar.DATE, dias);
        return fecha_prestamo;
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
        if(libro.getCant_disponible() > 0){
            // AQUÍ VALIDAMOS QUE EL USUARIO DEBE ESTAR HABILITADO PARA PRÉSTAMO //
            if (usuario.getConPrestamo() == 0){
                    try{
                        //TODO OK, PRESTAMOS EL LIBRO
                        Prestamo prestamo = new Prestamo(usuario, libro);
                        // ---------------- LO QUE SE DEBE HACER A CONTINUACIÓN SE PUEDE REALIZAR DENTRO DE ÉSTE MÉTODO Ó ----------------
                        // ----------------------------- DENTRO DE LA INSTANCIACIÓN DEL OBJETO -------------------------------------------
                        
                        // SI SE INTENTA DE ARRENDAR EL MISMO USUARIO CON EL MISMO LIBRO, IMPRIMIR ALERTA //

                        // REDUCIMOS LA CANTIDAD DISPONIBLE DEL LIBRO
                        libro.setCant_disponible(libro.getCant_disponible()-1);
                        // DEJAMOS AL USUARIO INHABILITADO PARA NUEVOS PRÉSTAMOS, ASIGNANDO EL COD ISBN AL ATRIBUTO: CONPRESTAMO
                        usuario.setConPrestamo(libro.getISBN());
                        // INSERTAMOS EN EL ARCHIVO HISTORICO DE PRESTAMOS CON INFO ADICIONAL (ISBN, FECHA_ACTUAL, PERIODO DE PRESTAMO AUTORIZADO, FECHA_DEVOLUCION "CALCULADA AUTO%")
                        prestamo.setUsuario(usuario);
                        prestamo.setLibro(libro);
                        // IMPRIMIMOS POR PANTALLA UN VOUCHER DE ARRIENDO (FORMATO A DEFINIR)

                        // RETORNAMOS EL PRÉSTAMO VALIDADO
                        return prestamo;
                    }catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
            }
        }else{
            throw new IllegalArgumentException("El Libro sin unidades suficientes para préstamo.");
        }
        
        return null;
    }
    
    public void ingresarDevolucion(int ISBN, String RUN, ArrayList<Prestamo> prestamos) throws FileNotFoundException, IOException {
        
        // A PARTIR DEL ENUNCIADO:
        // DEBEMOS VALIDAR QUE EL USUARIO A BUSCAR Y EL ISBN EXISTAN
        ArrayList<Usuario> usuarios = AppLibreria.cargarUsuarios("usuarios.csv");
        Usuario usuario = buscarUsuario(RUN, usuarios);
        
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MÉTODO BUSCAR PRESTAMO
        Prestamo prestamo = buscarPrestamo(ISBN, RUN, prestamos);
        
        // SI EL PRÉTAMO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (prestamo == null) {
            throw new IllegalArgumentException("El prestamo a buscar no existe.");
        }
        
        // ASIGNO LA DEVOLUCIÓN RESPETANDO LA RELACIÓN DE COMPOSICIÓN
        Devolucion devolucion = new Devolucion(ISBN,RUN,0);
        // ASINGO LA DEVOLUCIÓN RESPETANDO LA RELACIÓN DE COMPOSICIÓN
        // DEBIDO A QUE DEVOLUCIÓN SE INSTANCIÓ DENTRO DEL OBJETO Y NO POR FUERA
        setDevolucion(devolucion);
        
        // HABILITAMOS AL USUARIO (CONPRESTADO = 0)
        usuario.setConPrestamo(0);
        
        // DISPONIBILIZAMOS LA UNIDDAD DEL LIBRO
        int cant_dispo = getLibro().getCant_disponible() + 1;
        getLibro().setCant_disponible(cant_dispo);
        
        // SE DEBE DETERMINAR PLAZO DE ARRIENDO ASIGNADO POR TIPO USUARIO (10: ESTUDIANTE, 20: DOCENTE)
        String tipo_usuario = obtenerTipoDeUsuario();
        int dias_prestamo;
        if (tipo_usuario == "Docente") {
            dias_prestamo = 20;
        } else {
            dias_prestamo = 10;
        }
                
        // SI LA FECHA PACTADA DE DEVOLUCION NO SE CUMPLE, SE CALCULA LA DIFERENCIA EN DIAS ENTRE FECHA DE PRESTAMO VS FECHA DE DEVOLUCION, MENOS LOS DIAS APLICADOS POR TIPO USUARIO.
        Date fecha_prestamo = getFecha().getTime();
        Date fecha_devolucion = getFechaDevolucion().getTime();

        // Calcular la diferencia en milisegundos entre las dos fechas
        long diffInMilliseconds = Math.abs(fecha_devolucion.getTime() - fecha_prestamo.getTime());

        // Convertir la diferencia en milisegundos a días
        int diffInDays = (int) (diffInMilliseconds / (1000 * 60 * 60 * 24));

        // Comparar la diferencia en días con la cantidad de días almacenada en otra variable
        if (diffInDays <= dias_prestamo) {
            int atraso = dias_prestamo - diffInDays;
        }
        // SE APLICARÁ MULTA DE $1.000 POR DIA DE ATRASO
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
    
    public String getFechaFormateada(GregorianCalendar fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha.getTime());
    }
    
    public void generaVoucher() {
        String voucher = 
               "---------------------------------------------\n" +
               "              LIBRERIA UNIVERSIDAD ANDRES BELLO   \n" +
               "              USUARIO:  + " + usuario.getNombreCompleto() + "\n"+
               "              ISBN: " + libro.getISBN() + "\n"+
               "---------------------------------------------\n" +
               "|  FECHA PRESTAMO   |  TITULO LIBRO   |  FECHA DEVOLUCION   |  TOTAL MULTA   |\n" +
               "---------------------------------------------\n" +
               "|  " + getFechaFormateada(getFecha()) + "   |  " + libro.getTitulo() + "   |  " + getFechaFormateada(getFechaDevolucion()) +  "   |  $XXXXX   |\n" +
               "---------------------------------------------\n" +
               "                                             \n" + 
               "                               ______________\n" + 
               "                               FIRMA USUARIO \n\n";
        
        System.out.println(voucher);
    }
    
    @Override
    public String toString() {
        // GENERAMOS UN ESTADO BASE
        String estadoBase = "PRESTAMO\nISBN: " + getLibro().getISBN() + "\n" +
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
