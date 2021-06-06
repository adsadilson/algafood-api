$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);

	var id = button.data('codigo');
	var descricao = button.data('descricao');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + id);

	modal.find('.modal-body p span').html(id + ' - ' + descricao + '</strong> ?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();

	if ($('#msn-success').is(":visible")) {
		var msg = $('#msg-sucesso').text();
		var tipo = $('#msg-tipo').text();
		mostraDialogo(msg, tipo, 3000);
	};

	$('.js-currency').maskMoney({ decimal: ',', thousands: '.', allowZero: true });


	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();

		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');

		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});


		response.done(function(e) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + e + '</span>');
			botaoReceber.hide();
		});

		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});

	});

});


function mostraDialogo(mensagem, tipo, tempo) {

	// se houver outro alert desse sendo exibido, cancela essa requisição
	if ($("#message").is(":visible")) {
		return false;
	}

	// se não setar o tempo, o padrão é 3 segundos
	if (!tempo) {
		var tempo = 3000;
	}

	// se não setar o tipo, o padrão é alert-info
	if (!tipo) {
		var tipo = "info";
	}

	// monta o css da mensagem para que fique flutuando na frente de todos elementos da página
	var cssMessage = "display: block; position: fixed; top: 0; left: 25%; right: 25%; width: 50%; padding-top: 10px; z-index: 9999";
	var cssInner = "margin: 0 auto; box-shadow: 1px 1px 5px black;";

	// monta o html da mensagem com Bootstrap
	var dialogo = "";
	dialogo += '<div id="message" style="' + cssMessage + '">';
	dialogo += '    <div class="alert alert-' + tipo + ' alert-dismissable" style="' + cssInner + '">';
	dialogo += '    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>';
	dialogo += mensagem;
	dialogo += '    </div>';
	dialogo += '</div>';

	// adiciona ao body a mensagem com o efeito de fade
	$("body").append(dialogo);
	$("#message").hide();
	$("#message").fadeIn(200);

	// contador de tempo para a mensagem sumir
	setTimeout(function() {
		$('#message').fadeOut(300, function() {
			$(this).remove();
		});
	}, tempo); // milliseconds
}

$('.datepicker2').datepicker({
	format: 'dd/mm/yyyy',
	startDate: '-3d'
});