/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.setbackgroundcolorwithsqlitefx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 *
 * @author blj0011
 */
public class Utility {
    public static String EncodeColor(Color color)
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
    
    public static Color DecodeColor(String input)
    {
        if(input.isBlank())
        {
            return Color.WHITE;
        }
        
        return Color.web(input);
    }
}
