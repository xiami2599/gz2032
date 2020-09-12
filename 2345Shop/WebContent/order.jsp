<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单</title>
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
	
    </style>
    <link href="css/myCart.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
    <!-- <script type="text/javascript" src="js/myCar.js"></script> -->
</head>
<body>
<c:if test="${sessionScope.orderPage==null}">
			<jsp:forward page="OrderServlet?action=page"></jsp:forward>
			
		</c:if>  
<div id="top" >
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
		 <div class="login-wrap">
    <div class="w940">
      <div class="header clearfix">
        <h2 class="fl"> <a title="1234购物" href="index.jsp"> <img src="images/logo.png"> </a>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<span id="gwc">我的订单</span></h2>
      </div>
    </div>
  </div>
<div id="content">
	<form action="" method="post" name="myform">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">        
            <tr>
                <td class="title_1">商品编号</td>
                <td class="title_2" colspan="2">商品名称</td>
                <td class="title_3">获得积分</td>
                <td class="title_4">单价（元）</td>
                <td class="title_5">数量</td>
                <td class="title_6">状态</td>
                <td class="title_7">操作</td>
            </tr>
            <tr>
                <td colspan="8" class="line"></td>
            </tr>
            </br>
       <c:forEach var="order" items="${sessionScope.orderPage.lists }">
           <!--  <tr>
                <td colspan="8" class="shopInfo">店铺：<a href="#">纤巧百媚时尚鞋坊</a>    卖家：<a href="#">纤巧百媚</a> <img src="images/taobao_relation.jpg" alt="relation" /></td>
            </tr> -->
            <tr id="product1">
                <td class="cart_td_1">${order.id}</td>
                <td class="cart_td_2" ><img src="upload/${order.picPath}" alt="shopping" id="carimg"/></td>
                <td class="cart_td_3"><a href="#">${order.orderName}</a><br />
                  <br />
              </td>
                <td class="cart_td_4">5</td>
                <td class="cart_td_5">${order.price}</td>
                <td class="cart_td_6"> <input id="num_1" type="text"  value="${order.count }" class="num_input" readonly="readonly"/></td>
                <td class="cart_td_7">买家已付款</td>
                <td class="cart_td_8"><a href="OrderServlet?id=${order.id }&action=delete">删除</a></td>
            </tr>
          
		 </c:forEach>
		
		 <tr id="product1">
                <td colspan="2" class="cart_td_7" style="font-size: 12px; color: black;">收货人：</td>
                <td colspan="" class="cart_td_6">${sessionScope. clientName} </td>
                <td class="cart_td_7" colspan="2" style="font-size: 12px; color: black;"> 手机号：</td>
                <td colspan="3" class="cart_td_8">${sessionScope. phone}</td>
            </tr>
		 <tr id="product1">
                <td colspan="2" class="cart_td_7" style="font-size: 12px; color: black;"> 收货地址：</td>
                <td class="cart_td_6">${sessionScope. address}</td>
                <td colspan="2" class="cart_td_7" style="font-size: 12px; color: black;"> 订单日期：</td>
                <td colspan="3" class="cart_td_8">${sessionScope.orderTime}</td>
            </tr>
		
		
            <tr>
                <td  colspan="3" style="font-size: 15px">
                </td>
                <td colspan="5" class="shopend"><h3>订单总价： <span style="color:red;">￥${sessionScope.totalPrice}</span></h3><br />
                   
            </tr> 
                    
    	</table>
    </form>

</div>
<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
</body>
</html>
