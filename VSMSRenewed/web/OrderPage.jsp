<%-- 
    Document   : OrderPage
    Created on : 19 Jan, 2016, 1:36:08 PM
    Author     : Joel
--%>

<%@page import="java.util.Date"%>
<%@page import="Controller.OrderController"%>
<%@page import="Controller.IngredientController"%>
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
            //create new orders//
            
            ArrayList<Dish> dishlist = IngredientController.getDish("" + vendorID);
            
            //with dish and quantity to order selected by vendor, create an order
            HashMap<Dish, Integer> actualOrders = new HashMap<Dish, Integer>();//change this to actual form input
            
            //list of orderIDs
            ArrayList<Integer> order_id_list = new ArrayList<Integer>();
            
            //get orderlines
            ArrayList<Orderline> mixedList = new ArrayList<Orderline>();
            for(Map.Entry<Dish, Integer> entry : actualOrders.entrySet()){
                Dish dish = entry.getKey();
                int dishQty = entry.getValue();
                
                //getting new orderid
                ArrayList<Order> pastOrderList = OrderController.retrieveOrderList(vendorID);
                int lastOrderID = 0;
                for(Order order : pastOrderList){
                    int order_ID = order.getOrder_id();
                    if(order_ID > lastOrderID){
                        lastOrderID = order_ID;
                    }
                }
                lastOrderID++;
                order_id_list.add(lastOrderID);
                
                HashMap<Ingredient, ArrayList<String>> map = dish.getIngredientQuantity();
                for(Map.Entry<Ingredient, ArrayList<String>> entry2 : map.entrySet()){
                    Ingredient ingredient = entry2.getKey();
                    ArrayList<String> slist = entry2.getValue();
                    String quantity = slist.get(0);
                    String unit = slist.get(1);
                    int ingredient_supplier_id = ingredient.getSupplier_id();
                    String ingredientName = ingredient.getName();
                    double ingredient_price = Double.parseDouble(ingredient.getOfferedPrice());
                    double totalIngredientPrice = ingredient_price * dishQty;
                    //note: bufferpercentage not implemented yet
                    Orderline orderline = new Orderline(vendorID, lastOrderID, ingredient_supplier_id, ingredientName, totalIngredientPrice, dishQty, 0.0);
                    mixedList.add(orderline);
                }
            }
            ArrayList<Integer> list_of_supplier_id = new ArrayList<Integer>();
            for(Orderline ol : mixedList){
                int supplier_id = ol.getSupplier_id();
                if(!(list_of_supplier_id.contains(supplier_id))){
                    list_of_supplier_id.add(supplier_id);
                }
            }
            
            for(int this_supplier_id : list_of_supplier_id){
                ArrayList<Orderline> orderline_by_supplier = new ArrayList<Orderline>();
                int this_orderID = 0;
                for(Orderline orderline : mixedList){
                    int this_orderline_supplier_id = orderline.getSupplier_id();
                    if(this_supplier_id == this_orderline_supplier_id){
                        orderline_by_supplier.add(orderline);
                        this_orderID = orderline.getOrder_id();
                    }
                }
                Double total_order_price = 0.0;
                for(Orderline temp: orderline_by_supplier){
                    total_order_price += temp.getFinalprice();
                }
                Order order = new Order(this_orderID, vendorID, total_order_price, new Date(), orderline_by_supplier);
                OrderController.addOrder(order);
            }
        %>
        
        
        
    </body>
</html>
