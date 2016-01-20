<%-- 
    Document   : RecipeBuilder
    Created on : Jan 18, 2016, 12:59:09 PM
    Author     : Benjamin
--%>

<%@page import="Controller.IngredientController"%>
<%@page import="Model.Dish"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.js"></script>
        <link rel="stylesheet" href="css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <title>Recipe Builder</title>
        <%
          ArrayList<Dish> dishList = IngredientController.getDish("1");
        %>
    </head>
    <body>
        <h1>Recipe Builder List</h1>
        <ul>
            <% for (Dish dish :dishList){%>
            <li><%=dish%></li>
            <%}%>
        </ul>
    </body>
</html>

