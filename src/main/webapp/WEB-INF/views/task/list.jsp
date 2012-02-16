<%@ include file = "/resources/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=StyleSheet href="${ctx}/resources/css/screen.css"></link>
<title>TaskList</title>
</head>
<body>
	<%@ include file="/resources/common/header.jsp"%>
	<table>
		<thead>
			<tr>
				<th>Created</th>
				<th>Download</th>
			</tr>
		</thead>
		<c:forEach var="task" items="${list}">
			<tr>
				<td><fmt:formatDate value="${task.created}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${task.destination}</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/resources/common/footer.jsp"%>
</body>
</html>