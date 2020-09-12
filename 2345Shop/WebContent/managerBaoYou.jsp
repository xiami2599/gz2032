<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>9.9包邮管理</title>
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
		width: 800px;
		height: 600px;
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
			left:300px;
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
        <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a>&nbsp;>&nbsp;9.9包邮管理</div>
      </div>
    </div>
  </div>

 <div id="car">
 	
 	<table border="1" cellspacing="" cellpadding="" width="800">
		<tr height="60px">
			<th width="50px">编号</th>
			<th>图片</th>
			<th>商品标题</th>
			<th>分类</th>
			<th>价格</th>
			<th>原价</th>
			<th>优惠券</th>
			<th>销量</th>
			<th>操作</th>
			
		</tr>
		
		<c:if test="${sessionScope.managerbyPage==null}">
			<jsp:forward page="BaoYouServlet?action=pageManager"></jsp:forward>
		</c:if>
		
		<c:forEach var="baoyou" items="${sessionScope.managerbyPage.lists }">
			<tr>
				<td>${baoyou.id}</td>
				<td height="65px" width="85"> <img src="upload/${baoyou.picpath}" height="65px" width="85"/></td>
				<td width="160px">${baoyou.title}</td>
				<td>${baoyou.describe}</td>
				<td>￥${baoyou.price}</td>
				<td>￥${baoyou.prix}</td>
				<td>￥${baoyou.discount}</td>
				<td>￥${baoyou.sales}</td>
			
				<td width="80px">
					<a href="BaoYouServlet?id=${baoyou.id}&manBycurrentPage=${sessionScope.managerbyPage.currentPage}&action=delete&picpath=${baoyou.picpath}" onclick=" return confirm('确定要删除该记录吗？')">删除</a></br>
					<a href="BaoYouServlet?id=${baoyou.id}&action=getById">修改</a>
				</td>
			</tr>
		</c:forEach>
		<br>
		<tr height="50px">
			<td colspan="2">第${sessionScope.managerbyPage.currentPage}页</td>
			<td colspan="3">共有${sessionScope.managerbyPage.totalPage}页</td>
			
			<td colspan="3"> 总共有${sessionScope.managerbyPage.count}条记录</td>
			<h3><td ><a href="addBaoYou.jsp">添加9.9包邮商品</a></br></td>
		
		</tr>
	</table>
	<h3>
		<a href="BaoYouServlet?manBycurrentPage=1&action=pageManager">首页</a>
		<a href="BaoYouServlet?manBycurrentPage=${sessionScope.managerbyPage.currentPage-1<1?1:sessionScope.managerbyPage.currentPage-1}&action=pageManager">上一页</a>
		<a href="BaoYouServlet?manBycurrentPage=${sessionScope.managerbyPage.currentPage+1>sessionScope.managerbyPage.totalPage?sessionScope.managerbyPage.totalPage:sessionScope.managerbyPage.currentPage+1}&action=pageManager">下一页</a>
		<a href="BaoYouServlet?manBycurrentPage=${sessionScope.managerbyPage.totalPage}&action=pageManager">尾页</a>
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
