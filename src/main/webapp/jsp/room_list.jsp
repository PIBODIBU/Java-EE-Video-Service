<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Room" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="utils.MDColors" %>
<%@ page import="utils.Logger" %>
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

    .mdl-card {
        width: 100%;
        height: auto;
    }

    .mdl-textfield {
        width: 100%;
    }

    .mdl-grid {
        width: 80%;
        max-width: 100%;
    }

    .mdl-cell {
        width: 30%;
    }

    .block_title {
        margin-left: 11%;
        margin-top: 48px;
    }
</style>

<!-- Always shows a header, even in smaller screens. -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">Top twitter</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation mdl-layout--large-screen-only">
                <%--<a class="mdl-navigation__link" href="http://0.0.0.0/">Politics</a>--%>
                <%--<a class="mdl-navigation__link" href="http://0.0.0.0/apps">IT</a>--%>
                <%--<a class="mdl-navigation__link" href="http://github.com">Memes</a>--%>
                <%--<a class="mdl-navigation__link" href="http://0.0.0.0/about">Other</a>--%>
            </nav>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <div id="drawer_card" class="mdl-card mdl-shadow--4dp mdl-layout-title">
            <h3></h3>
        </div>

        <nav class="mdl-navigation">
            <%--<a class="mdl-navigation__link" href="http://0.0.0.0/apps">Politics</a>--%>
            <%--<a class="mdl-navigation__link" href="http://github.com">IT</a>--%>
            <%--<a class="mdl-navigation__link" href="http://0.0.0.0/contact">Memes</a>--%>
            <%--<a class="mdl-navigation__link" href="http://0.0.0.0/about">Other</a>--%>
        </nav>
    </div>
    <main class="mdl-layout__content">
        <div class="page-content">
            <%-- Content area start --%>

            <div class="mdl-typography--display-1 block_title">
                Rooms
            </div>

            <div class="mdl-grid">
                <%
                    LinkedList<Room> rooms = (LinkedList<Room>) request.getAttribute("rooms");
                    for (Room room : rooms) {
                %>
                <div class="mdl-cell mdl-cell--1-col">
                    <div class="card-square mdl-card mdl-shadow--8dp">
                        <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
                             style="
                                     max-height: 100px;
                                     height: 100px;
                                     width: 100%;
                                     color: #fff;
                                     background-color: <%=room.getColor()%>;
                                     ">
                            <h2 class="mdl-card__title-text"><%=room.getName()%>
                            </h2>
                        </div>

                        <div class="mdl-card__supporting-text">
                            <h6><%=room.getDescription()%>
                            </h6>

                            <h5>Owner: </h5>
                            <h6>
                                <%=room.getOwner().getName()%> <%=room.getOwner().getSurName()%>
                            </h6>
                        </div>

                        <div class="mdl-card__actions mdl-card--border">
                            <form action="${pageContext.request.contextPath}/rooms/enter" method="get">
                                <input type="hidden" name="room_uid" value="<%=room.getUID()%>">
                                <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                        type="submit">
                                    Enter
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

                <%-- Grid end --%>
            </div>

            <div class="mdl-typography--display-1 block_title">
                Actions
            </div>

            <div class="mdl-grid">

                <div class="mdl-cell mdl-cell--1-col">
                    <div class="card-square mdl-card mdl-shadow--8dp">
                        <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
                             style="
                            height: 100px;
                            width: 100%;
                            color: #fff;
                            background-color: #2196F3;">
                            <h2 class="mdl-card__title-text">New room</h2>
                        </div>

                        <div class="mdl-card__supporting-text">
                            <form action="${pageContext.request.contextPath}/rooms/my" method="post">
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                    <input class="mdl-textfield__input" type="text" id="field_name" name="room_name">
                                    <label class="mdl-textfield__label" for="field_name">Name...</label>
                                </div>
                                <br/>

                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                    <input class="mdl-textfield__input" type="text" id="field_description"
                                           name="room_description">
                                    <label class="mdl-textfield__label" for="field_description">Description...</label>
                                </div>
                                <br/>

                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                    <input class="mdl-textfield__input" type="text" id="field_owner" name="room_owner">
                                    <label class="mdl-textfield__label" for="field_owner">Owner UID...</label>
                                </div>
                                <br/>

                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                    <input class="mdl-textfield__input" type="text" id="field_color" name="room_color">
                                    <label class="mdl-textfield__label" for="field_color">Color...</label>
                                </div>
                                <br/>

                                <input type="hidden" name="action" value="create_room">
                                <input type="hidden" name="user_uid" value="<%=request.getParameter("uid")%>">

                                <button class="mdl-button mdl-js-button mdl-button--primary" style="float: right;"
                                        type="submit">
                                    Create new room
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="mdl-cell mdl-cell--1-col">
                    <div class="card-square mdl-card mdl-shadow--8dp">
                        <div class="mdl-card__title mdl-card--expand mdl-shadow--2dp"
                             style="
                            max-height: 100px;
                            height: 100px;
                            width: 100%;
                            color: #fff;
                            background-color: #F44336;">
                            <h2 class="mdl-card__title-text">Delete all rooms</h2>
                        </div>

                        <div class="mdl-card__supporting-text">
                            <h5>Warning! This action cannot be undone.</h5>

                            <form action="${pageContext.request.contextPath}/rooms" method="post">
                                <button class="mdl-button mdl-js-button mdl-button--primary"
                                        style="float: right; color: #F44336;"
                                        type="submit">
                                    Delete
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

                <%-- Grid end --%>
            </div>

            <%-- Content area end --%>
        </div>
    </main>
</div>

</body>
</html>