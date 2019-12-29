/*$(document).ready(function() {
	
	var nazivFilterInput = $('#nazivFilterInput');

	var filmoviTable = $('#filmoviTable');

	function getFilmovi(){
		
		var nazivFilter = nazivFilterInput.val();
		
		var params = {
			'nazivFilter': nazivFilter
		};
		
		$.get('FilmoviServlet', params, function(data){
			console.log(data);
			
			if (data.status == 'success'){
				filmoviTable.find('tr:gt(1)').remove();
				
				var filmovi = data.filmovi;
				for(f in filmovi){}
				filmoviTable.append(
						'<tr>' + 
						'<td><a href="FilmServlet?id=' + filmovi[f].id + '">' + filmovi[f].naziv  + '</a></td>' + 
						'<td>' + filmovi[f].trajanje  + '</td>' + 
						'<td>' + filmovi[f].distributer  + '</td>' + 
						'<td>' + filmovi[f].reziser  + '</td>' + 
						'<td>' + filmovi[f].zemljaPorekla  + '</td>' + 
						'<td>' + filmovi[f].godinaProizvodnje  + '</td>' + 
						'<td>' + filmovi[f].glumci  + '</td>' +
						'<td>' + filmovi[f].opis  + '</td>' + 
						'<td>' + filmovi[f].zanrovi  + '</td>' +  
						'</tr>'
						);
			}
			
		});
	}
	nazivFilterInput.on('keyup', function(event) {
		getFilmovi();
		
		event.preventDefault();
		return false;
	});
	
	getFilmovi();
});*/