<%-- 
    Document   : RecipeBuilder
    Created on : Jan 18, 2016, 12:59:09 PM
    Author     : Benjamin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.js"></script>
        <link rel="stylesheet" href="css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
            <script>
            function myFunction() {
                $('.ui.basic.modal')
                        .modal('show')
                        ;
            }
            
        </script>
        <title>Recipe Builder</title>
    </head>
    <body>
       
  <div class="ui huge header">Huge Header</div>
        <h1>Recipe Builder</h1>


        <div class="ui page grid">

 <div class="ui grid container">
        <div class="ui button" onclick="myFunction()">Add Ingredient</div>
        </div>
        
        
        <div class="ui basic modal" >
            <i class="close icon"></i>
            <div class="header">
                Archive Old Messages
            </div>
            <div class="image content">
                <div class="image">
                    <i class="archive icon"></i>
                </div>
                <div class="description">
                    <p>Your inbox is getting full, would you like us to enable automatic archiving of old messages?</p>
                </div>
            </div>
            <div class="actions">
                <div class="two fluid ui inverted buttons">
                    <div class="ui red basic inverted button">
                        <i class="remove icon"></i>
                        No
                    </div>
                    <div class="ui green basic inverted button">
                        <i class="checkmark icon"></i>
                        Yes
                    </div>
                </div>
                </div>
            </div>
        </div>
    </body>
</html>

