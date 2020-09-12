$(function(){
	//全选全不选
	$("#allCheckBox").click(function(){
		
		var isChecked = $("#allCheckBox").is(":checked");
		
		$(".cart_td_1").find("input[name='cartCheckBox']").attr("checked",isChecked);
		
	});
	//判断是否全选
	$(".cart_td_1").children("input[name='cartCheckBox']").click(function(){
		//得到复选框个数
		var cartCheckedes = $(".cart_td_1").children("input[name='cartCheckBox']");
		var sum = cartCheckedes.size();
		var k = 0; //选中个数
		
		for (var i = 0;i<cartCheckedes.length;i++) {
			//cartCheckedes[i]是dom对象
			if ($(cartCheckedes[i]).is(":checked")) {
				//每选中一个k+1
				k++;
			}
		}
		if (sum==k) {
			//全部选中后，全选框自动勾选
			$("#allCheckBox").attr("checked",true);
		} else{
			$("#allCheckBox").attr("checked",false);
		}
	});
	
	function productCount(){
		var zongjiage = 0;
		var zongjifen = 0;
	
		//得到商品相应的所有行
		var products = $("#shopping").find("tr[id]");
		//循环遍历商品行
		products.each(function(i,dom){
			//得到某一行
			var productTr = $(dom);
			var shuliang = productTr.find(".cart_td_6").find("input").val();
			var danjia = productTr.find(".cart_td_5").text();
			var xiaoji = shuliang*danjia;
			//四舍五入，保留两位小数
			var jiaGe = Math.round(xiaoji*100)/100;
			//设置小计的值
			productTr.find(".cart_td_7").text(jiaGe);
			zongjiage +=jiaGe;
			
			var jifen = productTr.find(".cart_td_4").text()*shuliang;
			zongjifen +=jifen;
		});
		//设置总价格
		var zongjiage1 = Math.round(zongjiage*100)/100;
		$("#total").text(zongjiage1);
		//设置总积分
		$("#integral").text(zongjifen);
	}
	productCount();
	//点击+1
	$(".cart_td_6").find("img[alt='add']").click(function(){
		//得到jq对象
		var inputJq = $(this).parent().find("input");
		//得到input中的值
		var value = inputJq.val();
		value++;
		
		inputJq.val(value);
		//重新计算
		productCount();
	});
	
	//点击-1
	$(".cart_td_6").find("img[alt='minus']").click(function(){
		//得到jq对象
		var inputJq = $(this).parent().find("input");
		//得到input中的值
		var value = inputJq.val();
		value--;
		if (value<=0) {
			value=1;
		}
		inputJq.val(value);
		//重新计算
		productCount();
	});
	//点击删除
	$(".cart_td_8").children("a").click(function(){
		var isYes = window.confirm("确定删除吗？")
		if (isYes) {
			//prev 前一个标签
			$(this).parent().parent().prev().remove();
			$(this).parent().parent().remove();
			productCount();
		}
	});
	//删除所选
	$("#deleteAll").click(function(){
		//得到商品相应的行  数组
		var isYes = window.confirm("确认删除所选商品吗？");
		if (isYes) {
			var products = $("#shopping").find("tr[id]");
			for (var i=0;i<products.length;i++) {
				var tr = $(products[i]);
				var isChecked = tr.find("input[name='cartCheckBox']").is(":checked");
				
				if (isChecked) {
					tr.prev().remove();
					tr.remove();
				}
			}
			productCount();
		}
	});

	
	
	
});