<%--<%@ page import="com.sun.xml.internal.fastinfoset.util.CharArray" %>--%>
<%--<%@ page import="java.util.*" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<html--%>
<%--xmlns:h="http://java.sun.com/jsf/html">--%>
<%--<head>--%>
<%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
<%--<link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.indigo-pink.min.css">--%>
<%--<script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>--%>

<%--<title>Java EE</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<style>--%>
<%--body html {--%>
<%--width: 100%;--%>
<%--height: 100%;--%>
<%--}--%>

<%--.mdl-data-table {--%>
<%--margin: 36px auto;--%>
<%--max-width: 60%;--%>
<%--}--%>
<%--</style>--%>

<%--<table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--4dp">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th class="mdl-data-table__cell--non-numeric">Attribute</th>--%>
<%--<th class="mdl-data-table__cell--non-numeric">Value</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<tr>--%>
<%--<td class="mdl-data-table__cell--non-numeric">User ID</td>--%>
<%--<td class="mdl-data-table__cell--non-numeric"><%= session.getAttribute("userId") %>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="mdl-data-table__cell--non-numeric">Visit count</td>--%>
<%--<td class="mdl-data-table__cell--non-numeric"><%= session.getAttribute("visitCount") %>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="mdl-data-table__cell--non-numeric">Last Access time</td>--%>
<%--<td class="mdl-data-table__cell--non-numeric"><%= session.getAttribute("lastAccess") %>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</tbody>--%>
<%--</table>--%>

<%--<form action="${pageContext.request.contextPath}/simple" method="post">--%>
<%--<input type="hidden" name="action" value="invalidate_session">--%>
<%--<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit">--%>
<%--Invalidate session--%>
<%--</button>--%>
<%--</form>--%>

<%--<h:form>--%>
<%--<h:outputLabel for="username" value="Username"/>--%>
<%--<h:inputText id="username" value="#{auth.username}" required="true"/>--%>
<%--<h:message for="username"/>--%>
<%--<br/>--%>
<%--<h:outputLabel for="password" value="Password"/>--%>
<%--<h:inputSecret id="password" value="#{auth.password}" required="true"/>--%>
<%--<h:message for="password"/>--%>
<%--<br/>--%>
<%--<h:commandButton value="Login" action="#{auth.login}"/>--%>
<%--<h:messages globalOnly="true"/>--%>
<%--</h:form>--%>

<%--</body>--%>
<%--</html>--%>

Welcome!

<div class="jumbotron" id="welcome">

    <h1>Welcome to the Stormpath Webapp Sample Application!</h1>

    <p class="lead">
        <br/>
        <br/>
        Welcome to this <i>gloriously simple</i>
        <a href="https://docs.stormpath.com/java/servlet-plugin/">Stormpath Java Webapp</a> sample application!
    <ul>
        <li>First, take a look through this very basic site.</li>
        <li>Then, check out this project's source code
            <a href="https://github.com/stormpath/stormpath-sdk-java/examples/servlet">on GitHub</a>.
        </li>
        <li>Lastly, integrate Stormpath into your own sites!</li>
    </ul>
    </p>

    <br/>
    <br/>

    <h2>What This Sample App Demonstrates</h2>

    <br/>
    <br/>

    <p>This simple application demonstrates how easy it is to register, login, and securely authenticate
        users on your website using the Stormpath Servlet Plugin.</p>

    <p>Not a Stormpath user yet? <a href="https://stormpath.com">Go signup now!</a></p>

    <br/>
    <br/>

    <p class="bigbutton"><a class="bigbutton btn btn-lg btn-danger" href="${pageContext.request.contextPath}/register"
                            role="button">Register</a></p>
</div>