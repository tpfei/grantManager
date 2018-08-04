<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="saveUser.do" method="POST">
		用户名：<input type="text" name="username" />
		<!-- 数据应该是从数据库取出来动态显示 -->
		<!--<c:forEach var="role" items="${requestScope.roles}">
			<input type="checkbox" name="roleId" value="${role.role_id}">${role.role_name}
		</c:forEach>-->
		<input type="submit" value="保存"/>
	</form>
</body>
</html>