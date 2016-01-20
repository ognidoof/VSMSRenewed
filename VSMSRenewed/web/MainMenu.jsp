<%-- 
    Document   : MainMenu
    Created on : Jan 18, 2016, 1:03:48 PM
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
        <title>Main Menu</title>
    </head>
    <body>
        <div class="ui segment" style="left:10%;width:80%;">
            <p>
            <div class="ui attached stackable menu">
                <div class="ui container">
                    <a class="item">
                        <i class="home icon"></i> Home
                    </a>
                    <a class="item">
                        <i class="grid layout icon"></i> Browse
                    </a>
                    <a class="item">
                        <i class="mail icon"></i> Messages
                    </a>
                    <div class="ui simple dropdown item">
                        More
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <a class="item"><i class="edit icon"></i> Edit Profile</a>
                            <a class="item"><i class="globe icon"></i> Contact</a>
                            <a class="item"><i class="settings icon"></i> Account Settings</a>
                        </div>
                    </div>
                    <div class="right item">
                        <div class="ui input"><input type="text" placeholder="Search..."></div>
                    </div>
                </div>
               
            </div>
            </p>
               <p>   
                   
                   
                   <div class="ui breadcrumb">
  <a class="section">Home</a>
  <i class="right angle icon divider"></i>
  <a class="section">Manage Menu</a>
  <i class="right angle icon divider"></i>
  <div class="active section">Recipe Buider</div>
</div>
            <div class="ui raised very padded text container">
                
                
                
  <h2 class="ui header">VSMS Menu</h2>
  <p></p>
  <p></p>
</div>
      
            
            
            </p>
      
            
            <div class="ui left rail">
                <div class="ui">
                   <%--
                   content
                   --%>
                </div>
            </div>
            <div class="ui right rail">
                <div class="ui">
                    <%--
                   content
                   --%>
                </div>
            </div>
            <p></p>
            <p></p>
        </div>
    </body>
</html>
