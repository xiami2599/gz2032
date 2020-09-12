<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>9块9包邮</title>
	</head>
	<link rel="stylesheet" type="text/css" href="css/9.9BaoYou.css"/>
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
		position: relative;
		top:60px;
	}
	#page{
	
		width: 250px;
		height: 40px;
		margin: auto;
		position: relative;
		top:10px;'
			
	}
	</style>
	<body>
	<c:if test="${sessionScope.byPage==null}">
			<jsp:forward page="BaoYouServlet?action=byPage"></jsp:forward>
		</c:if> 
		<div class="con">
			<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
	
		<div class="first">
			<div id="one">
				<img src="img/1.jpg"/>
			</div>
			<div id="two">
				<div id="h3">
					<h3 id="h3">
					<img src="img/2.jpg"/>
					<span id=spa"">近一小时疯抢</span>
				</h3>
				</div>
				
				<div id="twoOne">
					
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_4.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄3.9</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">屈臣氏一次性纸巾</a>
						</div>
					</div>
					
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_5.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄1.9</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">现发现货（网红饼干）</a>
						</div>
					</div>
					
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_6.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄9.9</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">去眼袋护眼霜</a>
						</div>
					</div>
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_7.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄0.9</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">手工披萨（广西特产）</a>
						</div>
					</div>
					
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_8.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄9.9</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">强力无痕迹</a>
						</div>
					</div>
					
					<div id="twoOne1">
						<div id="pic">
							<img src="img/2345_image_file_copy_9.jpg"/>
						</div>
						<div id="jiaGe">
							<span id="jia">＄3.8</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;疯抢<span id="feng">32424</span>件
						</div>
						<div id="text">
							<a href="#">木浆抽纸（40包）</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="second">
			<div id="one">
				<span id="one1">|</span>&nbsp;&nbsp;<span id="one2">9.9包邮精选</span>
			</div><hr />
			
			<div id="two">
				<a href="#">精选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">宝妈必囤</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">鲜果时蔬</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">学习无忧</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">居家百货</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">美食</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">服饰</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">配饰</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">美装</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">内衣</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#">箱包</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div><hr />
			
			
			
			<c:forEach var="by" items="${sessionScope.byPage.lists }">
				<a href= "BaoYouServlet?id=${by.id}&action=getByIdToInfo">
				<div id="three">
				<ul>
					<li>
						<div id="three1">
							<img src="upload/${by.picpath}"/>
							<div id="three2">
								${by.title}
							</div>
							<div id="three3">
								<span id="">原价&nbsp;${by.prix}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
								销量${by.sales}万
							</div>
							<div id="three4">
								<span id="jia">＄${by.price}</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;<span id="quan">${by.discount}元券</span>
							</div>
						</div>
						
					</li>
					
				</ul>
			</div>
			</a>	
		</c:forEach>	
				
		</div>
			
	</div>
		<div  id="page">
				
					<a href="BaoYouServlet?bycurrentPage=1&action=byPage">首页</a>
					<a href="BaoYouServlet?bycurrentPage=${sessionScope.byPage.currentPage-1<1?1:sessionScope.byPage.currentPage-1}&action=byPage">上一页</a>
					<a href="BaoYouServlet?bycurrentPage=${sessionScope.byPage.currentPage+1>sessionScope.byPage.totalPage?sessionScope.byPage.totalPage:sessionScope.byPage.currentPage+1}&action=byPage">下一页</a>
					<a href="BaoYouServlet?bycurrentPage=${sessionScope.byPage.totalPage}&action=byPage">尾页</a>
				
			</div>
		<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
	</body>
</html>
