<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理</title>
<link href="css/register.css" rel="stylesheet">
<link rel="start icon" href="images/resize_png_new.png">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
<style type="text/css">
	#top{
		width: 1200px;
		height: 140px;
		margin: auto;
	}
	#but{
		width: 1200px;
		height: 159px;
		margin: auto;
	}
	#car{
		width: 800px;
		height: 500px;
		border:1px,solid,red;
		margin: auto;
		background-color: azure;
	}
	#car #manbtntop{
		 width:100%;
		 height:50px;
		/*  background-color: yellow; */
	}
	#car #manbtntop .btn{
		 margin-top:120px;
		 width:110px;
		 height:50px;
		float:left;
		/* border:1px solid red; */
		margin-left: 118px;
	}
	#car #manbtntop .btn a{
		text-decoration:none;
	   font-size:16px;
	   font-weight:bold;
	   color:white;
	   display: block;
	   width:99px;
	   height:49px;
	   text-align:center;
	   line-height:49px;
	   background-color: tomato;
	}
	#car #manbtntop .btn a:hover{
		background-color: crimson;
	}
	</style>
</head>
 
<body class="bg-gray">
<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
<div> 
  <!--top start-->
  <div class="tp for-register">
    <div class="tp_box clearfix">
    </div>
  </div>
  <!--top end--> 
  <!--hd start--> 
  <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h1 class="fl"> <a title="1234购物" href="index.jsp"> <img src="images/logo.png"> </a> </h1>
        <div class="welcome fl">后台管理</div>
      </div>
    </div>
  </div>

 <div id="car">
  
  <div id="manbtntop">
  	
  		<div class="btn">
  			<a href="managerUser.jsp">用户管理</a>
  		</div>
  	
  		<div class="btn">
  			<a href="managerGoodChoice.jsp">好货精选管理</a>
  		</div>
  	
  		<div class="btn">
  			<a href="managerFengQiang.jsp">疯抢榜管理</a>
  		</div>
  	
  		<div class="btn">
  			<a href="managerBaoYou.jsp">9.9包邮管理</a>
  		</div>
  	
  		<div class="btn">
  			<a href="managerDdQiang.jsp">咚咚抢管理</a>
  		</div>
  		<div class="btn">
  			<a href="managerOrder.jsp">订单管理</a>
  		</div>
  </div>
		 
 	
 </div>
 
</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>

<script src="js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>
