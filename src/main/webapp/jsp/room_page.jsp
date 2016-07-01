<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Room" %>
<%@ page import="java.util.LinkedList" %>
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

<div class="mdl-grid">
    <%
        LinkedList<Room> rooms = (LinkedList<Room>) request.getAttribute("rooms");
        for (Room room : rooms) {
    %>
    <div class="card-square mdl-card mdl-shadow--8dp mdl-cell mdl-cell--4-col">
        <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
             style="
                     height: 100px;
                     width: 100%;
                     color: #fff;
                     background-color: <%=room.getColor()%>;">
            <h2 class="mdl-card__title-text"><%=room.getName()%>
            </h2>
        </div>

        <div class="mdl-card__supporting-text">
            <h6><%=room.getDescription()%>
            </h6>
            <h6><%=room.getOwnerUID()%>
            </h6>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>