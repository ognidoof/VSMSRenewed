/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.IngredientDAO;
import Model.Dish;
import Model.Ingredient;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author TC
 */
public class IngredientController {
    
    IngredientDAO iDAO = new IngredientDAO();
    
    public Ingredient getIngredient(String supplierId, String ingredientName){
        return iDAO.getIngredient(supplierId, ingredientName);
    }
    
    public int addIngredient(Ingredient ingredient){
        return iDAO.addIngredient(ingredient);
    }
    
    public void deleteIngredient(Ingredient ingredient){
        iDAO.deleteIngredient(ingredient);
    }
    
    public void updateIngredient(Ingredient ingredient){
        iDAO.updateIngredient(ingredient);
    }
    
    public ArrayList<Dish> getDish(String vendor_id){
        return iDAO.getDish(vendor_id);
    }
    
    public HashMap<Ingredient, ArrayList<String>> getIngredientQuantity(String dish_id, String vendor_id) {
        return iDAO.getIngredientQuantity(dish_id, vendor_id);
    }
    
    public void addDish(Dish dish){
        iDAO.addDish(dish);
    }
    
    public void addIngredientQuantity(Ingredient ingredient,String quantity, String unit, String dish_id, String vendor_id){
        iDAO.addIngredientQuantity(ingredient, quantity, unit, dish_id, vendor_id);
    }
    
    public void deleteDish(Dish dish){
        iDAO.deleteDish(dish);
    }
    
    public void deleteIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id){
        iDAO.deleteIngredientQuantity(dish_id, ingredient_name, vendor_id, supplier_id);
    }
    
    public void updateDish(Dish dish){
        iDAO.updateDish(dish);
    }
    
    public void updateIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id, String quantity, String unit){
        iDAO.updateIngredientQuantity(dish_id, ingredient_name, vendor_id, supplier_id, quantity, unit);
    }
}
