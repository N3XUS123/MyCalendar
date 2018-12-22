$(document).ready(
			/*<![CDATA[*/
 
			function() {
				$('#calendar').fullCalendar(
						{
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							defaultView : 'agendaWeek',
							firstDay : 1,
							minTime : "06:00:00",
							maxTime : "21:00:00",
							editable : false,
							eventLimit : true, // allow "more" link when too many
							// events
							events : {
								url : '/reservelist',
								type : 'GET',
								error : function() {
									alert('Error');
								},
								color : 'blue', // a non-ajax option
								textColor : 'white' // a non-ajax option
							},

							eventRender : function(reserva, element) {
								element.find(".fc-content").remove();
								var removeLink = '/web/removeReserve/' + reserva.id;
								var editLink = '/web/editReserve/' + reserva.id;
								var new_description = 
										'<div class="fc-content">'
										+ '<h6><small class="text-light">'
										+ moment(reserva.start).format("HH:mm")
										+ '-'
										+ moment(reserva.end).format("HH:mm")
										+ '<a href="' + removeLink + '" class="float-right pr-2" title="Delete"><i class="zmdi zmdi-delete text-light pt-1"></i></a>'
										+ '<a href="' + editLink + '" class="float-right pr-2" title="Edit"><i class="zmdi zmdi-edit text-light pt-1"></i></a>'
										+ '</small></h6>'
										+ '<strong>Aula: </strong>'
										+ reserva.aula.nombre + '<br/>'
										+ '</div>';
								element.append(new_description);
							}
						});

			});
	/*]]>*/