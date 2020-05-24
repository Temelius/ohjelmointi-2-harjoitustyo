async function removeArtist(id) {
    let res = await fetch(`/artists?id=${id}`, 
 		   {method: 'DELETE'}) 
    .then(res => {
    	var rowId = `artist-${id}`;
    	var myObj = document.getElementById(rowId);
    	myObj.remove();
    })  	
}

async function removeAlbum(id) {
    let res = await fetch(`/albums?id=${id}`, 
 		   {method: 'DELETE'}) 
    .then(res => {
    	var rowId = `album-${id}`;
    	var myObj = document.getElementById(rowId);
    	myObj.remove();
    })  	
}