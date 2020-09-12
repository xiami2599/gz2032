$().ready(function(){
	//全选或全不选
	$("#allCheckBox").click(function(){
		//得到$("#allCheckBox")的状态，（TRUE或FALSE）
		var isChecked = $(this).is(":checked");
		//设置其它的复选框跟$("#allCheckBox")的状态，（TRUE或FALSE）一致
		$(".cart_td_1").find("[name='cartCheckBox']").attr("checked",isChecked);
		
	});
	
	//判断是否是全选
	$(".cart_td_1").find("[name='cartCheckBox']").click(function(){
		var checkedBoxes = $(".cart_td_1").find("[name='cartCheckBox']");
		var num = checkedBoxes.size();
		var k = 0;
		checkedBoxes.each(function(i,dom){
			if($(dom).is(":checked")){
				k++;
			}
		});
		if(num==k){
			$("#allCheckBox").attr("checked",true);
		}else{
			$("#allCheckBox").attr("checked",false);
		}
	});
	
	//计算总价，积分，小计
	function countProducts(){
		//得到所以产品所在的行
		var productsTr = $("#shopping").find("tr[id]");
		var sumzongJia = 0;//用于总价的累加
		var jiFen = 0;//用于积分的累加
		//设置每一行的小计
		productsTr.each(function(i,dom){
			var currentTr = $(dom);//得到其中一行
			//得到单价
			var danJia  = currentTr.find("[class='cart_td_5']").text();
			//得到数量
			var shuLiang  = currentTr.children("[class='cart_td_6']").find("input").val();
			var xiaoJi = danJia * shuLiang;
			//设置当前行的小计
			currentTr.children("[class='cart_td_7']").text(xiaoJi);
			//计算总价（累加运算）；
			sumzongJia += xiaoJi;
			jiFen += currentTr.find("[class='cart_td_4']").text()*shuLiang;
		});
		//设置总价
		$("#total").text(sumzongJia);
		//设置积分
		$("#integral").text(jiFen);
	}
	 countProducts();
	//点击加1
	$(".cart_td_6").find("[alt='add']").click(function(){
		var input =  $(this).parent().find("input");
		var shuLiang =input.val();
		shuLiang++;
		input.val(shuLiang);
		countProducts();
	});
	
	//点击减1
	$(".cart_td_6").find("[alt='minus']").click(function(){
		var input =  $(this).parent().find("input");
		var shuLiang =input.val();
		shuLiang--;
		if(shuLiang<=0){
			shuLiang = 1;
			alert("数量最小值为1.");
		}
		input.val(shuLiang);
		countProducts();
	});
	
	//点击删除
	$(".cart_td_8").find("a").click(function(){
		$(this).parent().parent().prev().remove();
		$(this).parent().parent().remove();
		countProducts();
	});
	//删除所选的行
	$("#deleteAll").click(function(){
		//得到所以产品所在的行
		var productsTr = $("#shopping").find("tr[id]");
		productsTr.each(function(i,dom){
			var tr = $(dom);//当前行
			var isChecked = tr.find("input").is(":checked");
			if(isChecked){
				tr.prev().remove();
				tr.remove();
			}
		});
		countProducts();
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});