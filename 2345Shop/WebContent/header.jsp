<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		
		<style type="text/css">
		
			#userlogin{
				
				width: 272px;
				height: 30px;
				position: relative;
				top:15px;
				left:-20px;
				font-size:14px;
				font-family: 宋体;
				
			}
			#topimg{
				width: 180px;
				height: 20px;
				position: relative;
				left:60px;
				top:40px
				
			}
			#userlogin a{
			
				color:black;
			}
			#userlogin a:hover{
				color:red;
			}
		</style>
	</head>
	<link rel="stylesheet" type="text/css" href="css/header.css"/>
	<body>
	
		<div id="header">
			
			<div class="">
				<div class="left f_l"></div>
				<div class="center f_l">
					<form action="GoodChoiseServlet?action=souSuo&${souSuoGoodsByPrice=null }" method="post" target="top">
						<input type="text" class="ssk f_l" name="souSuoName" placeholder="请输入查找内容"/>
						<input type="submit" class="ssan f_l"  value="搜索"/>
						
						
					</form>
				</div>
				<div class="right f_l" >
					<c:if test="${sessionScope.user.loginName!=null}">
				
						<div id="userlogin">
								${sessionScope.user.loginName },欢迎来到2345购物！<a href="userinfo.jsp" target="_top">修改个人信息</a>
						</div>
					</c:if> 
					<!-- <img src="img/headgrup.png" id="topimg"/> -->
				</div>
			</div>
			<div class="li_list f_l">
				<ul>
					<a href="index.jsp" target="_top"><li>首页</li></a>
					<a href="goodChoice.jsp" target="_top"><li>好货精选</li></a>
					<a href="rudhedList.jsp" target="_top"><li>疯抢榜</li></a>
					<a href="9.9BaoYou.jsp" target="_top"><li>9.9包邮</li></a>
					<a href="dongDong.jsp" target="_top"><li>咚咚抢</li></a>
					<a href="login_user.jsp" target="_top"><li>登录</li></a>
					<a href="register.jsp" target="_top"><li>注册</li></a>
					<a href="myCart.jsp" target="_top"><li>购物车</li></a>
			</div>
		</div>
	</body>
</html>
