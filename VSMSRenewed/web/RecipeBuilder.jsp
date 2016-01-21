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
        <title>Recipe Builder</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $(document).ready(function() {
                //Need to add this form to add and refresh the table browser.
                $("ingredientservlet").ajaxForm(function() {
                    $('#modaldiv').modal('show');
                });
                $('.order-button').click(function() {
                    $('#modaldiv').modal('show');
                });
            });

        </script>
        <!--CSS-->
        <!-- Import CDN for semantic UI -->    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.js"></script>
        <!--for general CSS please refer to the main css. For others, please just append the link line below-->
        <link rel="stylesheet" type="text/css" href="css/main.css">

    </head>
    <body>
        <h1>Recipe Builder List</h1>
        <ul id="dishListAdded">
        </ul>

        <!--MODAL DIV-->
        <button type="submit" name="submit" class="ui teal button order-button">Order now</button>

        <div id="modaldiv" class="ui small modal">
            <i class="close icon"></i>
            <div class="header">
                Add Ingredients
            </div>
            <div class="content">
                <form id="addIngredient" action="ingredientservlet" method="get"> 
                    Supplier ID: <input type="text" name="supplier_id" /> 
                    Ingredient Name : <input type="text" name="name"/>
                    Unit (g,kg,etc): <input type="text" name="supplyUnit" />
                    Sub category: <input type="text" name="subcategory" />
                    Description: <input type="text" name="description" />
                    Price offered: <input type="text" name="offeredPrice" />
                    <input type="submit" value="Add" class="ui teal button add-ingredient-button" /> 
                </form>
            </div>
            <div class="actions">
                <div class="ui positive right labeled icon button">
                    <a class="text-white" href="<?php echo site_url('home/order');?>">Back to Home</a>
                    <i class="checkmark icon"></i>
                </div>
            </div>
        </div>


        <!--JAVASCRIPT-->
        <!--for general Javascript please refer to the main js. For others, please just append the script line below-->
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>

