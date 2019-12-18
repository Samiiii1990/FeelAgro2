/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feelagro.Proyecto.Final.entidades.Errores;

/**
 *
 * @author Familia Romero
 */
public class ErrorServicio extends Exception {
    
    /**
     *
     * @param men
     */
    public ErrorServicio(String men){
        
        super(men);
        
    }
    
}
