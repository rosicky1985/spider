<%@ include file="/resources/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel=StyleSheet href="${ctx}/resources/css/screen.css"></link>
		<title>TaskList</title>
		<script language="javascript"></script>
	
	</head>
	<body style="background-image:url(${ctx}/resources/images/bg13-1.jpg);" >
		<div style="width:800px; margin:0 auto; height:1024px ">
			<div id="header">
				<span><img src="${ctx}/resources/images/BlackSpider.png"
					width="100" style="vertical-align: middle;" /> </span> <span>Spider</span>
			</div>
			<div id="navi">
				<ul>
					<li><a id="apple" href="http://www.apple.com"
						style="margin:0px 5px 0px 5px"></a>
					</li>
					<li><a id="spider" href="${ctx}/task/run.do"
						style="margin:0px 5px 0px 5px"></a>
					</li>
					<li><a id="music" href="http://www.apple.com"
						style="margin:0px 5px 0px 5px"></a>
					</li>
					<li><a id="tools" href="http://www.apple.com"
						style="margin:0px 5px 0px 5px"></a>
					</li>
				</ul>
			</div>
			<div id="content">
				<div style="float: left; width: 500;padding: 0px 50px 0px 100px">
					<table align="center">
						<thead>
							<tr>
								<th>Spied</th>
								<th>Download</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="task" items="${list}">
								<tr>
									<td><fmt:formatDate value="${task.created}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><a href="${ctx}/resources/${task.destination}"><img
											src="${ctx}/resources/images/buttons/down3.png" width="23"
											height="18" style="margin: 5px 5px 5px 0px" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div style="padding: 0px 50px 0px 50px;float:right;width:295;font-family:微软雅黑;font-size:12;border-left-style: solid;border-width:2px;border-left-color:#666">
					what's happening	
				</div>
				<div style="clear:both;height:0px;"></div>
			</div>
		</div>
	</body>
</html>