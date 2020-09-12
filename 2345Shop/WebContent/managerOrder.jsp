<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户管理</title>
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
	#car{
		width:1200px;
		height: 400px;
		border:1px,solid,red;
		margin: auto;
	}
			table{
			margin: auto;
			text-align: center;
			border-color: lightcyan;
			background-color: whitesmoke ;
		}
		h3{
			position: relative;
			top:30px;
			left:500px;
			color: cadetblue;
		}
		a{
			text-decoration: none;
			color: black;
		}
		a:hover {
			color: #419641;
			text-decoration: underline;
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
        <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a>&nbsp;>&nbsp;订单管理</div>
      </div>
    </div>
  </div>

 <div id="car">
 	
 		<table border="1" cellspacing="" cellpadding="" width="1000">
		<tr height="60px">
			<th width="50px">编号</th>
			<th>图片</th>
			<th>商品名称</th>
			<th>价格</th>
			<th>数量</th>
			<th>收货人</th>
			<th>收货地址</th>
			<th>手机号</th>
			<th>订单日期</th>
			<th>操作</th>
			
		</tr>
		
		<c:if test="${sessionScope.orderManPage==null}">
			<jsp:forward page="OrderServlet?action=pageManager"></jsp:forward>
		</c:if>
		
		<c:forEach var="orderman" items="${sessionScope.orderManPage.lists }">
			<tr>
				<td>${orderman.id}</td>
				<td height="65px" width="85"> <img src="upload/${orderman.picPath}" height="65px" width="85"/></td>
				<td >${orderman.orderName}</td>
				<td>${orderman.price}</td>
				<td>${orderman.count}</td>
				<td>${orderman.clientName}</td>
				<td>${orderman.address}</td>
				<td>${orderman.phone}</td>
				<td>${orderman.orderTime}</td>
				
			
				<td width="80px">
					<a href="OrderServlet?id=${orderman.id}&orderMancurrentPage=${sessionScope.orderManPage.currentPage}&action=deleteMan" onclick=" return confirm('确定要删除该记录吗？')">删除</a></br>
				</td>
			</tr>
		</c:forEach>
		<br>
		<tr height="50px">
			<td colspan="3">第${sessionScope.orderManPage.currentPage}页</td>
			<td colspan="3">共有${sessionScope.orderManPage.totalPage}页</td>
			
			<td colspan="3"> 总共有${sessionScope.orderManPage.count}条记录</td>
		
		</tr>
	</table>
	<h3>
		<a href="OrderServlet?orderMancurrentPage=1&action=pageManager">首页</a>
		<a href="OrderServlet?orderMancurrentPage=${sessionScope.orderManPage.currentPage-1<1?1:sessionScope.orderManPage.currentPage-1}&action=pageManager">上一页</a>
		<a href="OrderServlet?orderMancurrentPage=${sessionScope.orderManPage.currentPage+1>sessionScope.orderManPage.totalPage?sessionScope.orderManPage.totalPage:sessionScope.orderManPage.currentPage+1}&action=pageManager">下一页</a>
		<a href="OrderServlet?orderMancurrentPage=${sessionScope.orderManPage.totalPage}&action=pageManager">尾页</a>
	</h3>
 	
 </div>
 
</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>
<script type="text/javascript" src="script/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="script/js.js"></script>
</html>
