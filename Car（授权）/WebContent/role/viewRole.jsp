<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看所有角色</title>
<style type="text/css">
	table
	{
		border-collapse:collapse;
	}
</style>
<script type="text/javascript" src="js/viewRole.js"></script>
<script type="text/javascript">
	var totalPage = "${pm.totalPage}";//全局变量
	var currentPage="${currentPage}";//全局变量
</script>
</head>
<body>
	<table border="1">
		<tr>
			<th>角色ID</th>
			<th>角色名</th>
			<th>授权</th>
			<th>删除</th>
		</tr>
		<c:forEach var="role" items="${pm.list}">
			<tr>
				<td>${role.role_id}</td>
				<td>${role.role_name}</td>
				<td>
					<a href="grantRole.do?roleId=${role.role_id}">授权</a>
				</td>
				<td>
					<a href="#">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="首页" id="first"/>
	<input type="button" value="上一页" id="prev"/>
	<input type="button" value="下一页" id="next"/>
	<input type="button" value="尾页" id="last"/>
</body>
</html>