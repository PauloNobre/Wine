$(function() {
	var uploadDrop = $('#upload-drop');
	
	var settings = {
		type: 'json',
		filelimit: 1,
		allow: '*.(jpg|jpeg|png)',
		action: '/fotos/' + uploadDrop.data('codigo'),
		complete: function(foto) {
			
		}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop(uploadDrop, settings);
});