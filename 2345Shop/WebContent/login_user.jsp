<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>登录</title>
<link href="css/register.css" rel="stylesheet" />
<link rel="start icon" href="images/resize_png_new.png" >
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
	#errot{
		width: 200px;
		height: 30px;
		color:red;
		font-size: 13px;
		
	}
	</style>
</head>

<body>
	<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
	</div>
<div class="bg-bule"> 
  <!--hd start-->
  <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h1 class="fl"> <a title="2345购物网" href="index.jsp"> <img src="images/logo.png"> </a> </h1>
        <div class="welcome fl">欢迎登录</div>
      </div>
    </div>
  </div>
  <!--hd end-->
  <div class="login-box w940 clearfix">
    <div class="pic fl"> <img src="images/page.jpg" title="上2345购物网，带你边看边买"> </div>
    <div class="fr entry">
      <h2 class="title">会员登录</h2>
      
      	<c:if test="${sessionScope.errot==null}">
	      <div id="errot">${sessionScope.errot }</div>
	      ${sessionScope.errot=null }
	</c:if> 
      
      
      <dl class="entry-name">
      
      <form action="UserServlet?action=login" method="post">
      
      	 <dt>
          <label>账号</label>
        </dt>
        <dd>
          <input type="text" class="text" name="loginName" placeholder="手机号/用户名">
        </dd>
      </dl>
      <dl>
        <dt>
          <label for="password">密码</label>
        </dt>
        <dd>
          <input type="password" name="password" class="text">
        </dd>
      </dl>
      <div class="item">
	      <input type="submit" value=""/>
      </div>
      </form>

      <div class="forget-psd mb15"> <a class="fl" href="index.jsp">2345购物直通车</a><a href="register.html" class="fr">免费注册</a> </div>
    </div>
  </div>
</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>
</html>

