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
    
    
}
