/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.IngredientDAO;
import Model.Dish;
import Model.Ingredient;
import Model.Order;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ingredientservlet/*")
public class IngredientController extends HttpServlet {

    @Override
    //doPost will be given to Menu.java
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //A dish needs: dish_id (controller generated),dish_name, vendor_id, dish_description, ingredientQuantity(to be added);
        String dish_name = request.getParameter("dish_name");
        String dish_description = request.getParameter("dish_description");
        String vendor_idStr = request.getParameter("vendor_id");
        System.out.println("It reaches here: "+dish_name+","+dish_description+","+vendor_idStr);
        
        //Check null values to add the creation process
        if(!UtilityController.checkNullStringArray(new String[]{dish_name,dish_description,vendor_idStr})){
            System.out.println("This is the checknullstring array");
            // Getting the id of dish and vendors
            //Get the number of dishes and add by one to become dish_id and create blank ingredient dish first. 
            //This is less efficient, cause need to call dishes at least twice, but it would be faster than iterating or filtering alldish
            ArrayList<Dish> getAllDish = getAllDish();
            int dish_id = getAllDish.size()+1;
            int vendor_id = UtilityController.convertStringtoInt(vendor_idStr);

            addDish(new Dish(dish_id,dish_name,vendor_id,dish_description));

        }
        System.out.println("This is the array after checknull");
        
        String dishListString = "";        
        ArrayList<Dish> dishList = getDish("1");
        
        System.out.println("The dishlist is " +dishList);
        for (Dish dish :dishList){
            dishListString += "<li>"+dish+"</li>";
        }
        
        response.setContentType("text/plain");  // Set content type of the response so that AJAX knows what it can expect.
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(dishListString);       // Write response body.
    }
    
    @Override
    //doGet will be given to RecipeBuilder.java
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplier_id = UtilityController.convertStringtoInt(request.getParameter("supplier_id")); 
        String name = request.getParameter("name");
        String supplyUnit = request.getParameter("supplyUnit");
        String subcategory = request.getParameter("subcategory");
        String description = request.getParameter("description");
        String offeredPrice = request.getParameter("offeredPrice");
        
        Ingredient ingredient = new Ingredient(supplier_id,name,supplyUnit, subcategory,description,offeredPrice);
        String dishListString = "";
        
        ArrayList<Dish> dishList = getDish("1");
        for (Dish dish :dishList){
            dishListString += "<li>"+dish+"</li>";
        }
        
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(dishListString);       // Write response body.
    }

    public static ArrayList<Ingredient> getIngredientBySupplier(int supplier_id) {
        return IngredientDAO.getIngredientBySupplier(supplier_id);
    }

    public static Ingredient getIngredient(String supplierId, String ingredientName) {
        return IngredientDAO.getIngredient(supplierId, ingredientName);
    }

    public static int addIngredient(Ingredient ingredient) {
        return IngredientDAO.addIngredient(ingredient);
    }

    public static void deleteIngredient(Ingredient ingredient) {
        IngredientDAO.deleteIngredient(ingredient);
    }

    public static void updateIngredient(Ingredient ingredient) {
        IngredientDAO.updateIngredient(ingredient);
    }
    
    public static ArrayList<Dish> getAllDish() {
        return IngredientDAO.getAllDish();
    }
    
    public static ArrayList<Dish> getDish(String vendor_id) {
        return IngredientDAO.getDish(vendor_id);
    }

    public static HashMap<Ingredient, ArrayList<String>> getIngredientQuantity(String dish_id) {
        return IngredientDAO.getIngredientQuantity(dish_id);
    }

    public static void addDish(Dish dish) {
        IngredientDAO.addDish(dish);
    }

    public static void addIngredientQuantity(Ingredient ingredient, String quantity, String unit, String dish_id, String vendor_id) {
        IngredientDAO.addIngredientQuantity(ingredient, quantity, unit, dish_id, vendor_id);
    }

    public static void deleteDish(Dish dish) {
        IngredientDAO.deleteDish(dish);
    }

    public static void deleteIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id) {
        IngredientDAO.deleteIngredientQuantity(dish_id, ingredient_name, vendor_id, supplier_id);
    }

    public static void updateDish(Dish dish) {
        IngredientDAO.updateDish(dish);
    }

    public static void updateIngredientQuantity(String dish_id, String ingredient_name, String vendor_id, String supplier_id, String quantity, String unit) {
        IngredientDAO.updateIngredientQuantity(dish_id, ingredient_name, vendor_id, supplier_id, quantity, unit);
    }

    public static ArrayList<Integer> getSupplierIdByIngredient(String ingredient_name) {
        return IngredientDAO.getSupplierIdByIngredient(ingredient_name);
    }
}
