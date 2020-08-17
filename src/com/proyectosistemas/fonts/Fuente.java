/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosistemas.fonts;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author Alejandro
 */
public class Fuente {
    private Font font= null;
    public String decker = "Decker.ttf";
    
    public Font fuente (String nombreFont, int estilo, float tamano){
        try{
            //se carga la fuente
            InputStream is = getClass().getResourceAsStream(nombreFont);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(Exception ex){
            //En el caso de un error carga por default en arial
            System.err.println(nombreFont+ "No se cargo el font correctamente!");
            font = new Font("Arial", Font.PLAIN, 14);
        }
    Font tfont = font.deriveFont(estilo, tamano);
    return tfont;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getDecker() {
        return decker;
    }

    public void setDecker(String decker) {
        this.decker = decker;
    }
    
    
}
