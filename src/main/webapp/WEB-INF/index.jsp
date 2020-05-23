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
	<div>
		<form method="POST" style="float:left;margin-right:25px;">
			<label for="name">Add new artist</label> 
			<input id="name" name="name" type="text"> 
			<input type="submit" value="Add artist">
		</form>
		<form method="GET">
			<label for="search">Search for artist</label>
			<input id="search" name="search" type="text">
			<input type="submit" value="Search">
		</form>
	</div>
	<table>
		<thead>
			<th>#</th>
			<th>Artist</th>
			<th>Action</th>
		</thead>
		<tbody>
			<c:forEach items="${ items }" var="artist">
				<tr id="artist-${artist.getArtistId() }">
					<td><c:out value="${ artist.getArtistId() }"></c:out></td>
					<td>
						<a href="/albums?ArtistId=${ artist.getArtistId() }">
							<c:out value="${ artist.getArtistName() }"></c:out>
						</a>
					</td>
					<td>
						<button onclick="removeArtist(${ artist.getArtistId() })">Remove</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="/scripts/app.js"></script>
</body>
</html>