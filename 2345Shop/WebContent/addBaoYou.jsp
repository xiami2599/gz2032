<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>添加9.9包邮</title>
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
		height: 500px;
		border:1px,solid,red;
		margin: auto;
	}
	.add{
		margin: auto;
		
		height: 500px;
		width:100%;
		background-color: whitesmoke;
		text-align: center;
		line-height: 55px;
	}
	table{
		margin: auto;
		position:relative;
		top:20px; 
		text-align: right;
	}
	table input{
		height: 30px;
		width:250px;
	}
	table .btn{
		height: 40px;
		width:100px;
		font-size: 17px;
	}
	h3{
		line-height: ;
		position: relative;
		top:20px;
		color: cadetblue;
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
        <div class="welcome fl">
        	<a href="mainManager.jsp" style="color:black;">管理首页&nbsp;></a>
        	<a href="managerBaoYou.jsp" style="color:black;">9.9包邮管理</a>&nbsp;>&nbsp;添加9.9包邮</div>
      </div>
    </div>
  </div>

 <div id="car">
 	
 	<div class="add">
 	
		<form  action="BaoYouServlet?action=save" enctype="multipart/form-data" method="post">
			
			<table border="0">
				<tr><td><label>上传图片：</label></td><td><input type="file" name="fileName" id="fileName" value="" width="50px"/></td></tr>
				<tr><td><label>商品标题：</td><td></label><input type="text" name="title"  value=""/></td></tr>
				<tr><td><label>分类：</label></td><td><input type="text" name="describe"  value=""/></td></tr>
				<tr><td><label>价格：</label></td><td><input type="text" name="price"  value=""/></td></tr>
				<tr><td><label>原价：</label></td><td><input type="text" name="prix"  value=""/></td></tr>
				<tr><td><label>优惠：</label></td><td><input type="text" name="discount"  value=""/></td></tr>
				<tr><td><label>销量：</label></td><td><input type="text" name="sales"  value="" style="width:202px;"/>(单位:万)</td></tr>
				
				<tr ><td colspan="2"><input type="submit" value="添加" class="btn"/></td></tr>
			
			</table>

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
