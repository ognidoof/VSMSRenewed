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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public static ArrayList<Dish> getDish(String vendor_id) {
        return IngredientDAO.getDish(vendor_id);
    }

    public static HashMap<Ingredient, ArrayList<String>> getIngredientQuantity(String dish_id, String vendor_id) {
        return IngredientDAO.getIngredientQuantity(dish_id, vendor_id);
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
