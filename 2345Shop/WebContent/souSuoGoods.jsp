<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>商品查找</title>
		<link rel="stylesheet" type="text/css" href="css/goodChoice.css"/>
		<script src="css/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
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
		top: 50px;
	}
	#cgimg{
		width: 100px;
		height: 100px;
	}
	#page{
	
		width: 250px;
		height: 40px;
		margin: auto;
		position: relative;
		top:40px;'
			
	}
	</style>
	</head>

	<c:if test="${sessionScope.souSuoGoodsByPrice!=null}">
			
			${sessionScope.souSuoGoods=null}
		</c:if>  

	<body>
		<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
		<p class="a">
			<a href="#">全部商品</a><i>&nbsp;&nbsp;>&nbsp;&nbsp;</i>共
			<l>&nbsp;48724&nbsp;</l> 款相关商品</p>
		<div id="cat-list">
			<span class="cat-lit-title">类目 : </span>
			<ul class="clearfix">
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">居家日用 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">美食 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">母婴 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">美妆 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">女装 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">数码家电 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">文娱车品 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">内衣 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">家装家纺 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">鞋品 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">男装 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">配饰 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">户外运动 </a>
				</li>
				<li class="lem theme-hover-color-1 ">
					<a class="theme-a" href="#">箱包 </a>
				</li>
			</ul>
		</div>
		<div id="b">
			<div class="b aa">
				<a class="tag-fixed " href="#" style="color: red;">综合</a>
				<a class="tag-fixed " href="#">最新</a>
				<a class="tag-fixed active" href="#">销量</a>
				<a class="tag-fixed " href="#" d>价格</a>
			</div>
			<div class="fl aa">
				<form action="GoodChoiseServlet?action=souSuoByPrice" method="post" >
					<input name="s" type="hidden" value="sell">
					<input name="u" type="hidden" value="1211761">
					<input name="r" type="hidden" value="l">
					<input  type="text" class="min-price" name="minPrice" placeholder="￥" value="">
					<span>-</span>
					<input  type="text" class="max-price" name="maxPrice" placeholder="￥" value="">
					<input  type="submit" id="sortSeachSubmit" value="确认"/>
					
				</form>
			</div>
			<div id="san">
				<span>
						<&nbsp;&nbsp;<i>1</i>/487&nbsp;&nbsp;<il>></il>
					</span>
			</div>
		</div>
	
	
	<div id="c">
		<ul class="clearfix">
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="tqg"/><span><label for="tqg">淘抢购</label></span></a>
			</li>
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="qhs"/><span><label for="qhs">聚划算</label></span></a>
			</li>
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="jxpp"/><span><label for="jxpp">精选品牌</label></span></a>
			</li>
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="ht"/><span><label for="ht">海淘</label></span></a>
			</li>
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="jbmj"/><span><label for="jbmj">金牌卖家</label></span></a>
			</li>
			<li class="theme-hover-color-1 ">
				<a class="theme-a" href="#"><input type="checkbox" id="tm"/><label for="tm">天猫</label></span></a>
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#four ul li").mouseover(function(){
				$(this).addClass("bainkuang")
			}).mouseout(function(){
				$(this).removeClass("bainkuang")
			});
		});
	</script>
	
	<div id="four">
		<ul>
		
			<c:forEach var="cg" items="${sessionScope.souSuoGoods }">
				
				<li>
					<img src="upload/${cg.picpath}" class="img" id="cgimg"/>
					<a href="GoodChoiseServlet?id=${cg.id}&action=getByIdToGoodChoiceInfo"><img src="img/T.png"/>${cg.title}</a>
					<p><span>原价${cg.pririce}</span><span class="you">销量${cg.xiaoliang}万</span></p>
					<p style="height: 40px;"><span class="zuo">￥${cg.price}</span><span class="juan">&nbsp;${cg.youhui}元卷&nbsp;</span></p>
				</li>
				
				
			</c:forEach>
			<c:forEach var="cg" items="${sessionScope.souSuoGoodsByPrice }">
				
				<li>
					<img src="upload/${cg.picpath}" class="img" id="cgimg"/>
					<a href="GoodChoiseServlet?id=${cg.id}&action=getByIdToGoodChoiceInfo"><img src="img/T.png"/>${cg.title}</a>
					<p><span>原价${cg.pririce}</span><span class="you">销量${cg.xiaoliang}万</span></p>
					<p style="height: 40px;"><span class="zuo">￥${cg.price}</span><span class="juan">&nbsp;${cg.youhui}元卷&nbsp;</span></p>
				</li>
				
				
			</c:forEach>
			
		</ul>
		
	</div>
	
	<div id="five">
			<ul>
				<li><a href="#"><img src="img/shouc.png"/><p>收藏</p></a></li>
				<li><a href="#"><img src="img/fenxiang.png"/><p>分享</p></a></li>
				<li><a href="#"><img src="img/fangui.png"/><p>反馈</p></a></li>
				<li><a href="#"><img src="img/totop.png" /><p>顶部</p></a></li>
			</ul>
		</div>
		<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
	</body>
</html>