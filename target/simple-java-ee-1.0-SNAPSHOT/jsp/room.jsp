<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Room" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="utils.MDColors" %>
<%@ page import="java.net.ServerSocket" %>
<%@ page import="java.net.Socket" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Custom HTML5 Video Player</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue-purple.min.css"/>
    <link
            href='http://fonts.googleapis.com/css?family=Roboto:400,400italic,500,500italic,700,700italic'
            rel='stylesheet' type='text/css'>
    <script src="https://storage.googleapis.com/code.getmdl.io/1.1.3/material.min.js"></script>
</head>
<body>

<style>
    html body {
        width: 100%;
        height: 100%;
    }

    form {
        width: 80%;
        margin: 0 auto;
    }

    #drawer_card {
        min-height: 20%;
        max-height: 20%;
        width: 100%;
        background: #2196F3;
        color: white;
        border-radius: 0;
    }

    .mdl-layout__drawer {
        border-right: 0;
    }

    .mdl-textfield {
        width: 100%;
    }

    .mdl-grid {
        width: 80%;
    }

    .mdl-cell {
        width: 30%;
    }

    .block_title {
        margin-left: 11%;
        margin-top: 48px;
    }
</style>

<%
    Room room = (Room) request.getAttribute("room");
%>

<div class="mdl-grid">

    <%-- Room info --%>
    <div class="mdl-cell mdl-cell--4-col">
        <div class="card-square mdl-card mdl-shadow--8dp">
            <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
                 style="
                            height: 100px;
                            width: 100%;
                            color: #fff;
                            background-color: <%=room.getColor()%>">
                <h2 class="mdl-card__title-text"><%=room.getName()%>
                </h2>
            </div>

            <div class="mdl-card__supporting-text">
                <h5>Info: </h5>

                <h6>
                    Name: <%=room.getName()%>
                    <br/>
                    Description: <%=room.getDescription()%>
                    <br/>
                    UID: <%=room.getUID()%>
                    <br/>
                    value_time: <%=room.getValue_time()%>
                </h6>
            </div>
        </div>
    </div>

    <%--ServerSocket info--%>
    <div class="mdl-cell mdl-cell--4-col">
        <div class="card-square mdl-card mdl-shadow--8dp">
            <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
                 style="
                            height: 100px;
                            width: 100%;
                            color: #fff;
                            background-color: #2196F3">
                <h2 class="mdl-card__title-text">New message</h2>
            </div>

            <div class="mdl-card__supporting-text">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="sample3">
                    <label class="mdl-textfield__label" for="sample3">Text...</label>
                </div>

                <button class="mdl-button mdl-js-button mdl-button--primary" id="submit">Send</button>
                <button class="mdl-button mdl-js-button mdl-button--primary" id="close">Close</button>

            </div>
        </div>
    </div>

    <%-- Grid end --%>
</div>

</body>
</html>