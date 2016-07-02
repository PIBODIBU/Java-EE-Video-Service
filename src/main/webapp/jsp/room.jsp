<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Room" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="utils.MDColors" %>
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
<%
Room room = (Room) request.getAttribute("room");
%>
</body>
</html>