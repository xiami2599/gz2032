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
        <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a>&nbsp;>&nbsp;用户管理</div>
      </div>
    </div>
  </div>

 <div id="car">
 	
 		<table border="1" cellspacing="" cellpadding="" width="1000">
		<tr height="60px">
			<th width="50px">编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>性别</th>
			<th>出生日期</th>
			<th>地址</th>
			<th>手机号</th>
			<th>注册时间</th>
			<th>操作</th>
			
		</tr>
		
		<c:if test="${sessionScope.userPage==null}">
			<jsp:forward page="UserServlet?action=userPage"></jsp:forward>
		</c:if>
		
		<c:forEach var="userinfo" items="${sessionScope.userPage.lists }">
			<tr>
				<td>${userinfo.id}</td>
				<td> ${userinfo.loginName}</td>
				<td >${userinfo.password}</td>
				<td>${userinfo.sex}</td>
				<td>${userinfo.birthday}</td>
				<td>${userinfo.address}</td>
				<td>${userinfo.phone}</td>
				<td>${userinfo.registerTime}</td>
				
			
				<td width="80px">
					<a href="UserServlet?id=${userinfo.id}&currentPage=${sessionScope.userPage.currentPage}&action=delete" onclick=" return confirm('确定要删除该记录吗？')">删除</a></br>
					<a href="UserServlet?id=${userinfo.id}&action=getByIdManager">修改</a>
				</td>
			</tr>
		</c:forEach>
		<br>
		<tr height="50px">
			<td colspan="3">第${sessionScope.userPage.currentPage}页</td>
			<td colspan="3">共有${sessionScope.userPage.totalPage}页</td>
			
			<td colspan="3"> 总共有${sessionScope.userPage.count}条记录</td>
		
		</tr>
	</table>
	<h3>
		<a href="UserServlet?userCurrentPage=1&action=userPage">首页</a>
		<a href="UserServlet?userCurrentPage=${sessionScope.userPage.currentPage-1<1?1:sessionScope.userPage.currentPage-1}&action=userPage">上一页</a>
		<a href="UserServlet?userCurrentPage=${sessionScope.userPage.currentPage+1>sessionScope.userPage.totalPage?sessionScope.userPage.totalPage:sessionScope.userPage.currentPage+1}&action=userPage">下一页</a>
		<a href="UserServlet?userCurrentPage=${sessionScope.userPage.totalPage}&action=userPage">尾页</a>
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
