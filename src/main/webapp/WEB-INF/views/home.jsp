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
	.day{
		align:center;
		background-color:#EFDEAD;
	}
</style>
</head>
<body>
	<h1>Welcome spider 2.0</h1>
	<div>
		<ul>
			<li><a href="item/export/">导出所有数据</a></li>
		</ul>
	</div>
	<div>
		<h3>立即运行</h3>
		<ul>
			<li><a href="task/run/all/">所有</a></li>
			<li><a href="task/run/daily/">日报</a></li>
			<li><a href="task/run/weekly/">周报</a></li>
			<li><a href="task/run/monthly/">月报</a></li>
			<li><a href="task/run/today/">今天</a></li>
		</ul>
	</div>
	<div>
		<input type="button" id="pre" value="上月"></input> <input type="button"
			id="next" value="下月"></input>
		<table style="border-collapse:collapse" border=1>
			<thead>
				<tr>
					<th>周日</th>
					<th>周一</th>
					<th>周二</th>
					<th>周三</th>
					<th>周四</th>
					<th>周五</th>
					<th>周六</th>
				</tr>
			</thead>
			<tbody id="tbody"></tbody>
		</table>
	</div>
</body>
</html>

