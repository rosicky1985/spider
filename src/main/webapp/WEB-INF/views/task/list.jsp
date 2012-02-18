<%@ include file = "/resources/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=StyleSheet href="${ctx}/resources/css/screen.css"></link>
<title>TaskList</title>
</head>
<body style="background-image:url(${ctx}/resources/images/bg4.png)">
	<div style="width: 800px;margin:0 auto;height:100%">
	<div id="header">
		<span><img src="${ctx}/resources/images/BlackSpider.png" width="100" style="vertical-align:middle;"/></span>
		<span>Spider</span>
	</div>
	<div id="navi">
		<a href="${ctx}/task/run.do"><img src="${ctx}/resources/images/Spider.png" width="30" height="30" style="margin:5px 5px 5px 0px"/></a>
	</div>
	<table align="center">
		<thead>
			<tr>
				<th>Spied</th>
				<th>Download</th>
			</tr>
		</thead>
		<c:forEach var="task" items="${list}">
			<tr>
				<td><fmt:formatDate value="${task.created}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="${ctx}/resources/${task.destination}">download</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<%@ include file="/resources/common/footer.jsp"%>
</body>
</html>