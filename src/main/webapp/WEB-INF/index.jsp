<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Artists</title>
<link rel="stylesheet"
	href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<h1>🎤 Artists</h1>
	<table>
		<thead>
			<th>#</th>
			<th>Artist</th>
		</thead>
		<tbody>
			<c:forEach items="${ items }" var="artist">
				<tr>
					<td>
						<c:out value="${ artist.getArtistId() }"></c:out>
					</td>
					<td>
						<c:out value="${ artist.getArtistName() }"></c:out>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>