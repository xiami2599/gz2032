<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>2345购物</title>
	</head>
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
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
	#huanyihuan{
		position: relative;
		left:500px;
		font-size: 19px;
	}
	</style>
	<script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var i = 1;
		$().ready(function(){
			$(".img_list").click(function(){
				//alert($("<div></div>").append($(this).clone()).html());
				i = parseInt($(this).html());
				$(".tp_index").css({
					backgroundImage:"url(img/lunbo_0"+i+".jpg)",
					backbackgroundRepeat:"no-repeat"
				});
			});
			window.setInterval("imgs()",3000);
		});
		function imgs(){
			i = i<4 ? i+1 : 1;
			$(".tp_index").css({
				backgroundImage:"url(img/lunbo_0"+i+".jpg)",
				backbackgroundRepeat:"no-repeat"
			});
		}
		
	</script>
	<body>
	<c:if test="${sessionScope.ddqPage==null}">
			<jsp:forward page="DongDongQiangServlet?action=page"></jsp:forward>
	</c:if> 
	<c:if test="${sessionScope.fqPage==null}">
			<jsp:forward page="FengQiangServlet?action=page"></jsp:forward>
	</c:if> 
	<c:if test="${sessionScope.cgPage==null}">
			<jsp:forward page="GoodChoiseServlet?action=page"></jsp:forward>
	</c:if> 
		
		<div id="top">
				<iframe src="header.jsp" width="1200px" height="159px" frameborder="0" scrolling="no" ></iframe>
			</div>
			
		<div id="shouye">
			<div class="top">
				<div class="top_left f_l">
					<dl>
						<dt><img src="img/CNVG_01.png"/>&nbsp;&nbsp;服饰鞋包</dt>
						<dd>
							<a href="#">男装</a>
							<a href="#">内衣</a>
							<a href="#">连衣裙</a>
							<a href="#">配饰</a>
							<a href="#">鞋品</a>
							<a href="#">箱包</a>
							<a href="#">卫衣</a>
						</dd>
						<dt><img src="img/CNVG_02.png"/>&nbsp;&nbsp;美食</dt>
						<dd>
							<a href="#">休闲零食</a>
							<a href="#">元气早餐</a>
							<a href="#">坚果</a>
							<a href="#">糖果</a>
							<a href="#">巧克力</a>
							<a href="#">方便速食</a>
						</dd>
						<dt><img src="img/CNVG_03.png"/>&nbsp;&nbsp;美妆</dt>
						<dd>
							<a href="#">唇妆</a>
							<a href="#">面膜</a>
							<a href="#">底妆</a>
							<a href="#">基础护肤</a>
							<a href="#">四季防晒</a>
							<a href="#">眼妆</a>
							<a href="#">洗面奶</a>
							      
						</dd>
						<dt><img src="img/CNVG_04.png"/>&nbsp;&nbsp;居家生活</dt>
						<dd>
							<a href="#">母婴日用品</a>
							<a href="#">厨房用品</a>
							<a href="#">纸巾</a>
							<a href="#">家装家纺</a>
							<a href="#">户外运动</a>
						</dd>
						<dt><img src="img/CNVG_05.png"/>&nbsp;&nbsp;数码办公</dt>
						<dd>
							<a href="#">数码家电</a>
							<a href="#">文娱车品</a>
							<a href="#">耳机</a>
						</dd>
					</dl>
				</div>
				<div class="top_zj f_l">
					<div class="tp_index">
						<ul>
							<li class="img_list f_l">1</li>
							<li class="img_list f_l">2</li>
							<li class="img_list f_l">3</li>
							<li class="img_list f_l">4</li>
							<li class="img_list f_l">5</li>
						</ul>
					</div>
					<div class="zj_img">
						<img src="img/cms_01.png" />
						<img src="img/cms_02.jpg" />
						<img src="img/cms_03.jpg" />
					</div>
				</div>
				<div class="top_right f_l">
					<div class="right_top">
						<img src="img/myys.jpg" />
					</div>
					<div class="right_center">
						<p>
							<i></i>
							<span>优惠头条</span>
						</p>
						<ul>
							<li>
								<i>拍下半价</i>
								<a href="#">小迷糊自然鲜颜素颜霜懒人裸妆</a>
							</li>
							<li>
								<i>第二件0元</i>
								<a href="#">远山桂圆干</a>
							</li>
							<li>
								<i>买二送一</i>
								<a href="#">狼王墙体除霉剂</a>
							</li>
						</ul>
					</div>
					<div class="right_bottom">
						<img src="img/nwtqg.png"/>
					</div>
				</div>
			</div>
			<div class="center">
				<div class="lb">
					<span>
						小编说
						<img src="img/lb.png" />
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;亲爱的,每日高额优惠券等你抢!海量优惠券免费领取,2345购物让网购更省钱! 
				</div>
				<div class="center_bottom">
					<div class="ddq_fkb f_l">
						<div class="ddq f_l">
							<div class="ddq_top">
								<i></i>
								<span class="span_ddq b_l">咚咚抢</span>
								<span>
									距本场结束
									<i>08</i>
									<i>17</i>
									<i>50</i>
								</span>
								<span class="span_a">
									<a href="DongDongQiangServlet?currentPage=${sessionScope.ddqPage.currentPage+1>sessionScope.ddqPage.totalPage?1:sessionScope.ddqPage.currentPage+1}&action=page">换一换</a>
									<a href="DongDongQiangServlet?currentPage=${sessionScope.ddqPage.currentPage+1>sessionScope.ddqPage.totalPage?1:sessionScope.ddqPage.currentPage+1}&action=page">查看更多</a>
								</span>
							</div>
							
							<c:forEach var="ddq" items="${sessionScope.ddqPage.lists }">
								<a class="ddq_a f_l" href="DongDongQiangServlet?id=${ddq.id}&action=getByIdToDongInfo">
									<img src="upload/${ddq.picpath}"/>
									<div class="ms f_l">
										<p>${ddq.title }</p>
										<div class="yh">
											<span>${ddq.descri }</span>
										</div>
										<div class="jg">
											￥
											<span>${ddq.price }</span>
											<b>${ddq.pririce }</b>
										</div>
									</div>
								</a>
							</c:forEach>
							
							
						</div>
						<div class="fqb f_l">
							<div class="fqb_top">
								<span class="span_fqb f_l b_l">疯抢榜</span>
								<div class="f_l">
									<span class="act">实时</span>
									<span>全天</span>
								</div>
								<span class="span_a">
									<a href="#">查看更多</a>
								</span>
							</div>
							
							<!-- 疯抢榜 -->
							
							<div class="list_top">
								<a class="top_1 f_l" href="#">
									<img class="f_l" src="img/top_1.png"/>
									<span  class="f_l"><img src="img/fq1.jpg"/></span>
									<div class="content f_l">
										<p>秒杀价9.9元！南极人蚊香液3液+1器</p>
										<span>近2小时疯抢<span class="num">1.1万</span>件</span>
										<div>
											<span class="price">￥<i>9.9</i></span>
											<span class="quan"><i>10</i>元券</span>
										</div>
									</div>
								</a>
								<a class="top_2 f_l" href="#">
									<img class="f_l" src="img/top_2.png"/>
									<span class="f_l"><img src="img/fq2.jpg"/></span>
									<div class="content f_l">
										<p>【云南三七】植物牙膏105g*2支</p>
										<span>近2小时疯抢<span class="num">1万</span>件</span>
										<div>
											<span class="price">￥<i>9.9</i></span>
											<span class="quan"><i>20</i>元券</span>
										</div>
									</div>
								</a>
							</div>
							
						 <div class="list_bt">
							<%-- <c:forEach var="fq" items="${sessionScope.fqPage.lists }">
								
							
								<a class="list f_l" href="productinfo.jsp">
									<div class="goods_img">
										<span class="rank_num"><i>5</i></span>
										<a href=""><img src="upload/${fq.picpath}"/></a>
									</div>
									<p>${fq.title}</p>
									<span class="dec">${fq.descri}<span class="num">${fq.xiaoliang}</span>件</span>
									<div>
										<span class="price">￥<i>${fq.price}</i></span>
										<span class="quan"><i>${fq.youhui}</i>元券</span>
									</div>
								</a>
							</c:forEach> --%>
						
							
								<a class="list f_l" href="#">
									<div class="goods_img">
										<span class="rank_num"><i>3</i></span>
										<img src="img/bx.jpg" />
									</div>
									<p>20支可擦笔芯+可擦笔2支+摩擦棒2个</p>
									<span class="dec">近2小时疯抢<span class="num">6635</span>件</span>
									<div>
										<span class="price">￥<i>4.9</i></span>
										<span class="quan"><i>5</i>元券</span>
									</div>
								</a>
								
								
								<a class="list f_l" href="#">
									<div class="goods_img">
										<span class="rank_num"><i>4</i></span>
										<img src="img/cw.jpg" />
									</div>
									<p>日系浅口隐形防滑船袜5双</p>
									<span class="dec">近2小时疯抢<span class="num">5968</span>件</span>
									<div>
										<span class="price">￥<i>3.9</i></span>
										<span class="quan"><i>3</i>元券</span>
									</div>
								</a>
								<a class="list f_l" href="#">
									<div class="goods_img">
										<span class="rank_num"><i>5</i></span>
										<img src="img/xlj.jpg" />
									</div>
									<p>【现货现发】加厚50片珍珠纹纯棉洗脸巾</p>
									<span class="dec">近2小时疯抢<span class="num">5852</span>件</span>
									<div>
										<span class="price">￥<i>5.1</i></span>
										<span class="quan"><i>3</i>元券</span>
									</div>
								</a>
								<a class="list f_l" href="#">
									<div class="goods_img">
										<span class="rank_num"><i>3</i></span>
										<img src="img/yszt.jpg" />
									</div>
									<p>【仁和药业】正宗老北京养生足贴50贴</p>
									<span class="dec">近2小时疯抢<span class="num">5631</span>件</span>
									<div>
										<span class="price">￥<i>9.9</i></span>
										<span class="quan"><i>60</i>元券</span>
									</div>
								</a>
								<a class="list f_l" href="#">
									<div class="goods_img">
										<span class="rank_num"><i>7</i></span>
										<img src="img/ckt.jpg" />
									</div>
									<p>【海氏海诺】防磨脚创可贴100片</p>
									<span class="dec">近2小时疯抢<span class="num">5345</span>件</span>
									<div>
										<span class="price">￥<i>5.9</i></span>
										<span class="quan"><i>5</i>元券</span>
									</div>
								</a> 
							</div> 
						</div>
					</div>
					<div class="center_right f_l">
						<p>
							<i></i>
							<span class="b_l">今日大牌</span>
						</p>
						<img class="top_img" src="img/bly_big.jpg" />
						<img class="bt_img" src="img/bly_s.jpg" />
						<div class="desc">
							<p>呵护美人肌-珀莱雅</p>
							<span>买一送一</span>
						</div>
						<div class="list">
							<a href="#">
								<img src="img/bly.jpg" />
								<div class="xinxi">
									<p>4瓶泊莱雅喷雾保湿补水</p>
									<div class="price">
										<span>￥<span>25.00</span></span>
										<b>￥35</b>
									</div>
								</div>
							</a> 
						</div>
					</div>
				</div>
			</div>
			<div class="bottom">
				<div class="bottom_top">
					<p class="f_l">
						<span class="b_l">好货精选</span>
						<span class="dec">实时更新 独享优惠券</span>
					</p>
					<a href="GoodChoiseServlet?cgcurrentPage=${sessionScope.cgPage.currentPage+1>sessionScope.cgPage.totalPage?1:sessionScope.cgPage.currentPage+1}&action=page" id="huanyihuan">换一换</a>
					<span class="sq f_r">今日已为用户省钱<b>126,563,391</b>元</span>
				</div>
				<div class="bottom_center">
				
					 <c:forEach var="cg" items="${sessionScope.cgPage.lists }">
								
						<a class="f_l" href="GoodChoiseServlet?id=${cg.id}&action=getByIdToGoodChoiceInfo">
						<div class="hhjx_img f_l">
							<img src="upload/${cg.picpath}" />
						</div>
						<div class="maoshu">
							<p>
								<span><img src="img/tianmao.png"/>${cg.title}</span>
							</p>
							<div class="sale_num">
								<span class="f_l">原价<i>${cg.pririce}</i></span>
								<span class="f_r">销量<i>${cg.xiaoliang}万</i></span>
							</div>
							<div class="ds">
								<div class="f_l"></div>￥<span>${cg.price}</span>
								<b class="f_r"><i>${cg.youhui}</i>元券</b>
							</div>
						</div>
						</a>
						
					</c:forEach>
				
				</div>
			</div>
		</div>
		<div id="but">
		<iframe src="footer.jsp" width="1200px" height="149px" frameborder="0" scrolling="no" style="margin: auto;"></iframe>	
		</div>
	</body>
</html>
