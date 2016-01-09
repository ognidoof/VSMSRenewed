/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.ConnectionManager;
import Model.Dish;
import Model.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
            query = "select * from ingredient where supplier_id=? AND ingredient_name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1,supplierId);
            statement.setString(2,ingredientName);
            rs = statement.executeQuery();
            while(rs.next()){
                int supId=Integer.parseInt(rs.getString("supplier_id"));
                ingredient=new Ingredient(supId, rs.getString("ingredient_name"), rs.getString("supply_unit"), rs.getString("category"), rs.getString("ingredient_description"), rs.getString("offered_price"));                
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
    
    public int addIngredient(Ingredient ingredient){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        int row=0;
        try
        {
            conn=ConnectionManager.getConnection();
            query = "insert into ingredient (supplier_id, ingredient_name, supply_unit, category, ingredient_description, offered_price) values (?,?,?,?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, ingredient.getSupplier_id()+"");
            statement.setString(2, ingredient.getName());
            statement.setString(3, ingredient.getSupplyUnit());
            statement.setString(4, ingredient.getSubcategory());
            statement.setString(5, ingredient.getDescription());
            statement.setString(6, ingredient.getOfferedPrice());
            row = statement.executeUpdate();
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
                    return row;

    }
    
    public void deleteIngredient(Ingredient ingredient){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "Delete from ingredient where supplier_id=? AND ingredient_name=?";
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
            query = "update Ingredient set supply_unit=?, category=?, ingredient_description=?, offered_price=? where supplier_id=? AND ingredient_name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, ingredient.getSupplyUnit());
            statement.setString(2, ingredient.getSubcategory());
            statement.setString(3, ingredient.getDescription());
            statement.setString(4, ingredient.getOfferedPrice());
            statement.setString(5, ingredient.getSupplier_id()+"");
            statement.setString(6, ingredient.getName());
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
    
    public ArrayList<Dish> getDish(String vendor_id){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        ArrayList<Dish> dishList=new ArrayList<Dish>();
        try
        {
            conn=ConnectionManager.getConnection();
            query = "select * from Dish where vendor_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, vendor_id);
            rs = statement.executeQuery();
            while(rs.next()){
               int dishId=Integer.parseInt(rs.getString("dish_id"));
               int venId=Integer.parseInt(rs.getString("vendor_id"));
               dishList.add(new Dish(dishId,rs.getString("dish_name"),venId, rs.getString("dish_description"), getIngredientQuantity(rs.getString("dish_id"), vendor_id)));   
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
        return dishList;
    }
        
    public HashMap<Ingredient, ArrayList<String>> getIngredientQuantity(String dish_id, String vendor_id) {
        HashMap<Ingredient, ArrayList<String>> toReturn = new HashMap<Ingredient, ArrayList<String>>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";

        try {
            conn = ConnectionManager.getConnection();
            query = "select * from IngredientQuantity where dish_id=? AND vendor_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, dish_id);
            statement.setString(2, vendor_id);
            rs = statement.executeQuery();
            while (rs.next()) {
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.add(rs.getString("quantity"));
                tempList.add(rs.getString("unit"));
                toReturn.put(this.getIngredient(rs.getString("supplier_id"), rs.getString("ingredient_name")), tempList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return toReturn;
    }
    
    public void addDish(Dish dish){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        
        
        try
        {
            conn=ConnectionManager.getConnection();
            query = "insert into Dish (dish_id, dish_name, vendor_id, dish_description) values (?,?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setString(1,dish.getDish_id()+"");
            statement.setString(2,dish.getDish_name());
            statement.setString(3,dish.getVendor_id()+"");
            statement.setString(4,dish.getDish_description());
            int rows = statement.executeUpdate();
            HashMap<Ingredient, ArrayList<String>> map=dish.getIngredientQuantity();
            Set<Ingredient> iSet=map.keySet();
            Iterator<Ingredient> iter=iSet.iterator();
            while(iter.hasNext()){
                Ingredient tempI=iter.next();
                ArrayList<String> tempList=map.get(tempI);
                this.addIngredientQuantity(tempI, tempList.get(0), tempList.get(1), dish.getDish_id()+"", dish.getVendor_id()+"");
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
        
    }
    
    public void addIngredientQuantity(Ingredient ingredient,String quantity, String unit, String dish_id, String vendor_id){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query ="insert into IngredientQuantity (dish_id, ingredient_name, vendor_id, supplier_id, quantity, unit) values (?,?,?,?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setString(1,dish_id);
            statement.setString(2,ingredient.getName());
            statement.setString(3,vendor_id);
            statement.setString(4,ingredient.getSupplier_id()+"");
            statement.setString(5, quantity);
            statement.setString(6, unit);
            int rows = statement.executeUpdate();
           
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
    
    public void deleteDish(Dish dish){
        HashMap<Ingredient, ArrayList<String>> map=dish.getIngredientQuantity();
        Set<Ingredient> iSet = map.keySet();
        Iterator<Ingredient> iter = iSet.iterator();
        while (iter.hasNext()) {
            Ingredient tempI = iter.next();
            ArrayList<String> tempList = map.get(tempI);
            this.deleteIngredientQuantity(dish.getDish_id()+"", tempI.getName(), dish.getVendor_id()+"", tempI.getSupplier_id() + "");
        }
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        
        
        try
        {
            conn=ConnectionManager.getConnection();
            query = "Delete from Dish where dish_id=? AND vendor_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, dish.getDish_id()+"");
            statement.setString(2, dish.getVendor_id()+"");
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
    
    public void deleteIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "Delete from IngredientQuantity where dish_id=? AND ingredient_name=? AND vendor_id=? AND supplier_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, dish_id);
            statement.setString(2, ingredient_name);
            statement.setString(3, vendor_id);
            statement.setString(4, supplier_id);
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
    
    public void updateDish(Dish dish){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        
        
        try
        {
            conn=ConnectionManager.getConnection();
            query = "update Dish set dish_id=?, dish_name=?, vendor_id=?, dish_description=? where dish_id=? AND vendor_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, dish.getDish_id()+"");
            statement.setString(2, dish.getDish_name());
            statement.setString(3, dish.getVendor_id()+"");
            statement.setString(4, dish.getDish_description());
            statement.setString(5, dish.getDish_id()+"");
            statement.setString(3, dish.getVendor_id()+"");
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
        HashMap<Ingredient, ArrayList<String>> map=dish.getIngredientQuantity();
        Set<Ingredient> iSet = map.keySet();
        Iterator<Ingredient> iter = iSet.iterator();
        while (iter.hasNext()) {
            Ingredient tempI = iter.next();
            ArrayList<String> tempList = map.get(tempI);
            this.updateIngredientQuantity(dish.getDish_id()+"", tempI.getName(), dish.getVendor_id()+"", tempI.getSupplier_id() + "",tempList.get(0),tempList.get(1));
        }
    }
    
    public void updateIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id, String quantity, String unit){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";
        try
        {
            conn=ConnectionManager.getConnection();
            query = "update IngredientQuantity set dish_id=?, ingredient_name=?, vendor_id=?, supplier_id=?, quantity=?, unit=? where dish_id=? AND ingredient_name=? AND vendor_id=? AND supplier_id=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, dish_id);
            statement.setString(2, ingredient_name);
            statement.setString(3, vendor_id);
            statement.setString(4, supplier_id);
            statement.setString(5, quantity);
            statement.setString(6, unit);
            statement.setString(7, dish_id);
            statement.setString(8, ingredient_name);
            statement.setString(9, vendor_id);
            statement.setString(10, supplier_id);
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
