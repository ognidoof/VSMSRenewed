<%-- 
    Document   : OrderPage
    Created on : 19 Jan, 2016, 1:36:08 PM
    Author     : Joel
--%>

<%@page import="java.util.Map"%>
<%@page import="Model.Ingredient"%>
<%@page import="java.util.HashMap"%>
<%@page import="Model.Dish"%>
<%@page import="Model.Dish"%>
<%@page import="DAO.IngredientDAO"%>
<%@page import="Model.Supplier"%>
<%@page import="Model.Orderline"%>
<%@page import="Model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.OrderDAO"%>
<%@page import="Model.Vendor"%>
<%@page import="DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            int vendorID = Integer.parseInt(((String)session.getAttribute("vendorID")));
            Vendor currentVendor = UserDAO.getVendorByID(vendorID);
            
            //display list of all orders
            ArrayList<Order> orderList = OrderDAO.retrieveOrderList(vendorID);
            for (Order o: orderList){
                int orderID = o.getOrder_id();
                double totalPrice = o.getTotal_final_price();
                ArrayList<Orderline> orderlines = o.getOrderlines();
                %>
                <a>Order ID: <%=orderID%></a>
                <a>Total Order Price: <%=totalPrice%></a>
                <%
                for(Orderline ol : orderlines){
                    int supplier_id = ol.getSupplier_id();
                    Supplier supMan = UserDAO.getSupplierById(supplier_id);
                    String supName = supMan.getSupplier_name();
                    
                    String ing_name = ol.getIngredient_name();
                    int quantity = ol.getQuantity();
                    double price = ol.getFinalprice();
                    
                    %>
                        <a>Supplier: <%=supName%></a>
                        <a>Ingredient: <%=ing_name%></a>
                        <a>Quantity: <%=quantity%></a>
                    <%
                }
            }
        %>
        
        <%
            //create new orders
            ArrayList<Dish> dishlist = IngredientDAO.getDish("" + vendorID);
            
            //with dish and quantity, create an order
            Dish dish = dishlist.get(1);
            int dish_quantity = 1;
            
            HashMap<Ingredient, ArrayList<String>> map = dish.getIngredientQuantity();
            for(Map.Entry<Ingredient, ArrayList<String>> entry : map.entrySet()){
                Ingredient key = entry.getKey();
                ArrayList<String> slist = entry.getValue();
                String quantity = slist.get(0);
                String unit = slist.get(1);
            }

        %>
    </body>
</html>
