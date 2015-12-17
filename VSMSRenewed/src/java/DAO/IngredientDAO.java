/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.ConnectionManager;
import Model.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author David
 */
public class IngredientDAO {
    public Ingredient getIngredient(String supplierId, String ingredientName){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        Ingredient ingredient=null;
        
        try
        {
            conn=ConnectionManager.getConnection();
            query = "select * from Ingredient where supplier_id=? & ingredient_name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1,supplierId);
            statement.setString(2,ingredientName);
            rs = statement.executeQuery();
            while(rs.next()){
                int supId=Integer.parseInt(rs.getString("supplier_id"));
                ingredient=new Ingredient(supId, rs.getString("ingredient_name"), rs.getString("supplyunit"), rs.getString("subcategory"), rs.getString("ingredient_description"), rs.getString("offeredprice"));                
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
            {
                try
                {
                    statement.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(conn != null)
            {
                try
                {
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }                
            }
        }
        return ingredient;
    }
    
    public void addIngredient(Ingredient ingredient){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "insert into Ingredient (supplier_id, ingredient_name, supplyunit, subcategory, ingredient_description, offeredprice) values (?,?,?,?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, ingredient.getSupplier_id()+"");
            statement.setString(2, ingredient.getName());
            statement.setString(3, ingredient.getSupplyUnit());
            statement.setString(4, ingredient.getSubcategory());
            statement.setString(5, ingredient.getDescription());
            statement.setString(6, ingredient.getOfferedPrice());
            int row = statement.executeUpdate();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
            {
                try
                {
                    statement.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(conn != null)
            {
                try
                {
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }                
            }
        }
    }
    
    public void deleteIngredient(Ingredient ingredient){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "Delete from Ingredient where supplier_id=? & ingredient_name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, ingredient.getSupplier_id()+"");
            statement.setString(2, ingredient.getName());
            int row = statement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
            {
                try
                {
                    statement.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(conn != null)
            {
                try
                {
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }                
            }
        } 
    }
    
    public void updateIngredient(Ingredient ingredient){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "update Ingredient set supplier_id=?, ingredient_name=?, supplyunit=?, subcategory=?, ingredient_description=?, offeredprice=? where supplier_id=? & ingredient_name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, ingredient.getSupplier_id()+"");
            statement.setString(2, ingredient.getName());
            statement.setString(3, ingredient.getSupplyUnit());
            statement.setString(4, ingredient.getSubcategory());
            statement.setString(5, ingredient.getDescription());
            statement.setString(6, ingredient.getOfferedPrice());
            statement.setString(7, ingredient.getSupplier_id()+"");
            statement.setString(8, ingredient.getName());
            int row = statement.executeUpdate();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
            {
                try
                {
                    statement.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(conn != null)
            {
                try
                {
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }                
            }
        }
    }
}
