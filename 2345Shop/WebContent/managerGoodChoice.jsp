<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>好货精选管理</title>
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
        <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a>&nbsp;>&nbsp;好货精选管理</div>
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
			<th>优惠</th>
			<th>销量</th>
			<th>操作</th>
			
		</tr>
		
		<c:if test="${sessionScope.managerGCPage==null}">
			<jsp:forward page="GoodChoiseServlet?action=pageCgManager"></jsp:forward>
		</c:if>
		
		<c:forEach var="gchoise" items="${sessionScope.managerGCPage.lists }">
			<tr>
				<td>${gchoise.id}</td>
				<td height="65px" width="85"> <img src="upload/${gchoise.picpath}" height="65px" width="85"/></td>
				<td width="160px">${gchoise.title}</td>
				<td>${gchoise.descri}</td>
				<td>￥${gchoise.price}</td>
				<td>￥${gchoise.pririce}</td>
				<td>￥${gchoise.youhui}</td>
				<td>${gchoise.xiaoliang}万</td>
			
				<td width="80px">
					<a href="GoodChoiseServlet?id=${gchoise.id}&cgcurrentPage2=${sessionScope.managerGCPage.currentPage}&action=delete&picpath=${gchoise.picpath}" onclick=" return confirm('确定要删除该记录吗？')">删除</a></br>
					<a href="GoodChoiseServlet?id=${gchoise.id}&action=getById">修改</a>
				</td>
			</tr>
		</c:forEach>
		<br>
		<tr height="50px">
			<td colspan="3">第${sessionScope.managerGCPage.currentPage}页</td>
			<td colspan="3">共有${sessionScope.managerGCPage.totalPage}页</td>
			
			<td colspan="2"> 总共有${sessionScope.managerGCPage.count}条记录</td>
			<h3><td ><a href="addGoodChoise.jsp">添加好货精选商品</a></br></td>
		
		</tr>
	</table>
	<h3>
		<a href="GoodChoiseServlet?managerCurrentPage=1&action=pageCgManager">首页</a>
		<a href="GoodChoiseServlet?managerCurrentPage=${sessionScope.managerGCPage.currentPage-1<1?1:sessionScope.managerGCPage.currentPage-1}&action=pageCgManager">上一页</a>
		<a href="GoodChoiseServlet?managerCurrentPage=${sessionScope.managerGCPage.currentPage+1>sessionScope.managerGCPage.totalPage?sessionScope.managerGCPage.totalPage:sessionScope.managerGCPage.currentPage+1}&action=pageCgManager">下一页</a>
		<a href="GoodChoiseServlet?managerCurrentPage=${sessionScope.managerGCPage.totalPage}&action=pageCgManager">尾页</a>
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
