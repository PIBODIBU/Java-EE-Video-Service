<%--<%@ page import="java.util.Random" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--<meta charset="utf-8">--%>
<%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>

<%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
<%--<link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.teal-red.min.css"/>--%>
<%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">--%>
<%--<script src="https://storage.googleapis.com/code.getmdl.io/1.1.3/material.min.js"></script>--%>

<%--<title>Player</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<!-- Always shows a header, even in smaller screens. -->--%>
<%--<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">--%>
<%--<header class="mdl-layout__header">--%>
<%--<div class="mdl-layout__header-row">--%>
<%--<!-- Title -->--%>
<%--<span class="mdl-layout-title">Roman Svyatnenko</span>--%>
<%--<!-- Add spacer, to align navigation to the right -->--%>
<%--<div class="mdl-layout-spacer"></div>--%>
<%--<!-- Navigation. We hide it in small screens. -->--%>
<%--<nav class="mdl-navigation mdl-layout--large-screen-only">--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/">Home</a>--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/apps">My apps</a>--%>
<%--<a class="mdl-navigation__link" href="http://github.com">Github</a>--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/about">About</a>--%>
<%--</nav>--%>
<%--</div>--%>
<%--</header>--%>
<%--<div class="mdl-layout__drawer">--%>
<%--<div id="drawer_card" class="mdl-card mdl-shadow--4dp mdl-layout-title">--%>
<%--<h3></h3>--%>
<%--</div>--%>

<%--<nav class="mdl-navigation">--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/">Home</a>--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/apps">My apps</a>--%>
<%--<a class="mdl-navigation__link" href="http://github.com">Github</a>--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/contact">Contact</a>--%>
<%--<a class="mdl-navigation__link" href="http://0.0.0.0/about">About</a>--%>
<%--</nav>--%>
<%--</div>--%>
<%--<main class="mdl-layout__content">--%>
<%--<div class="page-content" align="center">--%>
<%--<!-- Your content goes here -->--%>
<%----%>
<%--</div>--%>
<%--</main>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Custom HTML5 Video Player</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

<style>
    #video_container {
        max-width: 100%;
        max-height: 80%;
    }
</style>

<div id="video_container">
    <video poster="http://media.w3.org/2010/05/sintel/poster.png" preload="none" controls="" id="video" tabindex="0">
        <source type="video/mp4"
                src="http://dfs-dtln-19.dfs.ivi.ru/mp4-hd720/6Fs28bB1CQa-Tb3j4RCP8A,1467402000/f64.vcp.digitalaccess.ru/contents/3/8/14521f2a28393aa6880c3617597408.mp4?rand=0.9085534857586026&redirected=1&sessionID=997FD488jD3F2j481AjB45Cj328754739171jWC6j34810743324374416.4608"
                id="mp4"></source>
        <p>Your user agent does not support the HTML5 Video element.</p>
    </video>
</div>

<div>Current Time : <span id="currentTime">0</span></div>
<div>Total time : <span id="totalTime">0</span></div>

<script>
    $(function () {
        $('#currentTime').html($('#video_container').find('video').get(0).load());
        $('#currentTime').html($('#video_container').find('video').get(0).play());
    });

    setInterval(function () {
        $('#currentTime').html($('#video_container').find('video').get(0).currentTime);
        $('#totalTime').html($('#video_container').find('video').get(0).duration);
    }, 500)
</script>

</body>
</html>