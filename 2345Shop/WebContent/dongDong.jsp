<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/dongDong.css"/>
		<style type="text/css">
		#top{
		width: 1200px;
		height: 140px;
		margin: auto;
		}
	#buttom{
		width: 1200px;
		height: 159px;
		margin: auto;
	}
	#center #page{
	
		width: 250px;
		height: 40px;
		margin: auto;
		position: relative;
		top:10px;'
			
	}
	</style>
	</head>
	<body>
		<c:if test="${sessionScope.ddqPage1==null}">
			<jsp:forward page="DongDongQiangServlet?action=ddqPage"></jsp:forward>
		</c:if> 
		
		<div class="con">
			<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
			<div id="center">
				<div id="first">
					<img src="img/11.jpg" width="1200px" height="159px" />
				</div>
				
				<div id="two">
					<ul>
						<li>
							<div id="ulli">
								<span>13:00</span>
								<p>已开抢</p>
							</div>
						</li>
						<li>
							<div id="ulli">
								<span>14:00</span>
								<p>已开抢</p>
							</div>
						</li>
						<li>
							<div id="ulli">
								<span>15:00</span>
								<p>已开抢</p>
							</div>
						</li>
						<li>
							<div id="ulli">
								<span>16:00</span>
								<p>已开抢</p>
							</div>
						</li>
						<li>
							<div id="ulli">
								<span>17:00</span>
								<p>已开抢</p>
							</div>
						</li>
						
					</ul>
				</div>
			<div id="three">
			
				<c:forEach var="ddq" items="${sessionScope.ddqPage1.lists }">
					
					<div id="three1">
						<ul>
							<li>
								<div id="pic" >
									<a href="DongDongQiangServlet?id=${ddq.id}&action=getByIdToDongInfo"><img src="upload/${ddq.picpath}"/></a>
								</div>
								
								<div id="inpic">
									<div id="onepic">
										<a href="DongDongQiangServlet?id=${ddq.id}&action=getByIdToDongInfo"><h3>${ddq.title}</h3></a>
										<p style="color: red;">${ddq.descri}</p>
									</div>
									<div id="twopic">
										<span id="jia">
											<b>
												<i style="float: left">＄</i>
												<p style="float: left;font-size: 20px;color: red;">${ddq.price}</p>
											</b>
											券后价
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ddq.pririce}
										</span>
										
									</div>
									<div id="threpic">
										
										<div id="threpic1">
											
										</div>
										<div id="threpic2"><a href="DongDongQiangServlet?id=${ddq.id}&action=getByIdToDongInfo">马上抢</a></div>
									</div>
								</div>
							</li>
						</ul>
				</div>
				</c:forEach>

			</div>
			
			<div  id="page">
				<h3>
					<a href="DongDongQiangServlet?ddqcurrentPage1=1&action=ddqPage">首页</a>
					<a href="DongDongQiangServlet?ddqcurrentPage1=${sessionScope.ddqPage1.currentPage-1<1?1:sessionScope.ddqPage1.currentPage-1}&action=ddqPage">上一页</a>
					<a href="DongDongQiangServlet?ddqcurrentPage1=${sessionScope.ddqPage1.currentPage+1>sessionScope.ddqPage1.totalPage?sessionScope.ddqPage1.totalPage:sessionScope.ddqPage1.currentPage+1}&action=ddqPage">下一页</a>
					<a href="DongDongQiangServlet?ddqcurrentPage1=${sessionScope.ddqPage1.totalPage}&action=ddqPage">尾页</a>
				</h3>
			</div>
			
			</div>
		
			<div id="buttom">
			<iframe src="footer.jsp"  width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
		</div>
	</body>
</html>
