<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>商品详情</title>
<link href="css/register.css" rel="stylesheet">
<link rel="start icon" href="images/resize_png_new.png">
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
	
	#info{
				margin: auto;
				width: 900px;
				height: 350px;
				/*background-color: yellowgreen;*/
			}
			#left{
				width: 32%;
				height: 100%;
				/*background-color: red;*/
				float: left;
			}
			#infodatu{
				width:280px;
				height: 280px;
				position: relative;
				top: 30px;
				
			}
			#right{
				
				width: 67%;
				height: 100%;
				/*background-color: yellow;*/
				float: left;
			}
			table{
				position: relative;
				left:40px;
				margin-top: 40px;
				font-size: 16px
			}
			#quan{
				color: red;
				width: 60px;
				height: 30px;
				border:  1px solid red;
			}
			#price{
				
				color: red;
				font-size: 26px;
			}
			#btm{
				position: relative;
				left: 40px;
			}
			#buy{
				font-size: 18px;
				text-align: center;
				line-height: 40px;
				text-decoration: none;
				display: block;
				width: 120px;
				height: 40px;
				color: white;
				background-color: orangered;
			}
			#buy:hover{
				background-color:coral ;
			}

			#prix{
				
				text-decoration: line-through;
			}
			p{
				font-size: 20px;
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
        <h1 class="fl"> <a title="1234购物" href="index.html"> <img src="images/logo.png"> </a> </h1>
        <div class="welcome fl">商品详情</div>
      </div>
    </div>
  </div>
 
 <div id="info" class="">
			
			<div id="left">
				<img src="upload/${fqInfo.picpath}" id="infodatu"/>
				
			</div>
			
			<div id="right">
				<table border="0" width="550px" height="170px">
					
					<tr><td colspan="2"><p>${sessionScope.fqInfo.title}</p></td></tr>
					<tr><td>${fqInfo.descri}</td><td>累计销量：${fqInfo.xiaoliang }万</td></tr>
					<tr><td>独家优惠：<span id="quan">${fqInfo.youhui}元券</span></td><td></td></tr>
				</table>
				<hr />
				
				<div id="btm"> 
					<span id="price">￥${fqInfo.price}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原价：<span id="prix">${fqInfo.pririce}</span>
					
					<a href="CarServlet?carName=${sessionScope.fqInfo.title }&picPath=${fqInfo.picpath }&price=${fqInfo.price }&count=${1 }&TotalPrice=${fqInfo.price }&uid=${user.id }&action=save" id="buy">
					加入购物车</a>
				</div>
			</div>
		</div>
 
</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>
<script type="text/javascript" src="script/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="script/js.js"></script>
</html>
