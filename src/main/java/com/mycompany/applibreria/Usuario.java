/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

//import java.io.Console;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public abstract class Usuario {
    private String RUN;
    private String nombreCompleto;
    private char genero;
    private String carrera;
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
    
        /**
     * @return the conPrestamo
     */
    
        /**
     * @return the conPrestamo
     */
    public int getConPrestamo() {
        return conPrestamo;
    }

    /**
     * @param conPrestamo the conPrestamo to set
     */
    public void setConPrestamo(int conPrestamo) {
        this.conPrestamo = conPrestamo;
    }

    
    public static boolean crearUsuario(Usuario user, ArrayList<Usuario> usuarios)
    {
        try{
            
        for(int i =0; i < usuarios.size(); i++)
        {
            Usuario usuario = usuarios.get(i);
            
            if(usuario.getRUN().equals(user.getRUN()))
            {
                System.out.println("El rut ingresado ya existe");
                return false;
            }
            
            boolean validaRut = validarRut(user.getRUN());
            if(validaRut == false)
            {
                System.out.println("el rut ingresado no es valido");
                return false;
            }
            
            if(user.getGenero() != 'M' || user.getGenero() != 'F')
            {
                System.out.println("El genero ingresado no es correcto");
                     
                return false;
            }            
        }      
        user.setConPrestamo(0);
        usuarios.add(user);
        System.out.println("Usuario agregado correctamente");
        return true;
        
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("El usuario no se ha ingresado");
        }
    }
    
    public static boolean editarUsuario(Usuario user, ArrayList<Usuario> usuarios)
    {
        try{
            
        for(int i =0; i < usuarios.size(); i++)
        {
            Usuario usuario = usuarios.get(i);
            
            if(usuario.getRUN().equals(user.getRUN()))
            {
                 boolean validaRut = validarRut(user.getRUN());
            if(validaRut == false)
            {
                System.out.println("el rut ingresado no es valido");
                return false;
            }
            
            if(user.getGenero() != 'M' || user.getGenero() != 'F')
            {
                System.out.println("El genero ingresado no es correcto");
                     
                return false;
            }
            
            if(user.getConPrestamo() == 1)
            {
                System.out.println("el usuario tiene un prestamo activo");
                return false;
            }
            
                usuario.genero = user.getGenero();
                usuario.nombreCompleto = user.getNombreCompleto();
                usuario.carrera = user.getCarrera();
                usuario.setConPrestamo(user.getConPrestamo());
                System.out.println("Usuario modificado correctamente");
                return true;
            }                  
        }
            return true;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("El usuario no se ha ingresado");
        }
    }
        
    public static boolean eliminarUsuario(String rut, ArrayList<Usuario> usuarios)
    {
        try{
            
            for(int i =0; i < usuarios.size(); i++)
            {
                Usuario usuario = usuarios.get(i);
                
                if(usuario.getRUN().equals(rut))
                {
                    usuarios.remove(i);
                    System.out.println("Usuario eliminado correctamente");
                    return true;
                }                
            }
            return false;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("El usuario no se ha eliminado");
        }        
    }
    
    public static boolean validarRut(String cedula)
    {
        boolean validacion = false;
        try {
            String rut =  cedula.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
            throw new IllegalArgumentException("El rut ingresado no es valido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("El rut ingresado no es valido.");
        }
        return validacion;
    }
    
    public String toCSV() {
        // AGREGAR LOS ATRIBUTOS ADICIONALES SEPARADOS POR ;
        // 1-1;Tomás S;1
        
        return getRUN() + ";" + getNombreCompleto() + ";" + getCarrera()+ ";" + getGenero() + ";" + getConPrestamo() ;
    }


}

