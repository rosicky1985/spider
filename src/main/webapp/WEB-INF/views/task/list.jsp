<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TaskList</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Created</th>
				<th>Download</th>
			</tr>
		</thead>
		<c:forEach var="task" items="${list}">
			<tr>
				<td><fmt:formatDate value="${task.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>${task.destination}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>