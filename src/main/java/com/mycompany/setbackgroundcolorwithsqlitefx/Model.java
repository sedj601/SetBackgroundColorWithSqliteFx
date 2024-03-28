/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.setbackgroundcolorwithsqlitefx;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author blj0011
 */
public class Model {

    public Map<String, String> getAllProperties()
    {
        try(PropertiesHandler propertiesHandler = new PropertiesHandler())
        {
           return propertiesHandler.getAllProperties();
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Color getBackgroundColorProperty()
    {
        try(PropertiesHandler propertiesHandler = new PropertiesHandler())
        {
            String value = propertiesHandler.getProperty("BACKGROUND_COLOR");
            if(!value.isBlank())
            {
                return Utility.DecodeColor(value);
            } 
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Color.WHITE;
    }
    
    public boolean updateBackgroundColorProperty(Color color)
    {
        try(PropertiesHandler propertiesHandler = new PropertiesHandler())
        {
            return propertiesHandler.updateProperty("BACKGROUND_COLOR", Utility.EncodeColor(color));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean getFullScreenProperty()
    {
        try(PropertiesHandler propertiesHandler = new PropertiesHandler())
        {
            String value = propertiesHandler.getProperty("FULL_SCREEN");
            if(!value.isBlank())
            {
                return value.equals("YES");
            }                
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateFullScreenProperty(boolean isFullScreen)
    {
        try(PropertiesHandler propertiesHandler = new PropertiesHandler())
        {
            return propertiesHandler.updateProperty("FULL_SCREEN", isFullScreen? "YES" : "NO");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
