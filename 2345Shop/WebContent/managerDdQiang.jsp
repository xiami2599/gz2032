<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>咚咚抢管理</title>
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
        <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a>&nbsp;>&nbsp;咚咚抢管理</div>
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
			<th>操作</th>
			
		</tr>
		
		<c:if test="${sessionScope.managerddqPage==null}">
			<jsp:forward page="DongDongQiangServlet?action=pageManager"></jsp:forward>
		</c:if>
		
		<c:forEach var="ddqiang" items="${sessionScope.managerddqPage.lists }">
			<tr>
				<td>${ddqiang.id}</td>
				<td height="65px" width="85"> <img src="upload/${ddqiang.picpath}" height="65px" width="85"/></td>
				<td width="160px">${ddqiang.title}</td>
				<td>${ddqiang.descri}</td>
				<td>￥${ddqiang.price}</td>
				<td>￥${ddqiang.pririce}</td>
			
				<td width="80px">
					<a href="DongDongQiangServlet?id=${ddqiang.id}&manDDQcurrentPage=${sessionScope.managerddqPage.currentPage}&action=delete&picpath=${ddqiang.picpath}" onclick=" return confirm('确定要删除该记录吗？')">删除</a></br>
					<a href="DongDongQiangServlet?id=${ddqiang.id}&action=getById">修改</a>
				</td>
			</tr>
		</c:forEach>
		<br>
		<tr height="50px">
			<td colspan="3">第${sessionScope.managerddqPage.currentPage}页</td>
			<td colspan="1">共有${sessionScope.managerddqPage.totalPage}页</td>
			
			<td colspan="2"> 总共有${sessionScope.managerddqPage.count}条记录</td>
			<h3><td ><a href="addDongDongQiang.jsp">添加咚咚抢商品</a></br></td>
		
		</tr>
	</table>
	<h3>
		<a href="DongDongQiangServlet?manDDQcurrentPage=1&action=pageManager">首页</a>
		<a href="DongDongQiangServlet?manDDQcurrentPage=${sessionScope.managerddqPage.currentPage-1<1?1:sessionScope.managerddqPage.currentPage-1}&action=pageManager">上一页</a>
		<a href="DongDongQiangServlet?manDDQcurrentPage=${sessionScope.managerddqPage.currentPage+1>sessionScope.managerddqPage.totalPage?sessionScope.managerddqPage.totalPage:sessionScope.managerddqPage.currentPage+1}&action=pageManager">下一页</a>
		<a href="DongDongQiangServlet?manDDQcurrentPage=${sessionScope.managerddqPage.totalPage}&action=pageManager">尾页</a>
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
