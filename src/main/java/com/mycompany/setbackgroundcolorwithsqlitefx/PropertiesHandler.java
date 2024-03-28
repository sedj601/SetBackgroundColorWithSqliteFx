/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.setbackgroundcolorwithsqlitefx;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blj0011
 */
public class PropertiesHandler implements AutoCloseable{
    Connection connection;
    
    public PropertiesHandler()
    {
        try 
        {            
            Path currentRelativePath = Paths.get("");
            String currentDirectory = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current absolute path is: " + currentDirectory);
            connection = DriverManager.getConnection("jdbc:sqlite:" + currentDirectory + "/properties.sqlite3");
            //connection.createStatement().execute("PRAGMA foreign_keys = ON");
            System.out.println("Connected to SQLite Db: properties.sqlite3");
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.toString());
        }         
    }    
    
    public Map<String, String> getAllProperties()
    {
        Map<String, String> properties = new HashMap();
        
        String query = """
                       SELECT 
                            *
                       FROM 
                            KeyValue
                       """;
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) 
        {
            while (resultSet.next()) {
                properties.put(resultSet.getString("key"), resultSet.getString("value") == null? "" : resultSet.getString("value"));          
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return properties;
    }
    
    public String getProperty(String key)
    {        
        String query = """
                       SELECT 
                            value
                       FROM 
                            KeyValue
                       WHERE
                            key = ?
                       """;
        
        try (PreparedStatement pstmt = connection.prepareStatement(query))      
        {
            try(ResultSet resultSet = pstmt.executeQuery(query))
            {
                pstmt.setString(1, key);
                while (resultSet.next()) 
                {
                    return resultSet.getString("value") == null? "" : resultSet.getString("value");          
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.toString());
                return "";
            }
        }   
        catch (SQLException ex) 
        {
            Logger.getLogger(PropertiesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public boolean updateProperty(String key, String value)
    {
        String query = "UPDATE KeyValue SET value = ? WHERE key = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, value);
            pstmt.setString(2, key);
            pstmt.executeUpdate();

            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    } 
    
    public boolean addProperty(String key, String value)
    {
        String sqlQuery = "INSERT INTO KeyValue(key, value) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.executeUpdate();

            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    
     public boolean deleteProperty(String key)
    {
        Boolean control = false;
        
        String sqlQuery = "DELETE FROM KeyValue WHERE key = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) 
        {
            pstmt.setString(1, key);
            pstmt.executeUpdate();

            control = true;
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return control;
    }
         
    public void closeConnection()
    {
        try {
            connection.close();
            System.out.println("Closed connection to SQLite Db: properties.sqlite3");
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    @Override
    public void close() throws Exception {
        closeConnection();
    }
}
