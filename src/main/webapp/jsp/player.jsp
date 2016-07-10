<!DOCTYPE html>

<html lang="en">
<head>
    <title>player</title>
    <script type="text/javascript" src="//cdn.jsdelivr.net/afterglow/latest/afterglow.min.js">

    </script>

</head>
<body>

<video class="afterglow" id="myvideo" width="1280" height="720">
    <source type="video/mp4" src="https://afterglowplayer.com/sandbox/video/MP4/afterglow_local_hd.mp4" />
</video>
<script type="text/javascript">



    document.addEventListener("DOMContentLoaded", function(event) {
        var cpElement = document.getElementById("currentTime");
        var myPlayer = afterglow.getPlayer('myvideo');

        window.setInterval(function(){
            cpElement.innerHTML = myPlayer.currentTime();
        },1000);
    });


</script>

<div>
    <p>Current time:</p>
    <p id="currentTime"></p>
</div>
</body>
</html>