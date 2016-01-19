/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.IngredientDAO;
import DAO.OrderDAO;
import Model.Dish;
import Model.Ingredient;
import Model.Order;
import Model.Orderline;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author TC
 */
public class OrderController {
    
//    Test controller
    public static void main(String[] args) {
        ArrayList<Order> orderList = OrderDAO.retrieveAllOrderList();
        for (Order order: orderList){
            System.out.println(order);
        }
    }
    
    
    public static ArrayList<Order> retrieveAllOrderList(){
        return OrderDAO.retrieveAllOrderList();
    }
    
    public static Order retrieveOrderByID(int order_id){
        return OrderDAO.retrieveOrderByID(order_id);
    }
    public static ArrayList<Order> retrieveOrderList(int vendor_id){
        return OrderDAO.retrieveOrderList(vendor_id);
    }
    public static ArrayList<Orderline> retrieveOrderLineList(int vendor_id,int order_id){
        return OrderDAO.retrieveOrderLineList(vendor_id, order_id);
    }
    public static void addOrder(Order order){
        OrderDAO.addOrder(order);
    }
    public static void deleteOrder(Order order){
        OrderDAO.deleteOrder(order);
    }
    
}
