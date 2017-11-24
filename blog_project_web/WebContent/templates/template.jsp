<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head class="html_boxed">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/favicon/favicon.ico">

    <title>Highland: Index</title>

    <!-- CSS Plugins -->
    <link href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" >

    <!-- CSS Global -->
    <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600italic,600,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

  </head>

  <body>

    <!-- NAVIGATION
    ================================================== -->

   <tiles:insertAttribute name="header" />

   <tiles:insertAttribute name="content" />

   <tiles:insertAttribute name="footer" />


    <!-- JavaScript
    ================================================== -->
    
    <!-- JS Global -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- JS Plugins -->
    <script src="${pageContext.request.contextPath}/assets/plugins/scrolltopcontrol/scrolltopcontrol.js"></script>
    
    <!-- JS Custom -->
    <script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>

  </body>
</html>