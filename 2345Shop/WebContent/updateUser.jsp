<%@page import="dao.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>修改用户信息</title>
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
	#registersuccess{
		position: relative;
		left:30px;
	}
	#success{
		position: relative;
		left:250px;
		top:-20px;
		color: red;
		font-size: 16px;
	}
	#success{
		position: relative;
		left:250px;
		top:-20px;
		color: red;
		font-size: 16px;
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
  
  </div>
  <!--top end--> 
  <!--hd start--> 
  <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h1 class="fl"> <a title="1234购物" href="index.jsp"> <img src="images/logo.png"> </a> </h1>
         <div class="welcome fl"><a href="mainManager.jsp" style="color:black;">管理首页</a><a href="managerUser.jsp" style="color:black;">&nbsp;>&nbsp;用户管理</a>&nbsp;>&nbsp;修改用户信息</div>
      </div>
    </div>
  </div>
  <!--hd end-->
  <!-- content start -->
  <div class="container w940">
    <div class="register">
    
    <form action="UserServlet?&action=updateMan" method="post" enctype="multipart/form-data">
		      <div class="spsearch">
		      	
		      </br>  
		      	<%
					//从session中得回huodong
					 User updateUser = (User)session.getAttribute("updateUser");
				%>
				<input type="hidden" name="id" value="${sessionScope.updateUser.id}"/>
		        <p>
			          <label><b class="star"></b>&nbsp;用户名：</label>
			          <input class="spnormal" type="text" name="loginName" value="${sessionScope.updateUser.loginName}"/>
		          </p>
		      </div>
		      <div class="spsearch">
		        <p>
		          <label><b class="star"></b>&nbsp;密码：</label>
		          <input class="spnormal" type="password" name="password" value="${sessionScope.updateUser.password}"/>
		      </div>
		      <div class="spsearch">
		        <p>
		          <label><b class="star"></b>&nbsp;性别：</label>
		          <input type="radio"name="sex" id="userSex" ${sessionScope.updateUser.sex eq "男"?"checked" :""} value="男"/>男
				<input type="radio"name="sex" id="userSex" ${sessionScope.updateUser.sex eq "女"?"checked" :""} value="女"/>女
		        </p>
		      </div>
		      <div class="spsearch">
		        <p>
		          <label><b class="star"></b>&nbsp;出生日期：</label>
		          <input class="spnormal" type="date" name="birthday" value="${sessionScope.updateUser.birthday}"/>
		        </p>
		      </div>
		      <div class="spsearch">
		        <p>
		          <label><b class="star"></b>&nbsp;地址：</label>
		          <input class="spnormal" type="text" name="address" value="${sessionScope.updateUser.address}"/>
		        </p>
		      </div>
		      <div class="spsearch">
		        <p>
		          <label><b class="star"></b>&nbsp;手机号：</label>
		          <input class="spnormal" type="text" name="phone"value="${sessionScope.updateUser.phone}"/>
		        </p>
		      </div>
		      <div class="spsearch item1">
		        <p>
		          <label>&nbsp;</label>
		           <input type="submit" value="" style="background-image:url('images/xiugai.png'); height: 40px; width: 250px;" />
		        </p>
		      </div>
     </form>
     
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
