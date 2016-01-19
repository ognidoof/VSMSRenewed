<%-- 
    Document   : OrderPage
    Created on : 19 Jan, 2016, 1:36:08 PM
    Author     : Joel
--%>

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
            
            ArrayList<Order> orderList = OrderDAO.retrieveOrderList(vendorID);
            //display list of all orders
            
            for (Order o: orderList){
                int orderID = o.getOrder_id();
                double totalPrice = o.getTotal_final_price();
                ArrayList<Orderline> orderlines = o.getOrderlines();
                %>
                <a>OrderID: <%=orderID%></a>
                <a>Total Order Price: <%=totalPrice%></a>
                <%
                for(Orderline ol : orderlines){
                    int supplier_id = ol.getSupplier_id();
                    Supplier supMan = UserDAO.getSupplierById(supplier_id);
                    String supName = supMan.getSupplier_name();
                    
                    String ing_name = ol.getIngredient_name();
                    int quantity = ol.getQuantity();
                    
                    %>
                        <a>Supplier: <%=supName%></a>
                        <a>Ingredient: <%=ing_name%></a>
                        <a>Quantity: <%=quantity%></a>
                    <%
                }
            }
        %>
    </body>
</html>
