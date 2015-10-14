<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>

<c:url value="/j_spring_security_logout" var="logoutUrl" />

<!-- csrt support -->
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<body>
	<div align="center">
		<h1>Engineers List</h1>
		<h2>
			<a href="new">New Engineer</a>
		</h2>

		<table border="1">
			<th>No</th>
			<th>Username</th>
			<th>Email</th>
			<th>Actions</th>

			<c:forEach var="engineer" items="${listEngineers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${engineer.first_name}</td>
					<td>${engineer.last_name}</td>
					<td>${engineer.email_addr}</td>
					<td><a href="edit?id=${engineer.engineer_id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=${engineer.engineer_id}"
						onclick="return confirm ('Delete ${engineer.first_name}  ${engineer.last_name}?');">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function checkDelete(first_name) {
			confirm("Delete " + first_name + "?");
		}
	</script>
</body>
</html>
