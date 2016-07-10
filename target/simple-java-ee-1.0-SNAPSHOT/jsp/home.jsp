<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<body style="padding: 50px">


    <%
    HttpSession httpSession = request.getSession();
%>

<h5>Login</h5>

<form action="https://oauth.vk.com/authorize"
      style="float: left;"
      method="get">
    <input type="hidden" name="client_id" value="5084652">
    <input type="hidden" name="scope" value="friends,video,offline,email">
    <input type="hidden" name="redirect_uri" value="http://localhost:8080/login/vk">
    <input type="hidden" name="response_type" value="code">
    <input type="hidden" name="v" value="5.52">

    <button type="submit"
            class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
            <%=httpSession.getAttribute("email") != null ? "disabled" : ""%>>
        VK
    </button>
</form>

<form action="https://oauth.vk.com/authorize"
      style="float: left;"
      method="get">
    <input type="hidden" name="client_id" value="5084652">
    <input type="hidden" name="scope" value="friends,video,offline,email">
    <input type="hidden" name="redirect_uri" value="http://localhost:8080/login/vk">
    <input type="hidden" name="response_type" value="code">
    <input type="hidden" name="v" value="5.52">

    <button type="submit"
            class="<%=httpSession.getAttribute("email") == null ? "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect mdl-color-text--indigo" :
            "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"%>"
            <%=httpSession.getAttribute("email") != null ? "disabled" : ""%>
    >
        Facebook
    </button>
</form>

<form action="https://accounts.google.com/o/oauth2/v2/auth"
    method="get">
    <input type="hidden" name="client_id" value="506968348094-7u8kbg2q3v4jn5glpr1q4ti8jhnk597p.apps.googleusercontent.com">
    <input type="hidden" name="scope" value="email profile">
    <input type="hidden" name="redirect_uri" value="http://localhost:8080/login/googleplus">
    <input type="hidden" name="response_type" value="code">

    <button type="submit"
            class="<%=httpSession.getAttribute("email") == null ? "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect mdl-color-text--red-900" :
            "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"%>"
            <%=httpSession.getAttribute("email") != null ? "disabled" : ""%>
    >
        Google+
    </button>
</form>

<form action="${pageContext.request.contextPath}/logout"
    method="get">

    <button type="submit"
            class="<%=httpSession.getAttribute("email") != null ? "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect mdl-color-text--red" :
            "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"%>"
            <%=httpSession.getAttribute("email") == null ? "disabled" : ""%>
    >
        Logout
    </button>
</form>

<%
    if (session.getAttribute("name") != null && session.getAttribute("surname") != null) {
%>
<h6>
    Hi, <%=httpSession.getAttribute("name")%> <%=httpSession.getAttribute("surname")%>
</h6>
<%
    }
%>

</body>
</html>