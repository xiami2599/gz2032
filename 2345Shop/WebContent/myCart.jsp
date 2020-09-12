<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>购物车</title>
    <style type="">
    	#top{
	margin: auto;
	position: relative;
	left:130px
}

	.fl{
			margin: auto;
			position: relative;
			left:390px
	}
	#gwc{
			font-size: 29px;
			font-family: serif;
	}
	#but{
		margin: auto;
		position: relative;
			left:150px
	}
	#center{
		position: relative;
		top:30px;
		height: 170px;
	}
	#left{
			float:left;
			width:50%
	}
	#right{
		float:left;
	}
	#buybtn{
			position: relative;
			left:85px;
	}
    </style>
    <link href="css/myCart.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="js/myCar.js"></script>
</head>
<body>
<c:if test="${sessionScope.cars==null}">
			<jsp:forward page="CarServlet?uid=${sessionScope.user.id }&action=getByUid11"></jsp:forward>
			sessionScope.cars==null
		</c:if>  
<div id="top" >
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
		 <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h2 class="fl"> <a title="1234购物" href="index.jsp"> <img src="images/logo.png"> </a>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<span id="gwc">购物车</span></h2>
      </div>
    </div>
  </div>
<div id="content">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">        
            <tr>
                <td class="title_1"><input id="allCheckBox" type="checkbox" value=""/>全选</td>
                <td class="title_2" colspan="2">店铺宝贝</td>
                <td class="title_3">获积分</td>
                <td class="title_4">单价（元）</td>
                <td class="title_5">数量</td>
                <td class="title_6">小计（元）</td>
                <td class="title_7">操作</td>
            </tr>
            <tr>
                <td colspan="8" class="line"></td>
            </tr>
            </br>
       <c:forEach var="car" items="${sessionScope.cars }">
           <!--  <tr>
                <td colspan="8" class="shopInfo">店铺：<a href="#">纤巧百媚时尚鞋坊</a>    卖家：<a href="#">纤巧百媚</a> <img src="images/taobao_relation.jpg" alt="relation" /></td>
            </tr> -->
            <tr id="product1">
                <td class="cart_td_1"><input name="cartCheckBox" type="checkbox" value="product1"/></td>
                <td class="cart_td_2" ><img src="upload/${car.picPath}" alt="shopping" id="carimg"/></td>
                <td class="cart_td_3"><a href="#">${car.carName}</a><br />
                  <br />
                    保障：<img src="images/taobao_icon_01.jpg" alt="icon" /></td>
                <td class="cart_td_4">5</td>
                <td class="cart_td_5">${car.price}</td>
                <td class="cart_td_6"><img src="images/taobao_minus.jpg" alt="minus" class="hand"/> <input id="num_1" type="text"  value="${car.count }" class="num_input" readonly="readonly"/> <img src="images/taobao_adding.jpg" alt="add" class="hand"/></td>
                <td class="cart_td_7"></td>
                <td class="cart_td_8"><a href="CarServlet?id=${car.id }&action=delete">删除</a></td>
            </tr>
		 </c:forEach>
           
    	</table>
    	
    	<div id="center" style="width: 100%">
    		<div id="left">
    			商品总价（不含运费）：<label id="total" class="yellow" name="totalPrice"></label> 元<br />
                   	 可获积分 <label class="yellow" id="integral"></label> 点<br />
    		</div>
    		<div id="right">
    			<form  action="OrderServlet?action=save"  method="post">
								收件人：<input type="text" name="clientName" id="" value="" />&nbsp;&nbsp;请填写收件人<br />
								地&nbsp;&nbsp;&nbsp;&nbsp;址：<input type="text" name="address" id="" value="" /><span>&nbsp;&nbsp;请填写地址</span><br />
								电&nbsp;&nbsp;&nbsp;&nbsp;话：<input type="text" name="phone"  />（区号-电话号码-分机）<br />
								<br />
						<input type="submit" value="                " style="background-image:url('images/taobao_subtn.jpg'); height: 27px" id="buybtn"/>
				</form> 
    		</div>
    	</div>
                   	 
</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>
</html>
