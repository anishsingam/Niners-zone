<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
<script src="js/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/skel-layers.min.js"></script>
<script src="js/init.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/additional-methods.min.js"></script>
<script src="js/jquery.countdown.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
<script src="js/flipclock.js"></script>
<link rel="stylesheet" href="css/flipclock.css">		
<link rel="stylesheet" href="css/skel.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style-desktop.css" />
<link rel="stylesheet" href="css/application.css" />
<link rel="stylesheet" href="css/jquery.datetimepicker.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
</head>
<div class="header"></div>

<!-- Wrapper -->
<body class="left-sidebar">
<div id="wrapper">

	<!-- Content -->
	<div id="content">
		<form action = "<%=response.encodeURL("LogoutServlet")%>" method ="post">
									<input class="logout_button" type ="submit" value ="logout"/></form>
		<div class="inner">

	


	<!-- Copyright -->

	
	