$(function() {
	var uploadDrop = $('#upload-drop');
	var containerFoto = $('.js-container-foto');
	
	var settings = {
		type: 'json',
		filelimit: 1,
		allow: '*.(jpg|jpeg|png)',
		action: '/fotos/' + uploadDrop.data('codigo'),
		complete: function(foto) {
			uploadDrop.addClass('hidden');
			containerFoto.prepend('<img src="' + foto.url + '" class="img-responsive" style="margin: auto"/>');
		},
		beforeSend: function(xhr) {
			var header = $('input[name=_csrf_header]').val();
			var token = $('input[name=_csrf]').val();
			xhr.setRequestHeader(header, token);
		}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop(uploadDrop, settings);
});