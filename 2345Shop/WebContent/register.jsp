<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>注册</title>
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
      <div class="top"> <span class="tp_loginInfo">您好，欢迎来到2345购物网！</span><a href="login_user.jsp" class="top-a">[登录]</a> </div>
    </div>
  </div>
  <!--top end--> 
  <!--hd start--> 
  <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h1 class="fl"> <a title="1234购物" href="index.jsp"> <img src="images/logo.png"> </a> </h1>
        <div class="welcome fl">欢迎注册</div>
      </div>
    </div>
  </div>
  <!--hd end-->
  <div class="have-reg w940"><span>我已经注册，现在就&nbsp;<a href="login_user.html" class="blue_link">登录</a></span></div>
  <!-- content start -->
  <div class="container w940">
    <div class="register">
    
    <form action="UserServlet?action=save" method="post" enctype="multipart/form-data">
      <div class="spsearch">
      </br>  
      	<c:if test="${sessionScope.registersuccess!=null }">
	   <p id="success">${sessionScope.registersuccess }</p>
	      ${sessionScope.registersuccess=null }
		</c:if> 
        <p>
	          <label><b class="star">*</b>&nbsp;用户名：</label>
	          <input class="spnormal" type="text" name="loginName">
	          <span  class="short">4-20位字符，支持英文、数字和符号的组合</span> 
          </p>
      </div>
      <div class="spsearch">
        <p>
          <label><b class="star">*</b>&nbsp;请设置密码：</label>
          <input class="spnormal" type="password" name="password">
          <span class="long">6-20位字符，支持英文、数字和符号的组合，不建议使用纯数字、纯字母或纯符号</span> </p>
      </div>
      <div class="spsearch">
        <p>
          <label><b class="star">*</b>&nbsp;性别：</label>
          <input class="" type="radio" name="sex" value="男">男
          <input class="" type="radio" name="sex" value="女">女
        </p>
      </div>
      <div class="spsearch">
        <p>
          <label><b class="star">*</b>&nbsp;出生日期：</label>
          <input class="spnormal" type="date" name="birthday">
        </p>
      </div>
      <div class="spsearch">
        <p>
          <label><b class="star">*</b>&nbsp;地址：</label>
          <input class="spnormal" type="text" name="address">
        </p>
      </div>
      <div class="spsearch">
        <p>
          <label><b class="star">*</b>&nbsp;手机号：</label>
          <input class="spnormal" type="text" name="phone">
        </p>
      </div>
      <div class="spsearch item">
        <p>
          <label>&nbsp;</label>
           <input type="submit" value=""/>
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
