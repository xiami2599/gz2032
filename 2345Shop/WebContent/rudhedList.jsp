<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>疯抢榜</title>
		<link rel="stylesheet" type="text/css" href="css/rudhedList.css"/>
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
	#fqinfo{
	
		width: 200px;
		height: 200px;
	}
	#page{
	
		width: 250px;
		height: 40px;
		margin: auto;
		position: relative;
		top:10px;'
			
	}
		</style>
	</head>
	<body>
	<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
	<div id="one">
		<a href="#" id="a"></a>
	</div>	
	<div id="two">
		<ul>
			<li><a href="#" style="display:block;width: 50px;text-align: center;color: white;  border-radius: 10px;background-color: red;">全部</a></li>
			<li><a href="#">居家日用</a></li>
			<li><a href="#">美食</a></li>
			<li><a href="#">母婴</a></li>
			<li><a href="#">美妆</a></li>
			<li><a href="#">女装</a></li>
			<li><a href="#">数码家电</a></li>
			<li><a href="#">文娱车品</a></li>
			<li><a href="#">内衣</a></li>
			<li><a href="#">家装家纺</a></li>
			<li><a href="#">鞋品</a></li>
			<li><a href="#">男装</a></li>
			<li><a href="#">配饰</a></li>
			<li><a href="#">户外运动</a></li>
			<li><a href="#">箱包</a></li>
		</ul>
	</div>	
	<div id="three">
		<ul>
			<li class="top1" style="position: relative;left: -37px;"><a href="#">
				<div class="top" style="background: url(img/top1.jpg);">  </div>
				<div class="zuo" style="background: url(img/T1.jpg) no-repeat; background-size: 200px;"></div>
				<div class="you">
					<p>【小熊尼奥】3D立体儿童启蒙早教认知卡</p>
					 <span class="num" style="background-color: antiquewhite;color: orangered;">近24小时疯抢 <span style="font-size: 18px;color: #fe2e54;">10.7万</span> 件</span>
					 <p><i style="font-size: 24px;color: orangered;">¥19.9</i>&nbsp;&nbsp;&nbsp;<i style="color: #A9A9A9;font-size: 12px;">天猫价179.9</i></p>
				</div>
				</a></li>
			<li class="top2" style="position: relative;left: -22px;"><a href="#">
				<div class="top" style="background: url(img/top2.jpg);">   </div>
				<div class="zuo" style="background: url(img/T2.jpg) no-repeat; background-size: 200px;"></div>
				<div class="you">
					<p>【免税！】recipe玥之秘水晶防晒喷雾</p>
					 <span class="num" style="background-color: lightcyan;color: orchid;">近24小时疯抢 <span style="font-size: 18px;color: orchid;">9.7万</span> 件</span>
					 <p><i style="font-size: 24px;color: orangered;">¥49.9</i>&nbsp;&nbsp;&nbsp;<i style="color: #A9A9A9;font-size: 12px;">天猫价89.9</i></p>
				</div>
				</a></li>
			<li class="top3" style="position: relative;left: -7px;"><a href="#">
				<div class="top" style="background: url(img/top3.jpg);">   </div>
				<div class="zuo" style="background: url(img/T3.jpg) no-repeat; background-size: 200px;"></div>
				<div class="you">
					<p>拍3盒19.9元 众知堂医用冷敷贴10贴</p>
					 <span class="num" style="background-color: antiquewhite;color: orangered;">近24小时疯抢 <span style="font-size: 18px;color: #fe2e54;">8.4万</span> 件</span>
					 <p><i style="font-size: 24px;color: orangered;">¥9.9</i>&nbsp;&nbsp;&nbsp;<i style="color: #A9A9A9;font-size: 12px;">天猫价89.9</i></p>
				</div>
				</a></li>
		</ul>
	</div>	
		<c:if test="${sessionScope.fqPage1==null}">
			<jsp:forward page="FengQiangServlet?action=fqPage"></jsp:forward>
		</c:if> 
		<div id="four">
		
		<ul>
				<%
				   int i=4;
				%>	
			<c:forEach var="fq" items="${sessionScope.fqPage1.lists }">
				
				<li>
					<a href="FengQiangServlet?id=${fq.id}&action=getByIdToInfo">
						<img src="upload/${fq.picpath}" id="fqinfo"/>
						<p class="beijin" style="font-size: 14px;color: white; ">&nbsp;&nbsp;定金￥20&nbsp;抵&nbsp;￥40</p>
						<p style="font-size: 13px;width: 215px;height: 16px; overflow: hidden;position: relative;top: -35px;">
							${fq.title}</p>
						<p class="yugou">${fq.descri} <span style="font-size: 18px;">${fq.xiaoliang}</span> 件</p>	
						<div class="num">
						<p class="daoshoujia">到手¥ <span style="font-size: 20px;">${fq.price}</span>
						<i style="color: #929292;text-decoration: line-through;">¥ ${fq.pririce}</i>	
						</p><span class="top"> <%= i++ %> </span>
						<img src="img/T.png" style="width: 25px;height: 25px;float: right;position: relative;left: -5px;top: -5px;"/></div>
					</a>
				</li>
				
			</c:forEach>		
				
			</ul>
			
		</div>
		<div  id="page">
				<h3>
					<a href="FengQiangServlet?fqcurrentPage1=1&action=fqPage">首页</a>
					<a href="FengQiangServlet?fqcurrentPage1=${sessionScope.fqPage1.currentPage-1<1?1:sessionScope.fqPage1.currentPage-1}&action=fqPage">上一页</a>
					<a href="FengQiangServlet?fqcurrentPage1=${sessionScope.fqPage1.currentPage+1>sessionScope.fqPage1.totalPage?sessionScope.fqPage1.totalPage:sessionScope.fqPage1.currentPage+1}&action=fqPage">下一页</a>
					<a href="FengQiangServlet?fqcurrentPage1=${sessionScope.fqPage1.totalPage}&action=fqPage">尾页</a>
				</h3>
			</div>
		<div id="five">
			<ul>
				<li><a href="#"><img src="img/shouc.png"/><p>收藏</p></a></li>
				<li class="zhankai"><a href="#"><img src="img/fenxiang.png"/><p>分享</p></a></li>
				<li><a href="#"><img src="img/fangui.png"/><p>反馈</p></a></li>
				<li><a href="#"><img src="img/totop.png" /><p>顶部</p></a></li>
			</ul>
		</div>
	<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
	</body>
</html>
