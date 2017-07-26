<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>audio.js</title>
    <script>var _gaq=[['_setAccount','UA-20257902-1'],['_trackPageview']];(function(d,t){ var g=d.createElement(t),s=d.getElementsByTagName(t)[0]; g.async=1;g.src='//www.google-analytics.com/ga.js';s.parentNode.insertBefore(g,s)}(document,'script'))</script>
    <script src="<%=application.getContextPath()%>/resources/js/audio.min.js"></script>
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/index.css" media="screen">
   
    <script>
      audiojs.events.ready(function() {
        audiojs.createAll();
      });
    </script>
  </head>
  <body>
    <header>
      <h1>audio.js</h1>
    </header>

    <audio src="<%=application.getContextPath()%>/resources/media/video.mp4" preload="auto"></audio>

    
  </body>
</html>
