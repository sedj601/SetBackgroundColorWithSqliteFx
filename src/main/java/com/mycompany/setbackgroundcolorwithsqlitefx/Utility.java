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
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("R:").append((int)(color.getRed() * 225)).append("_");
        stringBuilder.append("G:").append((int)(color.getGreen() * 225)).append("_");
        stringBuilder.append("B:").append((int)(color.getBlue() * 225)).append("_");
        stringBuilder.append("O:").append(color.getOpacity());
                    
        return stringBuilder.toString();
    }
    
    public static Color DecodeColor(String input)
    {
        if(input.isBlank())
        {
            return Color.WHITE;
        }
        
        Map<String, Double> inputMap = new HashMap();
        
        List<String> inputList = Arrays.asList(input.split("_"));
        inputList.stream().forEach((t) -> {
            inputMap.put(t.split(":")[0], Double.valueOf(t.split(":")[1]));
        });
        
        System.out.println(inputMap.get("R") + " " + inputMap.get("G") + " " + inputMap.get("B") + " " + inputMap.get("O"));
        return Color.rgb(inputMap.get("R").intValue(), inputMap.get("G").intValue(), inputMap.get("B").intValue(), inputMap.get("O"));
    }
}
