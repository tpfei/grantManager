<%@ page contentType="text/html" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" href="menu/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="menu/dtree/dtree.js"></script>
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function addTab(title, url){
			if ($('#tt').tabs('exists', title)){
				$('#tt').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<script type="text/javascript">
			d = new dTree('d');
			d.add(0, -1, '汽车租赁');
			<c:forEach var="role" items="${user.roles}">
				<c:forEach var="menu" items="${role.menus}">
					<c:choose>
						<c:when test="${empty menu.father_menu_id}">
							d.add('${menu.menu_id}', 0, '${menu.menu_name}');//添加菜单
							<c:forEach var="fun" items="${menu.funs}">
								d.add('${fun.fun_id}','${menu.menu_id}','${fun.fun_name}', '${fun.fun_url}');//菜单的权限
							</c:forEach>
						</c:when>
						<c:otherwise>
							d.add('${menu.menu_id}','${menu.father_menu_id}', '${menu.menu_name}');//添加子菜单
							<c:forEach var="fun" items="${menu.funs}">
								d.add('${fun.fun_id}','${menu.menu_id}','${fun.fun_name}', '${fun.fun_url}');//子菜单的权限
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:forEach>
			document.write(d);
		</script>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'" id="tt" class="easyui-tabs" style="width:100%;height:700px;"class="easyui_layout">
		<div title="主页"></div>
	</div>
</body>
</html>