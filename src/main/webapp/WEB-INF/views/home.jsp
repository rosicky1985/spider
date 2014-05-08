<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spider 2.0</title>
<script src="${ctx }/resources/js/yui3-min.js"></script>
<script src="${ctx }/resources/js/calendar.js"></script>
<script src="${ctx }/resources/js/home.js"></script>
<style type="text/css" src="${ctx }/resources/css/calendar.css"></style>
<style type="text/css">
.day {
	align: center;
	background-color: #EFDEAD;
}
body{
	font-size:12px;
	font-family: 'Lucida Grande', Tahoma, Verdana, arial, sans-serif, hei;
}
ul li{
	display:inline;
}
fieldset{
	display:inline;
	float:left;
}
fieldset legend{
	color:rgb(11, 85, 196);
	font-weight: bold;
}
</style>
</head>
<body>
	<h1>Welcome spider 2.0</h1>
	<div>
		<form method="get" action="item/export" name="export">
		<fieldset>
		<legend>按目标时间导出</legend>
		<div>时间段开始<input name="start" size=12/>(yyyyMMdd,不填写代表不care)</div>
		<div>时间段结束<input name="end" size=12/>(yyyyMMdd,不填写代表不care)<input type="button" value="导出数据" onclick="validation()"></div>
		
		</fieldset>
		<script>
			function validation(){
				var exp = document.export;
				var url = exp.action;
				var param = [];
				if(exp.start.value.trim() != "") param.push({p:'start',v:exp.start.value});
				if(exp.end.value.trim() != "") param.push({p:'end',v:exp.end.value});
				var first = true;
				for(var i = 0 ; i < param.length;i ++){
					var p = param[i];
					if(first){
						url += '?';
						first = false;
					}else{
						url += '&';
					}
					url += (p.p + '=' + p.v);
				}
				window.location.href=url;
			} 
		</script>
		</form>
	</div>
	<div>
		<form method="get" action="item/exportbycreated" name="exportbycreated">
		<fieldset>
		<legend>按发生时间导出</legend>
		<div>时间段开始<input name="start" size=12/>(yyyyMMdd,不填写代表不care)</div>
		<div>时间段结束<input name="end" size=12/>(yyyyMMdd,不填写代表不care)<input type="button" value="导出数据" onclick="validationbycreated()"></div>
		
		</fieldset>
		<script>
			function validationbycreated(){
				var exp = document.exportbycreated;
				var url = exp.action;
				var param = [];
				if(exp.start.value.trim() != "") param.push({p:'start',v:exp.start.value});
				if(exp.end.value.trim() != "") param.push({p:'end',v:exp.end.value});
				var first = true;
				for(var i = 0 ; i < param.length;i ++){
					var p = param[i];
					if(first){
						url += '?';
						first = false;
					}else{
						url += '&';
					}
					url += (p.p + '=' + p.v);
				}
				window.location.href=url;
			} 
		</script>
		</form>
	</div>
	<fieldset>
		<legend>立即运行</legend>
		<ul>
			<li><a href="task/run/all/">所有</a></li>
		</ul>
		<ul><li><a href="task/run/daily/">日报</a></li>
			<li><a href="task/run/weekly/">周报</a></li>
			<li><a href="task/run/monthly/">月报</a></li></ul>
		<ul>
			<li><a href="task/run/today/">今天</a></li>
		</ul>
	</fieldset>
</body>
</html>

