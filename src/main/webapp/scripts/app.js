async function removeProduct(id) {
    let res = await fetch(`/?id=${id}`, 
 		   {method: 'DELETE'}) 
    .then(res => {
    	var rowId = `artist-${id}`;
    	var myObj = document.getElementById(rowId);
    	myObj.remove();
    })  	
}