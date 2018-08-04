<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
}
</style>
</head>
<body>
	<table border=1>
		<c:forEach var="menu" items="${role.menus}">
				<!-- 没有二级菜单就合并前两列 -->
				<c:choose>
					<c:when test="${empty menu.father_menu_id}">
						<tr>
						<td colspan="2">
							<input type="checkbox" value="${menu.menu_id}" />${menu.menu_name}
						</td>
						<td>
							<c:forEach var="fun" items="${menu.funs}">
								<input type="checkbox" value="${fun.fun_id}" />${fun.fun_name}
							</c:forEach>
						</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td rowspan="3">
								<input type="checkbox" value="${menu.menu_id}" />${menu.menu_name}
							</td>
							<td>
								<c:forEach var="fun" items="${menu.funs}">
									<input type="checkbox" value="${fun.fun_id}" />${fun.fun_name}
								</c:forEach>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
		</c:forEach>
	</table>
</body>
</html>