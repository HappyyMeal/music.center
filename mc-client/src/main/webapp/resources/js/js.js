$(function() {
	var lis = $('.nav > li');
	menu_focus(lis[0], 1);

	$('#send').on('click', function() {
		resetState();
		var songTitle = $('#songTitle').val();
		if (songTitle != null && songTitle != '') {
			getSongsByTitle(songTitle);
		} else {
			alert('Поле поиска не может быть пустым.')
		}
	});

	function resetState() {
		$('#failMessage').css('display', 'none');
		$('#resultTable').css('display', 'none');
	}

	function getSongsByTitle(songTitle) {
		console.log(songTitle);

		$
				.ajax(
						{
							dataType : 'json',
							url : "http://ukuzmin.com:8082/mc-server/v1/music/song/find/"
									+ songTitle,
						})
				.done(function(data) {
					if (data != null && data.length > 0) {
						appendResults(data);
					} else {
						appendNothingHasBennFound();
					}

				})
				.fail(
						function() {
							alert("Unexpected error occured. Please contact your system administrator.")
						});
	}

	function appendResults(data) {
		$('#resultTable').css('display', 'inline-block');
		$('#results').html('');

		for ( var i in data) {
			$('tbody')
					.append(
							'<tr><td>'
									+ data[i].songTitle
									+ '</td><td><audio controls="controls" preload="none"><source src="'
									+ data[i].songLink
									+ '" type="audio/mp3"></audio></td>'
									+ '<td><a href="/mc-client/playlist/show_playlists/'
									+ data[i].songId
									+ '"><button class="btn btn-default" type="button">Добавить</button></a></td></tr>');
		}
	}

	function appendNothingHasBennFound() {
		$('#failMessage').css('display', 'block');
	}

	$('.message a').click(function() {
		$('form').animate({
			height : "toggle",
			opacity : "toggle"
		}, "slow");
	});

	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confirm_password");

	function validatePassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Введенные пароли не совпадают");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;

	$('.form').find('input, textarea').on('keyup blur focus', function(e) {

		var $this = $(this), label = $this.prev('label');

		if (e.type === 'keyup') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.addClass('active highlight');
			}
		} else if (e.type === 'blur') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.removeClass('highlight');
			}
		} else if (e.type === 'focus') {

			if ($this.val() === '') {
				label.removeClass('highlight');
			} else if ($this.val() !== '') {
				label.addClass('highlight');
			}
		}

	});

	$('.tab a').on('click', function(e) {

		e.preventDefault();

		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');

		target = $(this).attr('href');

		$('.tab-content > div').not(target).hide();

		$(target).fadeIn(600);

	});

});
