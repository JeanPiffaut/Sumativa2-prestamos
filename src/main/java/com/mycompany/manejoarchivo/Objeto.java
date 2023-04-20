/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manejoarchivo;

/**
 *
 * @author Tom
 */
public class Objeto {
    private String columna1;
    private String columna2;
    private String columna3;
    private String columna4;
    private String columna5;

    public Objeto(String columna1, String columna2, String columna3, String columna4, String columna5) {
        setColumna1(columna1);
        setColumna2(columna2);
        setColumna3(columna3);
        setColumna4(columna4);
        setColumna5(columna5);
    }

    /**
     * @return the columna1
     */
    public String getColumna1() {
        return columna1;
    }

    /**
     * @param columna1 the columna1 to set
     */
    public void setColumna1(String columna1) {
        this.columna1 = columna1;
    }

    /**
     * @return the columna2
     */
    public String getColumna2() {
        return columna2;
    }

    /**
     * @param columna2 the columna2 to set
     */
    public void setColumna2(String columna2) {
        this.columna2 = columna2;
    }

    /**
     * @return the columna3
     */
    public String getColumna3() {
        return columna3;
    }

    /**
     * @param columna3 the columna3 to set
     */
    public void setColumna3(String columna3) {
        this.columna3 = columna3;
    }

    /**
     * @return the columna4
     */
    public String getColumna4() {
        return columna4;
    }

    /**
     * @param columna4 the columna4 to set
     */
    public void setColumna4(String columna4) {
        this.columna4 = columna4;
    }

    /**
     * @return the columna5
     */
    public String getColumna5() {
        return columna5;
    }

    /**
     * @param columna5 the columna5 to set
     */
    public void setColumna5(String columna5) {
        this.columna5 = columna5;
    }
    
    @Override
    public String toString() {
       return "Columna 1: " + getColumna1() + "\n" + 
               "Columna 2: " + getColumna2() + "\n" +
               "Columna 3: " + getColumna3() + "\n" +
               "Columna 4: " + getColumna4() + "\n" +
               "Columna 5: " + getColumna5();
    };
    
    public String toCSV() {
        return getColumna1() + ";" + getColumna2() + ";" + getColumna3() + ";" + getColumna4() + ";" + getColumna5();
    }
}
