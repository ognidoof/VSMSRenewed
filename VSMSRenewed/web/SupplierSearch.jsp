<%-- 
    Document   : index
    Created on : Jan 13, 2016, 5:41:13 PM
    Author     : TC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="javascript.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Auto-Completion using AJAX</title>
    </head>
    <body onload="init()">

        <h1>Auto-Completion using AJAX</h1>
        <p>This example shows how you can do real time auto-completion using Asynchronous
            JavaScript and XML (Ajax) interactions.</p>

        <p>In the form below enter a name. Possible names that will be completed are displayed
            below the form. For example, try typing in "Bach," "Mozart," or "Stravinsky,"
            then click on one of the selections to see composer details.</p>
        <form name="autofillform" action="autocomplete"><table border="0" cellpadding="5">
                
                <tbody>
                    <tr>
                        <td><strong>Supplier Name:</strong></td>
                        <td>
                            <input type="text"
                                   size="40"
                                   id="complete-field"
                                   onkeyup="doCompletion();">
                        </td>
                    </tr>
                    <tr>
                        <td id="auto-row" colspan="2">
                            <table id="complete-table" class="popupBox"></table>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>