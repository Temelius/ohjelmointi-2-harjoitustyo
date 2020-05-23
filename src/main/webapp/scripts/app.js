async function removeArtist(id) {
    let res = await fetch(`/?id=${id}`, 
 		   {method: 'DELETE'}) 
    .then(res => {
    	var rowId = `album-${id}`;
    	var myObj = document.getElementById(rowId);
    	myObj.remove();
    })  	
}