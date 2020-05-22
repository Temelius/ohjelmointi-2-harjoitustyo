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
	<form method="POST">
		<label for="name">Add new artist</label>
		<input id="name" name="name" type="text">
		<input type="submit" value="Add artist">
	</form>
	<table>
		<thead>
			<th>#</th>
			<th>Artist</th>
			<th>Action</th>
		</thead>
		<tbody>
			<c:forEach items="${ items }" var="artist">
				<tr id="artist-${artist.getArtistId() }">
					<td>
						<c:out value="${ artist.getArtistId() }"></c:out>
					</td>
					<td>
						<c:out value="${ artist.getArtistName() }"></c:out>
					</td>
					<td>
						<button onclick="removeProduct(${ artist.getArtistId() })">Remove</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script src="/scripts/app.js"></script>
</body>
</html>