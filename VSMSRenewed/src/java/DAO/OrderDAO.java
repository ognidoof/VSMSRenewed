/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Benjamin
 */
import Controller.ConnectionManager;
import Model.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class OrderDAO {

    
    
    
    private static void handleSQLException(SQLException e, String sql, String... parameters) {
        String msg = "Unable to access data. SQL : " + sql + "\n";

        for (String para : parameters) {
            msg += para + "\n";

        }

        Logger.getLogger(UserDAO.class
                .getName()).log(Level.SEVERE, msg, e);
        throw new RuntimeException(msg, e);
    }
    
    //methods retrieves ALL orders in the database regardless of vendor or supplier
    public static ArrayList<Order> retrieveAllOrderList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from order";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Retrieves the orders from the database
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int vendor_id = rs.getInt("vendor_id");
                double total_final_price = rs.getDouble("total_final_price");
                ArrayList<Orderline> orderLineList = retrieveOrderLineList(vendor_id, order_id );
               
                Order order = new Order(order_id, vendor_id ,total_final_price, orderLineList);
                orderList.add(order);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return orderList;
    }
    
    //methods retrieves all order from a particular vendor
    public static ArrayList<Order> retrieveOrderList(int vendor_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from order WHERE vendor_id = ##";
            sql = sql.replace("##", ""+vendor_id);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Retrieves the orders
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                
                //int vendor_id = rs.getInt("vendor_id");
                double total_final_price = rs.getDouble("total_final_price");
                ArrayList<Orderline> orderLineList = retrieveOrderLineList(vendor_id, order_id );
               
                Order order = new Order(order_id, vendor_id ,total_final_price, orderLineList);
                orderList.add(order);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return orderList;
    }
    
    
    //method retrieves all orderline items of a particular order
    public static ArrayList<Orderline> retrieveOrderLineList(int vendor_id, int order_id ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Orderline> orderLineList = new ArrayList<Orderline>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM `orderline` WHERE vendor_id = #1 && order_id = #2";
            sql = sql.replace("#1", ""+vendor_id);
            sql = sql.replace("#2", ""+order_id);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            //Retrieves the orderline info from database
            while (rs.next()) {
                
//                int vendor_id = rs.getInt("vendor_id");
//                int order_id = rs.getInt("order_id");
                int supplier_id = rs.getInt("supplier_id");
                String ingredient_name = rs.getString("ingredient_name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                double buffer_percentage = rs.getDouble("buffer_percentage");

                Orderline orderline = new Orderline(vendor_id, order_id ,supplier_id, ingredient_name, price, quantity, buffer_percentage);
                orderLineList.add(orderline);
            }
           
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return orderLineList;
    }
    
}
