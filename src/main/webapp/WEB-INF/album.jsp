<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Albums</title>
<link rel="stylesheet"
	href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<h1>💿 Albums</h1>
	<div>
		<form method="POST" style="float:left;margin-right:25px;">
			<label for="name">Add new album</label> 
			<input id="name" name="name" type="text"> 
			<input type="submit" value="Add album">
		</form>
	</div>
	<table>
		<thead>
			<th>#</th>
			<th>Album</th>
			<th>Action</th>
		</thead>
		<tbody>
			<c:forEach items="${ items }" var="album">
				<tr id="album-${album.getAlbumId() }">
					<td><c:out value="${ album.getAlbumId() }"></c:out></td>
					<td>
						<c:out value="${ album.getAlbumTitle() }"></c:out>
					</td>
					<td>
						<button onclick="removeAlbum(${ album.getAlbumId() })">Remove</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="/scripts/app.js"></script>
</body>
</html>