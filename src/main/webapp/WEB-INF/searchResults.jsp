<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<link rel="stylesheet"
	href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<div>
		<a href="/artists">All artists</a>
	</div>
	<h1>Search for artist</h1>
	<form method="GET" action="/artists/search">
		<label for="search">Search for artist</label> <input id="search"
			name="q" type="text"> <input type="submit" value="Search">
	</form>
	<h2>Search Results:</h2>
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
					<td><a href="/albums?ArtistId=${ artist.getArtistId() }">
							<c:out value="${ artist.getArtistName() }"></c:out>
					</a></td>
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