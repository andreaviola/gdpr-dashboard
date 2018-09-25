function BuildingSearch(prefix, baseUrl) {

	this.prefix = prefix;
	this.baseUrl = baseUrl;

	this.table = null;

	onReady = function() {
		$(document).on('click', 'a.btn-add', add);
		$(document).on('click', 'a.btn-view', view);
		$(document).on('click', 'a.btn-edit', edit);
		$(document).on('click', 'a.btn-del', del);
		$(document).on('submit', '#' + prefix + '-form', save);
	}
	
	add = function(event) {
		event.preventDefault();
		$('#' + prefix + '-modal #smallTitle').text('nuovo');
		$("#" + prefix + "-form :input").each(function() {
			$(this).val(null); 
		});
		$("#" + prefix + "-form :input[name='id']").val(0);
		$('#' + prefix + '-modal').modal();
	}
	
	view = function(event) {
		var id = $(this).attr('id');
		var url = baseUrl + "/" + id.substring(id.lastIndexOf('-') + 1);

		var jqxhr = $.ajax({
			url : url,
			type : "GET",
			dataType : "json"
		});
		jqxhr.done(function(data, textStatus, jqXHR) {
			console.log(data);
			var keys = Object.keys(data);
			var values = Object.values(data);
			for (var i in keys) {
				if (values[i])
					$('#' + prefix + '-modal-view').find("#" + keys[i]).text(values[i]);
			}
			$('#' + prefix + '-modal-view').modal();
		});
		jqxhr.fail(function(jqXHR, textStatus, errorThrown) {

		});
	}

	edit = function(event) {
		var id = $(this).attr('id');
		var url = baseUrl + "/" + id.substring(id.lastIndexOf('-') + 1);
		$('#' + prefix + '-modal #smallTitle').text('modifica');
		
		var jqxhr = $.ajax({
			url : url,
			type : "GET",
			dataType : "json"
		});
		jqxhr.done(function(data, textStatus, jqXHR) {
			populate(document.getElementById(prefix + '-form'), data);
			$('#' + prefix + '-modal').modal();
		});
		jqxhr.fail(function(jqXHR, textStatus, errorThrown) {

		});
	}

	del = function(event) {
		var id = $(this).attr('id');
		var url = baseUrl + "/" + id.substring(id.lastIndexOf('-') + 1);

		var jqxhr = $.ajax({
			url : url,
			type : "DELETE",
			dataType : "json"
		});
		jqxhr.done(function(data, textStatus, jqXHR) {
			if (data === 'SUCCESS') {
				table.ajax.reload();
				$.notify({
					icon : 'fa fa-check',
					title : '<strong>Eliminazione</strong>',
					message : '<strong>completata con successo</strong>',
					animate : {
						enter : 'animated fadeInRight',
						exit : 'animated fadeOutRight'
					}
				}, {
					type : 'success'
				});
			}
		});
		jqxhr.fail(function(jqXHR, textStatus, errorThrown) {

		});
	}

	save = function(event) {
		event.preventDefault();
		var formId = $(this).attr('id');
		var url = baseUrl + "/" + $("#" + formId + " :input[name='id']").val();
		var jqxhr = $.ajax({
			url : url,
			type : "POST",
			data : $("#" + formId).serializeObject(),
			dataType : "json"
		});
		jqxhr.done(function(data, textStatus, jqXHR) {
			table.ajax.reload();
			$('#' + prefix + '-modal').modal('hide');
		});
		jqxhr.fail(function(jqXHR, textStatus, errorThrown) {

		});
		// jqxhr.always(alwaysHandler);
	}

	initDataTable = function() {
		table = $('#' + prefix + '-table')
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"stateSave" : true,
							"order" : [ [ 1, 'desc' ] ],
							"pagingType" : "full_numbers",
							"lengthMenu" : [ [ 10, 25, 50 ], [ 10, 25, 50 ] ],
							responsive : true,
							"ajax" : {
								"type" : "GET",
								"url" : baseUrl + "/datatables"
							},
							"columns" : [
									{
										data : "code",
										name : "code",
										className : "dt-center"
									},
									{
										data : "name",
										name : "name",
										className : "dt-center all"
									},
									{
										data : "type",
										name : "type",
										className : "dt-center"
									},
									{
										data : "address",
										name : "address",
										className : "dt-center"
									},
									{
										data : "notes",
										name : "notes",
										className : "dt-center"
									},
									{
										data : "id",
										name : "id",
										orderable : false,
										searchable : false,
										render : function(data, type, row, meta) {
											if (type === 'display') {
												var btns = '<a class="btn-sm btn-primary btn-view" id="'
														+ prefix
														+ '-btn-view-'
														+ data
														+ '"><i class="fa fa-list" title="Visualizza"></i></a>';
												btns += '&nbsp';
												btns += '<a class="btn-sm btn-primary btn-edit" id="'
														+ prefix
														+ '-btn-edit-'
														+ data
														+ '"><i class="fa fa-edit" title="Modifica"></i></a>';
												btns += '&nbsp';
												btns += '<a class="btn-sm btn-primary btn-del" id="'
														+ prefix
														+ '-btn-del-'
														+ data
														+ '"><i class="fa fa-trash" title="Elimina"></i></a>';
												return btns;
											}
											return data;
										},
										className : "dt-center dt-nowrap all"
									} ]
						});
	}

	return {
		onReady : onReady,
		initDataTable : initDataTable
	};

}